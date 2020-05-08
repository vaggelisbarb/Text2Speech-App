/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.StrategiesFactory;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for encoding a document file with the preferred encoding style
 */
public class EncodeDocument implements ActionListener{

	private MainAppGUI mainGUI;
	private StrategiesFactory strategiesfactory;
	private TextToSpeechAPI managerAudio;
	
	
	public EncodeDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		
		managerAudio = this.mainGUI.getAPI();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (mainGUI.getCurrentDocument() != null) {
			if (mainGUI.getCurrentDocument().getLineHashmapSize() != 0) {
				if (mainGUI.getcurrentEncodingTechnique() != null) {
				
					Document doc = mainGUI.getCurrentDocument();
					String encodeString = mainGUI.getcurrentEncodingTechnique();
					strategiesfactory = mainGUI.getStrategiesFactory();
					doc.setAudioManager(managerAudio);
					doc.tuneEncodingStrategy(strategiesfactory.createStrategy(encodeString));
				
					mainGUI.getCurrentDocument().playEncodedContents();
				}else
					mainGUI.popUpInformMessage("No encode techique is selected\nMake sure you select one before audio transformation", "Encoding Error");
			}else
				mainGUI.popUpWarningMessage("Empty Document to encode", "Encoding Error");
		}else
			mainGUI.popUpWarningMessage("No Document to encode", "Encoding Error");
	}
	
}
