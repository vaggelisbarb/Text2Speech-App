/**
 * 
 */
package text2speechapis;

import view.MainAppGUI;

/**
 * @author vaggelisbarb
 *
 */
public class TextToSpeechAPIFactory {
	
	private MainAppGUI mainGUI;
	
	public TextToSpeechAPIFactory(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}
	
	
	public TextToSpeechAPI createTTSAPI(String api) {
		switch (api) {
		case "TTSAdapter": 
			return new FreeTTSAdapter();
		case "FakeAPI":
			return new FakeTextToSpeechAPI();
		default:
			throw new IllegalArgumentException("Unexpected value: " + api);
		}
	}
		
}
