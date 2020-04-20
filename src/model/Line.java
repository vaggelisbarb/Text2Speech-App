/**
 * 
 */
package model;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;


/**
 * @author vaggelisbarb
 * Class responsible for each line of the document.
 */
public class Line {
	private ArrayList<String> words;
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	
	
	public Line(ArrayList<String> words) {
		this.words = words;
	}


	public Line(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.words = new ArrayList<String>();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}

	
	public void playLine() {
		// TODO
	}
	
	public void playReverseLine() {
		// TODO
	}
	
	public void playEncodedLine() {
		// TODO
	}
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		// TODO
	}
	
	
}
