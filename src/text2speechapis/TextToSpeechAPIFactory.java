/**
 * 
 */
package text2speechapis;

/**
 * @author vaggelisbarb
 *
 */
public class TextToSpeechAPIFactory {
	
	
	public TextToSpeechAPIFactory() {
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
