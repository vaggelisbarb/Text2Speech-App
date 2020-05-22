/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 *
 */
public class RemoveDocument implements ActionListener {
	
	private MainAppGUI mainGUI;	
	
	public RemoveDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (mainGUI.getCurrentDocument() != null)
			mainGUI.removeDocumentArea();
		else
			mainGUI.popUpWarningMessage("No document to remove", "Null document Error");
	}
	
}
