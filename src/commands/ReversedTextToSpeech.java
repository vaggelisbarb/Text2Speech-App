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
public class ReversedTextToSpeech implements ActionListener{

	private MainAppGUI mainGUI;
	private Document currentDocument;
	private TextToSpeechAPIFactory textfactory;
	private TextToSpeechAPI managerAudio;
	
	
	public ReversedTextToSpeech(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		currentDocument = mainGUI.getCurrentDocument();
		if (currentDocument != null) {
			if (currentDocument.getLineHashmapSize() != 0) {
				
				textfactory = new TextToSpeechAPIFactory(mainGUI);
		
				managerAudio = textfactory.createTTSAPI("TTSAdapter");
			
				currentDocument.setAudioManager(managerAudio);
				currentDocument.playReverseContents();
			}else
				mainGUI.popUpInformMessage("Document is empty !!", "No document");
		}else 
			mainGUI.popUpInformMessage("None Document is found\nAudio not generated!!", "Audio Error");
	}

}
