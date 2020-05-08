/**
 * 
 */
package encodingstrategies;

/**
 * @author vaggelisbarb
 *
 */
public abstract class TemplateEncoding implements EncodingStrategy{
	
	
	public String encode(String txt) {
		StringBuilder encoder = new StringBuilder();
		for (char txtChar : txt.toCharArray()){
			if (Character.isLetter(txtChar))
				encoder.append(this.mapCharacter(txtChar));
			else
				encoder.append(txtChar);
		}
		System.out.println("Encoded Txt : "+encoder.toString());
		return encoder.toString();
	}
	
	
	public abstract char mapCharacter(char txtChar);
	
}
