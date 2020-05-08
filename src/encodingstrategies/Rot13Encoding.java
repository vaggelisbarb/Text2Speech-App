/**
 * 
 */
package encodingstrategies;

/**
 * @author vaggelisbarb
 *
 */
public class Rot13Encoding extends TemplateEncoding {

	
	public Rot13Encoding () {}
	
	
	@Override
	public char mapCharacter(char txtChar) {

		if (txtChar >= 'a' && txtChar <= 'm')
			txtChar += 13;
		else if (txtChar >= 'A' && txtChar <= 'M')
			txtChar += 13;
		else if (txtChar >= 'n' && txtChar <='z')
			txtChar -=13;
		else if (txtChar >= 'N' && txtChar <= 'Z')
			txtChar -=13;
		return txtChar;
	}
	
}
