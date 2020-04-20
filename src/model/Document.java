/**
 * 
 */
package model;

import java.util.HashMap;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

/**
 * @author vaggelisbarb
 * Class responsible for document objects. A document object consists of multiple lines. Each line is an Line object.
 */
public class Document {
	
	private HashMap<Line, Integer> lineHashmap;
	private String author;
	private String title;
	private String creationDate;
	private String saveDate;
	
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	

	public Document() { 
		lineHashmap = new HashMap<Line, Integer>();
	}
	
	
	public Document(HashMap<Line, Integer> lineHashmap) {
		this.lineHashmap = lineHashmap;
		System.out.println("\"New Document Object Created\" with {"+lineHashmap+"} as given HashMap\n");
	}



	/**
	 * @param encodingStrategy 
	 * @param audioManager
	 */
	public Document(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}
	
	
	
	public int getLineHashmapSize() {
		return lineHashmap.size();
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
