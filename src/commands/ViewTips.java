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
public class ViewTips implements ActionListener{

	
	private MainAppGUI mainGUI;
	
	
	public ViewTips(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.enableTipsInternalFrame();
	}
	
}
