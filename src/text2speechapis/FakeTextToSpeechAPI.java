/**
 * 
 */
package text2speechapis;

import java.util.ArrayList;

/**
 * @author vaggelisbarb
 *
 */
public class FakeTextToSpeechAPI implements TextToSpeechAPI{

	private ArrayList<String> TextToPlay;
	
	public FakeTextToSpeechAPI() {
		TextToPlay = new ArrayList<String>();
	}
	
	@Override
	public void play(String text) {
		TextToPlay.add(text);
	}

	@Override
	public void setVolume(int vol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPitch(int pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRate(int rate) {
		// TODO Auto-generated method stub
		
	}

	public String getTextToPlay() {
		String fullText = "";
		for (String line : TextToPlay) {
			fullText += line;
		}
		return fullText;
	}

	
	
}
