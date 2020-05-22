package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import encodingstrategies.StrategiesFactory;
import encodingstrategies.TemplateEncoding;

public class AtBashEncodeTest {

	private static StrategiesFactory strategiesfactory;
	private static TemplateEncoding encoding;
	private static String atbashString;
	
	@Before
	public void setUp() throws Exception {
		strategiesfactory = new StrategiesFactory();
		encoding = (TemplateEncoding) strategiesfactory.createStrategy("AtBash");
		atbashString = "Zodzbh xlwv zh ru gsv tfb dsl vmwh fk nzrmgzrmrmt blfi xlwv droo yv z erlovmg khbxslkzgs dsl pmldh dsviv blf orev";

	}

	@Test
	public void testMapCharacter() {
		char[] alphabetLowCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		for (int i = 0; i < alphabetLowCase.length; i++) {
			char expectedChar = (char) (('z' - alphabetLowCase[i])+'a');
			assertEquals(expectedChar, encoding.mapCharacter(alphabetLowCase[i]));
		}
		
		for (int i = 0; i < alphabetUpperCase.length; i++) {
			char expectedChar = (char) (('Z' - alphabetUpperCase[i])+'A');
			assertEquals(expectedChar, encoding.mapCharacter(alphabetUpperCase[i]));
		}
	}

	@Test
	public void testEncode() {
		String expectedString = "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live";
		String decodedText = encoding.encode(atbashString);
		assertEquals(expectedString, decodedText);
	}

}
