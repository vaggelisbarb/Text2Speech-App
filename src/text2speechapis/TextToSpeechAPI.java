/**
 * 
 */
package text2speechapis;

/**
 * @author vaggelisbarb
 * Interface responsible for text to audio transformation.
 */
public interface TextToSpeechAPI {
	
	public void play(String text);
	
	public void setVolume(int vol);
	
	public void setPitch(int pitch);
	
	public void setRate(int rate);
}
