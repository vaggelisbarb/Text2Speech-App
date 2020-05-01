/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Document;
import model.Line;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for saving a document file
 */
public class SaveDocument extends IConstructDocument implements ActionListener{
	
	private MainAppGUI mainGUI;
	private File docToFile;
	private Document newDocument;
	
	
	public SaveDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n\t$$$ \"Save Document\" Command is given\n");
		
		// Browse through Home Directory
		JFileChooser fileBrowse = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileBrowse.setDialogTitle("Save document");
	
		// Set a filter for showing only .txt, files only
		fileBrowse.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileBrowse.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files","txt");
		fileBrowse.addChoosableFileFilter(filter);
			
		int userSelection = fileBrowse.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			int numOfObjLines = constructDocumentObject(fileBrowse);
			if (numOfObjLines != 0) {
				docToFile = fileBrowse.getSelectedFile();
				
				Path fullPath = Paths.get(fileBrowse.getSelectedFile().getAbsolutePath());
				Path fileName = fullPath.getFileName();
				try (PrintWriter out = new PrintWriter(fileBrowse.getSelectedFile())){
					out.print(mainGUI.getDocumentArea());
					
					mainGUI.popUpInformMessage("File : "+fileName.toString()+" saved\n"+"Full path : "+fullPath.toString(), "Save message");
					System.out.println("Save file to path : "+fullPath.toString());
					
					mainGUI.setCurrentDocument(newDocument);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
			
		}
	}


	@Override
	public int constructDocumentObject(JFileChooser fileBrowse) {
		int lineCounter = 0;
		LinkedHashMap<Line, Integer> linesHashmap;
		
		try {
			String docText = mainGUI.getDocumentArea();
			if (!docText.equals("")) {
				Scanner scanner = new Scanner(docText);
				
				linesHashmap = new LinkedHashMap<Line, Integer>();
				System.out.println("\"An empty HashMap<Line, Integer> has been created\"");
			
				String line = null;
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
	
					ArrayList<String> wordsList = lineTokenize(line);
					Line newLineObj = new Line(wordsList);
					System.out.println("At line {"+lineCounter+"} -> Object Line {"+newLineObj+"} -> ArrayList<String> consists of {"+wordsList.size()+"} words \n");
					
					linesHashmap.put(newLineObj,lineCounter);
					
					lineCounter++;
				}
				
				newDocument = new Document(linesHashmap);
				
				System.out.println("Total number of lines loaded from the file : "+lineCounter+"\n");
				System.out.println("~~Document Object consists of {"+newDocument.getLineHashmapSize()+"} Line Objects");
				
				
				scanner.close();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return newDocument.getLineHashmapSize();
	}
	
}
