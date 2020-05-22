/**
 * 
 */
package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Class responsible for encoding a SINGLE LINE with the selected encoding style
 */
public class EncodeLine implements ActionListener{

	private MainAppGUI mainGUI;
	private StrategiesFactory strategiesfactory;
	private TextToSpeechAPI managerAudio;
	
	
	public EncodeLine(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		managerAudio = this.mainGUI.getAPI();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		mainGUI.removeHighlights();
		Document currentDoc = mainGUI.getCurrentDocument();
		String technique = mainGUI.getcurrentEncodingTechnique();
		String selectedLine = mainGUI.getLineSelected();
		
		if (currentDoc != null) {
			if (currentDoc.getLineHashmapSize() != 0) {				
				if (technique != null && selectedLine != null) {
					int numLine = currentDoc.isTextObjectLine(selectedLine);
					if (numLine != -1) {
						strategiesfactory = mainGUI.getStrategiesFactory();
						EncodingStrategy strategy = strategiesfactory.createStrategy(technique);
						
						currentDoc.setAudioManager(mainGUI.getAPI());
						currentDoc.tuneEncodingStrategy(strategy);
						
						currentDoc.playEncodedLine(numLine);
					}
				}
			}
		}
	}
	
}
