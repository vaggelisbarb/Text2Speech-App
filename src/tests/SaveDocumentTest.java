package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Document;
import model.Line;

public class SaveDocumentTest {
	
	private static String filename;
	private static Document document;
	
	@Before
	public void setUp() throws Exception {
		filename = "src/tests/Aristotle.txt";
		document = new Document();
	}

	@Test
	public void testConstructDocumentObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testActionPerformed() {
		fail("Not yet implemented");
	}

}
