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
 * Class responsible for transforming a document file to audio
 */
public class TextToSpeech implements ActionListener{

	private MainAppGUI mainGUI;
	private Document currentDocument;
	private TextToSpeechAPI managerAudio; 
	
	public TextToSpeech(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		
		// Create a new TextToSpeechAPIFactory
		managerAudio = this.mainGUI.getAPI();		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.removeHighlights();
		currentDocument = mainGUI.getCurrentDocument();
		
		// Check if the document we try to use is NULL or empty
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				
				// Set this TTSAdapter object to the current document
				currentDocument.setAudioManager(managerAudio);
				
				// Play contents of the document
				currentDocument.playContents();
			}else
				mainGUI.popUpWarningMessage("Document is empty !!", "No document");
		}else 
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");
		
	}
	
}
