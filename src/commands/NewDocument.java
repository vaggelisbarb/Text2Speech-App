/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import model.Document;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for creating a new document file
 */
public class NewDocument implements ActionListener{
	
	private MainAppGUI mainGUI;
	private Document newDocument;
	
	
	public NewDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n\t$$$ \"New Document\" command is given");
		
		newDocument = new Document();
		
		mainGUI.clearTextArea();
		mainGUI.enableDocEdit();
		mainGUI.popUpInformMessage("A new document is ready for use", "New Document Message");
		mainGUI.setCurrentDocument(newDocument);
	}
	
}
