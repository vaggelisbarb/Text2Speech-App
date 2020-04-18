/**
 * 
 */
package model;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

/**
 * @author vaggelisbarb
 * Class responsible for document objects. A document object consists of multiple lines. Each line is an Line object.
 */
public class Document {
	
	private String author;
	private String title;
	private String creationDate;
	private String saveDate;
	
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	

	public Document() { }

	
	/**
	 * @param encodingStrategy 
	 * @param audioManager
	 */
	public Document(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}
	


	public void playContents() {
		// TODO
	}
	
	public void playReverseContents() {
		// TODO
	}
	
	public void playEncodedContents() {
		// TODO
	}
	
	public void playLine(int numLine) {
		// TODO
	}
	
	public void playReverseLine(int numLine) {
		// TODO
	}

	public void playEncodedLine(int numLine) {
		// TODO
	} 
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		// TODO
	}
}
