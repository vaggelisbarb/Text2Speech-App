/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;
import text2speechapis.TextToSpeechAPI;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 *
 */
public class ReversedLineToSpeech implements ActionListener{

	private MainAppGUI mainGUI;
	private Document currentDocument;
	private TextToSpeechAPI manageraudio;
	
	public ReversedLineToSpeech(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		manageraudio = this.mainGUI.getAPI();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.removeHighlights();
		
		currentDocument = mainGUI.getCurrentDocument();
		String selectedLine = mainGUI.getLineSelected();
		
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				if (selectedLine != null) {
					int numLine = currentDocument.isTextObjectLine(selectedLine);
					if (numLine != -1) {
						currentDocument.setAudioManager(manageraudio);
						currentDocument.playReverseLine(numLine);
					}else
						mainGUI.popUpInformMessage("Select a single line ONLY", "Selected text Error");

				}else
					mainGUI.popUpWarningMessage("No text is selected", "No line selected Error");
			}else
				mainGUI.popUpWarningMessage("Document is empty !!", "No document");

		}else
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");

	}
	
}
