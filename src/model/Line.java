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
	

	
	public Line(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}


	public Line(ArrayList<String> words) {
		this.words = words;
	}


	public Line(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.words = new ArrayList<String>();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}

	public boolean checkForLineChanges(Line otherLine) {
		boolean isTheSameLine = this.words.equals(otherLine.words);
		return isTheSameLine;
	}
	

	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}


	@Override
	public String toString() {
		String line = "";
		for (String word : words)
			line +="."+ word;
		return line;
	}


	public void playLine() {
		audioManager.play(toString());
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
