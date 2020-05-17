/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	}


	public Document(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		super();
		this.encodingStrategy = encodingStrategy;
		this.audioManager = audioManager;
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

	
	public int isTextObjectLine(String text) {
		ArrayList<Line> lineKeySet = new ArrayList<Line>(contents.keySet());
		for (int i = 0; i < lineKeySet.size(); i++) {
			
			Line line = (Line) lineKeySet.get(i);
			Integer numLine = contents.get(line);
			String lineInString = line.toDotString();
			if (text.compareTo(lineInString) == 0) {
				return numLine;
			}
		}
		return -1;
	}
	
	
	/**
	 * Play each Line of the Document
	 * Iterate the LinkedHashMap of Lines and for each Line object
	 * call Line.playLine()
	 */
	public void playContents(){
		System.out.println("\n____DOCUMENT TO AUDIO____\n");
		ArrayList<Line> lineKeySet = new ArrayList<Line>(contents.keySet());
		for (int i = 0; i < lineKeySet.size(); i++) {
			
			Line line = (Line) lineKeySet.get(i);
			Integer numberOfLine = contents.get(line)+1;
			System.out.println("Line["+numberOfLine+"] : "+line.lineToString());
			line.playLine();
		}
			
	}
	
	/**
	 * Play reversed each Line Of the Document
	 * The same code strategy with playContents().
	 * The iteration starts from the last key of the the LinkedHashmap.
	 */
	public void playReverseContents() {
		System.out.println("\n____REVERSED DOCUMENT TO AUDIO____\n");
		
		//create an ArrayList initialized with keys of map
		ArrayList<Line> lineKeySet = new ArrayList<Line>(contents.keySet());
		for (int i = lineKeySet.size() - 1; i >= 0; i--) {
			
			//get key
			Line line = (Line) lineKeySet.get(i);
			Integer numberOfLine = contents.get(line)+1;
			System.out.print("Line["+numberOfLine+"] : "+line.reverseLineToString()+"\n");
			line.playReverseLine();
		}

	}
	
	public void playEncodedContents() {
		System.out.println("\n____ENCODED DOCUMENT TO AUDIO____\n");
	
		//create an ArrayList initialized with keys of map
		ArrayList<Line> lineKeySet = new ArrayList<Line>(contents.keySet());
		for (int i = 0; i < lineKeySet.size(); i++) {
					
			//get key
			Line line = (Line) lineKeySet.get(i);
			line.playEncodedLine();
		}
	}
	
	public void playLine(int numLine) {
		for (Entry<Line, Integer> entry : contents.entrySet()) {
			if (entry.getValue() == numLine) {
				entry.getKey().playLine();
			}
		}
	}
	
	public void playReverseLine(int numLine) {
		// TODO
	}

	public void playEncodedLine(int numLine) {
		// TODO
	} 
	
	
	public void tuneEncodingStrategy(EncodingStrategy strategy) {
		this.encodingStrategy = strategy;
		ArrayList<Line> lineKeySet = new ArrayList<Line>(contents.keySet());

		for (int i = 0; i < lineKeySet.size(); i++) {
			Line line = (Line) lineKeySet.get(i);
			line.tuneEncodingStrategy(strategy);
		}
	}
}
