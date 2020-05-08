/**
 * 
 */
package commands;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Document;
import view.MainAppGUI;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author vaggelisbarb
 * Class responsible for creating a new document file
 */
public class NewDocument implements ActionListener{
	
	private MainAppGUI mainGUI;
	private Document newDocument;
	private JTextField authorField ;
	private JTextField titleField;
	private JPanel inputPanel; 
	
	public NewDocument(MainAppGUI mainGUI) {
		this.mainGUI = mainGUI;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n\t~~~New Document~~~\n");
		displayInputMessage();
	}
	
	
	public int displayInputMessage() {
		authorField = new JTextField(15);
		authorField.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		
		titleField = new JTextField(15);
		titleField.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		
		inputPanel = new JPanel(new GridLayout(0, 1));
		inputPanel.setFont(new Font("League Spartan Semibold", Font.BOLD, 16));
		inputPanel.add(new JLabel("Author"));
		inputPanel.add(authorField);
		inputPanel.add(new JLabel("Document Title"));
		inputPanel.add(titleField);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, "Give document's details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION)
			if (!authorField.getText().equals("") && !titleField.getText().equals("")) {
				mainGUI.popUpInformMessage("A new document is ready for use\n\n"+"Author : "+authorField.getText()+"\nTitle : "+titleField.getText()+"\n\nCreation time : "+getCurrentDateTime(), "Document's Details");
				newDocument = new Document(authorField.getText(), titleField.getText(), getCurrentDateTime());
				mainGUI.setCurrentDocument(newDocument);
				
				mainGUI.setDocumentsDetailArea("AUTHOR : "+authorField.getText()+" , TITLE : "+titleField.getText()+" , CREATION : "+getCurrentDateTime());
				mainGUI.setDocAreaVisible();
				mainGUI.clearTextArea();
				mainGUI.enableDocEdit();
			}else
				displayInputMessage();
		
		return result;
	}
	
	public String getCurrentDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date currentDate = new Date();
		
		return formatter.format(currentDate);
 	}
	
	
}
