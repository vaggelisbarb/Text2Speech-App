/**
 * 
 */
package encodingstrategies;

/**
 * @author vaggelisbarb
 *
 */
public class AtBashEncoding extends TemplateEncoding{

	
	public AtBashEncoding() {}
	
	
	@Override
	public char mapCharacter(char txtChar) {
		int atbashChar = 0;
		if (txtChar >= 'A' && txtChar <= 'Z')
			atbashChar = ('Z' - txtChar) + 'A';
		else if (txtChar >='a' && txtChar <='z')
			atbashChar = ('z' - txtChar) + 'a';
		return (char)atbashChar;
	}
	
}
