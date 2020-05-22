package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import encodingstrategies.StrategiesFactory;
import encodingstrategies.TemplateEncoding;

public class Rot13EncodeTest {
	
	private static StrategiesFactory strategiesfactory;
	private static TemplateEncoding rotStrategy;
	private static String stringEncoded; 
	
	@Before
	public void setUp() throws Exception {
		strategiesfactory = new StrategiesFactory();
		rotStrategy = (TemplateEncoding) strategiesfactory.createStrategy("Rot13");
		
		stringEncoded = "Nyjnlf pbqr nf vs gur thl jub raqf hc znvagnvavat lbhe pbqr jvyy or n ivbyrag cflpubcngu jub xabjf jurer lbh yvir";
	
	}

	@Test
	public void testMapCharacter() {
		assertEquals('n', rotStrategy.mapCharacter('a'));
		assertEquals('o', rotStrategy.mapCharacter('b'));
		assertEquals('p', rotStrategy.mapCharacter('c'));
		assertEquals('q', rotStrategy.mapCharacter('d'));
		assertEquals('r', rotStrategy.mapCharacter('e'));
		assertEquals('s', rotStrategy.mapCharacter('f'));
		assertEquals('t', rotStrategy.mapCharacter('g'));

		assertEquals('u', rotStrategy.mapCharacter('h'));
		assertEquals('v', rotStrategy.mapCharacter('i'));
		assertEquals('w', rotStrategy.mapCharacter('j'));
		assertEquals('x', rotStrategy.mapCharacter('k'));
		assertEquals('y', rotStrategy.mapCharacter('l'));
		assertEquals('z', rotStrategy.mapCharacter('m'));
	}

	@Test
	public void testEncode() {
		String decodedRotText = rotStrategy.encode(stringEncoded);
		String expected = "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live";
		assertEquals(expected, decodedRotText);
	}

}
