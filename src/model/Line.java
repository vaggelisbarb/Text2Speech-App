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

	
	public int getNumOfWords() {
		return words.size();
	}


	/**
	 * @return the line in a string. Between each word is a '.' for the speech to become a bit slower
	 */
	public String toDotString() {
		String line = "";
		line = String.join(" ", words);
		return line;
	}

	
	public String lineToString() {
		String normalLine = "";
		normalLine = new StringBuilder(toDotString()).toString();
		return normalLine;
	}
	
	public String reverseLineToString() {
		String reverseLine = "";
		reverseLine = new StringBuilder(toDotString()).reverse().toString();
		return reverseLine;
	}
	

	public void playLine() {
		this.audioManager.play(this.toDotString());
	}
	
	public void playReverseLine() {
		this.audioManager.play(reverseLineToString());
	}
	
	public void playEncodedLine() {
		String lineTxt = "";
		for (String word : words)
			lineTxt +=" " + this.encodingStrategy.encode(word);
		this.audioManager.play(lineTxt);
	}
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		this.encodingStrategy = strategy;
	}
	
	
}
