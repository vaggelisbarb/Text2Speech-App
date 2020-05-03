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
public class SetPitch implements ActionListener {

	private MainAppGUI mainGUI;
	private TextToSpeechAPI managerAudio;
	private Document currentDocument;
	
	public SetPitch(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		currentDocument = mainGUI.getCurrentDocument();
		managerAudio = mainGUI.getAPI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		currentDocument = mainGUI.getCurrentDocument();
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				int pitchFromSlider = mainGUI.getPitchValue();
				
				managerAudio.setPitch(pitchFromSlider);
				
				// Set this TTSAdapter object to the current document
				currentDocument.setAudioManager(managerAudio);
				
				if (pitchFromSlider <= 80)
					mainGUI.popUpWarningMessage("Deep Voice\nSpeech maybe be less comprehensible", "Pitch Warning");
				else if (pitchFromSlider >= 220)
					mainGUI.popUpWarningMessage("High Voice\nSpeech maybe be less comprehensible", "Pitch Warning");
				
			}else
				mainGUI.popUpInformMessage("Document is empty !!", "No document");
		}else 
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");		
	}
	
}	
