/**
 * 
 */
package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * @author vaggelisbarb
 *
 */
public class FreeTTSAdapter implements TextToSpeechAPI{
	
	private VoiceManager vm;
	private Voice voice;
	
	
	/**
	 * Constructor
	 * 
	 * @param vm
	 * @param voice
	 */
	public FreeTTSAdapter(VoiceManager vm, Voice voice) {
		super();
		this.vm = vm;
		this.voice = voice;
	}

	
	@Override
	public void play(String text) {
		// TODO Auto-generated method stub
		
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
	
}
