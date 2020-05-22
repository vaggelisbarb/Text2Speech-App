package tests;

import static org.junit.Assert.*;

import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

import commands.OpenDocument;

public class LoadDocumentTest {
	
	private static String filename;
	private static FileReader fileReader;
	private static OpenDocument openDocument;
	
	private static int lineObjects;
	
	@Before
	public void setUp() throws Exception {
		filename = "src/tests/Aristotle.txt";
		openDocument = new OpenDocument(null);
		fileReader = new FileReader(filename);
		
		lineObjects = 5;
		
	}

	@Test
	public void testConstructDocumentObject() {
		//int numOfLines = openDocument.constructDocumentObject(fileReader);
		//assertEquals(FileUtilities.countLinesOfAFile(filename), numOfLines);
	}

}
