/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;

import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Factory class is responsible for creating the objects of 'commands Package'. This is the only class seen by ' view Package's ' classes.
 */
public class CommandsFactory {
	
	private MainAppGUI mainGUI;
	private OpenDocument opendoc;
	private NewDocument newdoc; 
	
	public CommandsFactory(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		System.out.println("CommandsFactory created ");
	}

	
	/**
	 * @param commandType a String that indicates which command type
	 * @return an ActionListener based on the command type that is given
	 */
	public ActionListener createCommand(String commandType) {
		switch (commandType) {
		case "OpenDocument" :
			return new OpenDocument(mainGUI);
		case "NewDocument" :
			return new NewDocument();
		case "SaveDocument" :
			return new SaveDocument();
		case "EditDocument" :
			return new EditDocument(mainGUI);
		case "TextHighlight" :
			return new HighlightText();
		default:
			throw new IllegalArgumentException("Unexpected command : " + commandType);
		}
	}

	
	
}
