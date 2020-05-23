package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Document;
import model.Line;
import text2speechapis.FakeTextToSpeechAPI;

public class ToSpeechTest {

	private static Document document;
	private static FakeTextToSpeechAPI fakeAPI;
	private static Line newLine1;
	private static Line newLine2;
	
	
	public ArrayList<String> lineTokenize(String singleLine) {
		ArrayList<String> wordsList = new ArrayList<String>();
		StringTokenizer defaultTokenizer = new StringTokenizer(singleLine);
		
		while (defaultTokenizer.hasMoreTokens())
			wordsList.add(defaultTokenizer.nextToken());	
		return wordsList;
	}
	
	@Before
	public void setUp() throws Exception {
		fakeAPI = new FakeTextToSpeechAPI();
		
		LinkedHashMap<Line, Integer> map = new LinkedHashMap<Line, Integer>();
		
		newLine1 = new Line(lineTokenize("Hello World. This works very fine."));
		newLine2 = new Line(lineTokenize("This test is about speechAPI"));
		
		map.put(newLine1, 0);
		map.put(newLine2, 1);
		
		document = new Document(map);
		document.setAudioManager(fakeAPI);		
		
	}

	@Test
	public void test() {
		document.playContents();
		String returnedValue = fakeAPI.getTextToPlay();
		
		assertEquals("Hello World. This works very fine.This test is about speechAPI", returnedValue);
	}

}
