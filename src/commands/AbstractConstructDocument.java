package commands;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

/**
* @author vaggelisbarb
*
*/
public abstract class AbstractConstructDocument {
		
	/**
	* Function responsible for tokenize a single line and store each token in a ArrayList<String>
	* 
	* @param singleLine A string containing a line of the file
	* @return An ArrayList<String> with the tokens
	*/
	public ArrayList<String> lineTokenize(String singleLine) {
		ArrayList<String> wordsList = new ArrayList<String>();
		StringTokenizer defaultTokenizer = new StringTokenizer(singleLine);
		
		while (defaultTokenizer.hasMoreTokens())
			wordsList.add(defaultTokenizer.nextToken());	
		return wordsList;
	}
	
	
	public abstract int constructDocumentObject(JFileChooser fileChooser);
		
}

