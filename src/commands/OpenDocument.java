/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Document;
import model.Line;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for opening a document file
 */
public class OpenDocument extends AbstractConstructDocument implements ActionListener{
	
	private MainAppGUI mainGUI;
	private Document currentDocument;
	private String authorline;
	private String titleline;
	private String createDate;
	private String saveDate;
	
	public OpenDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}

	
	/**
	 * Browse through directories after Load Document menu button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n\t~~~Load Document~~~\n");
		
		// Browse through Home Directory
		JFileChooser fileBrowse = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileBrowse.setDialogTitle("Load a .txt document ");
		
		// Set a filter for showing only .txt, files only
		fileBrowse.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileBrowse.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files","txt");
		fileBrowse.addChoosableFileFilter(filter);
		
		int returnValue = fileBrowse.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			int numObjLines = constructDocumentObject(fileBrowse);
			if (numObjLines != 0) {
				try {
					
					mainGUI.setDocAreaVisible();
					mainGUI.setTextArea(fileBrowse.getSelectedFile());
					
					Path fullPath = Paths.get(fileBrowse.getSelectedFile().getAbsolutePath());
					Path fileName = fullPath.getFileName(); 
					mainGUI.popUpInformMessage("Load successfully \n"+fileName.toString(), "Load message");
										
					
					// Set MainGUI currentDocument 
					mainGUI.setCurrentDocument(currentDocument);		
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				currentDocument.toString();
				
			}	
		}
	}
	
	

	
	/**
	 * Reads a given file line by line and tokenize each of it 
	 * 
	 * @param fileBrowse A JFileChooser component
	 * @return The # of lines of the file that eventually are loaded
	 */
	public int constructDocumentObject(JFileChooser fileChooser) {
		int lineCounter = 0;	
		LinkedHashMap<Line, Integer> linesHashMap;
		try {
			FileReader filereader = new FileReader(fileChooser.getSelectedFile());
			BufferedReader buffer = new BufferedReader(filereader);
			
			System.out.print("*File loaded for READ MODE*\n");
			
			linesHashMap = new LinkedHashMap<Line, Integer>();			
			
			String line = buffer.readLine();
			while (line != null) {
				
				if (line.startsWith("SAVED DATE :")) {
					saveDate = line;
				}
				else if (line.startsWith("AUTHOR :")) {
					authorline = line;
				}
				else if (line.startsWith("TITLE :")) {
					titleline = line;
				}
				else if (line.startsWith("CREATION DATE :")) {
					createDate = line;
				}
				
				ArrayList<String> wordList = lineTokenize(line);
				Line newLineObj = new Line(wordList);
				System.out.println("--> Line {"+lineCounter+"} consists of {"+newLineObj.getNumOfWords()+"} words");
				
				linesHashMap.put(newLineObj, lineCounter);
				
				lineCounter++;
				line = buffer.readLine();
			}
			System.out.println("CONSTRUCT : "+authorline+" "+titleline + " " + createDate + " " + saveDate);
			
			currentDocument = new Document(linesHashMap);
			currentDocument.setSaveDate(saveDate.substring(12));
			currentDocument.setAuthor(authorline.substring(8));
			currentDocument.setTitle(titleline.substring(7));
			currentDocument.setCreationDate(createDate.substring(15));

			
			System.out.println("--> Document Object consists of {"+currentDocument.getLineHashmapSize()+"} Line Objects");
			
			buffer.close();
			filereader.close();
			
		}catch (IOException exp) {
			exp.printStackTrace();
			System.err.println("File not found !!");
		}
		
		return currentDocument.getLineHashmapSize();
	}
	
	


}
