/**
 * 
 */
package commands;

import java.awt.event.ActionListener;
import view.MainAppGUI;

/**
 * @author vaggelisbarb
 * Factory class is responsible for creating the objects of 'commands Package'. This is the only class seen by ' view Package's ' classes.
 */
public class CommandsFactory {
	
	private MainAppGUI mainGUI;

	
	public CommandsFactory(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
		System.out.println("\t~~CommandsFactory created~~");
	}

	
	/**
	 * @param commandType a String that indicates which command type
	 * @return an ActionListener based on the command type that is given
	 */
	public ActionListener createCommand(String commandType) {
		switch (commandType) {
		case "OpenDocument" :
			return new OpenDocument(mainGUI);
		case "NewDocument" :
			return new NewDocument(mainGUI);
		case "SaveDocument" :
			return new SaveDocument(mainGUI);
		case "EditDocument" :
			return new EditDocument(mainGUI);
		case "TextHighlight" :
			return new HighlightText(mainGUI);
		case "TextToSpeech" :
			return new TextToSpeech(mainGUI);
		case "HighlightTextToSpeech":
			return new LineToSpeech(mainGUI);
		case "ReversedTextToSpeech":
			return new ReversedTextToSpeech(mainGUI);
		case "ReversedLineToSpeech":
			return new ReversedLineToSpeech(mainGUI);
		case "VolumeSettings":
			return new SetVolume(mainGUI);
		case "RatioSettings":
			return new SetRatio(mainGUI);
		case "PitchSettings":
			return new SetPitch(mainGUI);
		case "Rot13":
			return new Rot13Action(mainGUI);
		case "AtBash":
			return new AtBashAction(mainGUI);
		case "EncodeDocument":
			return new EncodeDocument(mainGUI);
		case "Tips" :
			return new ViewTips(mainGUI);
		default:
			throw new IllegalArgumentException("Unexpected command : " + commandType);
		}
	}

	
	
}
