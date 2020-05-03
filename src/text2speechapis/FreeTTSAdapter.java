/**
 * 
 */
package text2speechapis;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * @author vaggelisbarb
 *
 */
public class FreeTTSAdapter implements TextToSpeechAPI{
	
	private VoiceManager vm;
	private Voice voice;
	
	

	public FreeTTSAdapter() {
		try {
		    System.setProperty("logLevel", "OFF"); // INFO or WARN are also valid
		    System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		    Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		    
		    vm = VoiceManager.getInstance();
			voice = vm.getVoice("kevin16");
			
			System.out.println("\n\t~~~FreeTTSAdapter generated~~~");
		
		}catch(EngineException e) {
			  System.out.println("Unable to provide speech synthesis: " + e);
			  System.exit(1);
		}	
		
	}

	
	@Override
	public void play(String text) {
		try {
			voice.allocate();
			voice.speak(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setVolume(int vol) {
		voice.setVolume((float)vol/100);
		
	}

	@Override
	public void setPitch(int pitch) {
		voice.setPitch((float)pitch);		
	}

	@Override
	public void setRate(int rate) {
		voice.setRate((float)rate);
		
	}
	
}
