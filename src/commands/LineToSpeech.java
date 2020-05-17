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
 * Class responsible for transforming a SINGLE LINE of a document into audio
 */
public class LineToSpeech implements ActionListener{

	private MainAppGUI mainGUI;
	
	
	public LineToSpeech(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.removeHighlights();
		Document currentDocument = mainGUI.getCurrentDocument();
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				String selectedText = mainGUI.getLineSelected();
				if (selectedText != "") {
					int numLine = currentDocument.isTextObjectLine(selectedText);
					if (numLine != -1) {
						
						currentDocument.setAudioManager(mainGUI.getAPI());
						currentDocument.playLine(numLine);
					}else
						mainGUI.popUpInformMessage("Select a single line ONLY", "Selected text Error");
				}else
					mainGUI.popUpWarningMessage("No text selected", "No text selected Error");
			}else
				mainGUI.popUpWarningMessage("Document is empty !!", "No document");
		}else
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");

		
	}
	
}
