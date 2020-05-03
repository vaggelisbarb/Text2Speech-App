/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 *
 */
public class SetVolume implements ActionListener{

	private MainAppGUI mainGUI;
	private Document currentDocument;
	private TextToSpeechAPI managerAudio;
	
	public SetVolume(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		managerAudio = mainGUI.getAPI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		currentDocument = mainGUI.getCurrentDocument();
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				int volumeFromSlider = mainGUI.getVolumeValue();
				
				managerAudio.setVolume(volumeFromSlider);
				
				// Set this TTSAdapter object to the current document
				currentDocument.setAudioManager(managerAudio);
				
				if (volumeFromSlider <= 50)
					mainGUI.popUpWarningMessage("Volume too low", "Warning");
				
			}else
				mainGUI.popUpInformMessage("Document is empty !!", "No document");
		}else 
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");
	}
	
}
