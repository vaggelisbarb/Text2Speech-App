/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for editing document
 */
public class EditDocument implements ActionListener{
	private MainAppGUI mainGUI;
	
	
	public EditDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (mainGUI.getCurrentDocument() != null) {
			System.out.println("\n\t~~~Edit Document~~~\n");
			mainGUI.enableDocEdit();
			mainGUI.popUpInformMessage("Document edit option is enabled", "Edit Enable");
		}else
			mainGUI.popUpWarningMessage("No Document for edit\nLoad or create a new", "Edit Error");
	}

}
