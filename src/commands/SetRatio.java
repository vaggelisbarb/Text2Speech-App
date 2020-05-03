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
public class SetRatio implements ActionListener {

	private MainAppGUI mainGUI;
	private TextToSpeechAPI managerAudio;
	private Document currentDocument;
	
	public SetRatio(MainAppGUI mainGUI) {
		super();
		this.mainGUI = mainGUI;
		currentDocument = mainGUI.getCurrentDocument();
		managerAudio = mainGUI.getAPI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		currentDocument = mainGUI.getCurrentDocument();
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				int rateFromSlider = mainGUI.getRateValue();
				
				managerAudio.setRate(rateFromSlider);
				
				// Set this TTSAdapter object to the current document
				currentDocument.setAudioManager(managerAudio);
				
				if (rateFromSlider <= 100)
					mainGUI.popUpWarningMessage("Rate too LOW\nSpeech maybe be less comprehensible", "Rate Warning");
				else if (rateFromSlider >= 230)
					mainGUI.popUpWarningMessage("Rate too HIGH\nSpeech maybe be less comprehensible", "Rate Warning");
				
			}else
				mainGUI.popUpInformMessage("Document is empty !!", "No document");
		}else 
			mainGUI.popUpWarningMessage("None Document is found\nAudio not generated!!", "Audio Error");
	}

}
