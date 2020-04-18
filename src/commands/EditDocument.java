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
		mainGUI.enableDocEdit();
	}

}
