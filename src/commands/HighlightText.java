/**
 * 
 */
package commands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Painter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for highlighting a specific text of the document
 */
public class HighlightText implements ActionListener{

	private MainAppGUI mainGUI;
	private DefaultHighlightPainter painter;
	
	
	public HighlightText(MainAppGUI mainGUI) {
		super();
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		try {
			mainGUI.getHighlighter();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}	
