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
public class Rot13Action implements ActionListener{
	
	private MainAppGUI mainGUI;
	
	public Rot13Action(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.setCurrentEncodingTechnique("Rot13");
		
		System.out.println("\nEncoding Strategy -> *"+mainGUI.getcurrentEncodingTechnique()+"*");	
	}
	
	
}
