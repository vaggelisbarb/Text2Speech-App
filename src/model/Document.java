/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

/**
 * @author vaggelisbarb
 * Class responsible for document objects. A document object consists of multiple lines. Each line is an Line object.
 */
public class Document {
	
	private LinkedHashMap<Line, Integer> contents;
	private String author;
	private String title;
	private String creationDate;
	private String saveDate;
	
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	
	
	
	public Document(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}


	public Document(String author, String title, String creationDate) {
		this.author = author;
		this.title = title;
		this.creationDate = creationDate;
	}
	
	
	public Document(LinkedHashMap<Line, Integer> contents) {
		this.contents = contents;
		System.out.println("\"New Document Object Created\" with {"+contents+"} as given HashMap\n");
	}


	public Document(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
	}
	
	
	
	public int checkForDocumentChanges(Document otherDocument) {
		if (this.contents.size() == otherDocument.contents.size()) {
			if (this.contents.equals(otherDocument.contents)) {
				return 1;
			}
			return -1;
		}
		return 0;
	}
	
	
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


	public String getSaveDate() {
		return saveDate;
	}


	public void setSaveDate(String saveDate) {
		this.saveDate = saveDate;
	}


	public int getLineHashmapSize() {
		return contents.size();
	}


	public TextToSpeechAPI getAudioManager() {
		return audioManager;
	}


	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
		for (Line line : contents.keySet()) {
			line.setAudioManager(audioManager);
		}	
	}

	
	/**
	 * Play each Line of the Document
	 */
	public void playContents(){
		ArrayList lineKeySet = new ArrayList(contents.keySet());
		for (int i = 0; i < lineKeySet.size(); i++) {
			
			Line line = (Line) lineKeySet.get(i);
			System.out.println(line.lineToString());
			line.playLine();
		}
			
	}
	
	/**
	 * Play reversed each Line Of the Document
	 */
	public void playReverseContents() {
		//create an ArrayList initialized with keys of map
		ArrayList lineKeySet = new ArrayList(contents.keySet());
		for (int i = lineKeySet.size() - 1; i >= 0; i--) {
			
			//get key
			Line line = (Line) lineKeySet.get(i);
			System.out.print(line.reverseLineToString()+"\n");
			line.playReverseLine();
		}

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
