/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Line;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for opening a document file
 */
public class OpenDocument implements ActionListener{
	private MainAppGUI mainGUI;
	
	
	public OpenDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		System.out.println("OPEN DOCUMENT object created");
	}

	/**
	 * Browse through directories after Load Document menu button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Browse through Home Directory
		JFileChooser fileBrowse = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileBrowse.setDialogTitle("Load a document : ");
		
		// Set a filter for showing only .txt, files only
		fileBrowse.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileBrowse.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files","txt");
		fileBrowse.addChoosableFileFilter(filter);
		
		int returnValue = fileBrowse.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				FileReader filereader = new FileReader(fileBrowse.getSelectedFile());
				BufferedReader buffer = new BufferedReader(filereader);
				
				String line = null;
				//System.out.println(buffer.readLine());
				mainGUI.setTextArea(fileBrowse.getSelectedFile());
				
				mainGUI.popUpMessage("Load successfully");
				buffer.close();
			} catch (IOException exp) {
				exp.printStackTrace();
				System.err.println("File not found !!");
			}
		}
	}
	

}
