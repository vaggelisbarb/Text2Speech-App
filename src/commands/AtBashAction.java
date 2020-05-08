/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainAppGUI;

/**
 * @author vaggelisbarb
 *
 */
public class AtBashAction implements ActionListener{

	private MainAppGUI mainGUI;
	
	
	public AtBashAction(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.setCurrentEncodingTechnique("AtBash");
		
		System.out.println("\nEncoding Strategy -> *"+mainGUI.getcurrentEncodingTechnique()+"*");	
	}
	
}
