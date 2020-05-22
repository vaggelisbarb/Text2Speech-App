/**
 * 
 */
package encodingstrategies;

/**
 * @author vaggelisbarb
 *
 */
public class StrategiesFactory {
	
	
	
	public StrategiesFactory() {
	}

	
	public EncodingStrategy createStrategy(String encodeType) {
		TemplateEncoding encodingStrategy;
		if (encodeType.equals("Rot13")) {
			encodingStrategy = new Rot13Encoding();
			return encodingStrategy;
		}
		else if (encodeType.equals("AtBash")) {
			encodingStrategy = new AtBashEncoding();
			return encodingStrategy;
		}
		else
			throw new IllegalArgumentException("Unexpected command : " + encodeType);
	}
}
