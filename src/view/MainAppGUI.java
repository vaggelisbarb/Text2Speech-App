package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import model.Document;
import model.Line;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import commands.CommandsFactory;
import encodingstrategies.StrategiesFactory;

import java.awt.event.InputEvent;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;


import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JDesktopPane;
import java.awt.Choice;
import javax.swing.Icon;

public class MainAppGUI {

	// GUI frame and the MenuBar containing the menuItems above
	private JFrame frmTextToSpeech;
	private JMenuBar menuBar;

	// Menu buttons for the File part
	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenuItem mntmNewFile;
	private JMenuItem mntmSaveFile;
	private JMenuItem removeDocument;
	private JMenuItem mntmOpenFile;
	
	// Menu Buttons of the Speech part
	private JMenu menuSpeech;
	private JMenuItem text2speech_MenuItem;
	private JMenuItem mntmTextHighlight;
	private JMenuItem highlight2speech_MenuItem;
	private JMenuItem reverseText2speech_MenuItem;
	private JMenuItem reverseLine2SpeechMenuItem;
	
	// Menu buttons for the Encoding part
	private JMenu menuEncoding; 
	private JRadioButtonMenuItem atbshaMenuItem;
	private JRadioButtonMenuItem rot13MenuItem;
	private ButtonGroup encodingGroup;
	
	// Menu buttons for the Settings part
	 private JMenu menuSettings;
	 private JMenu volumeSubmenu;
	 private JMenuItem mntmSetVolume;
	 private JSlider volumeSlider;
	 private JMenu rateSubmenu;
	 private JSlider rateSlider;
	 private JMenuItem mntmSetRate;
	 private JMenu pitchSubmenu;
	 private JMenuItem mntmSetPitch;
	 private JSlider pitchSlider;
	
	 
	 private JMenuItem editMenuItem;
	 
	 
	 // Initializations
	 private CommandsFactory commandsfactory;
	 private Document currentDocument;
	 private Line currentSelectedLine;
	 private TextToSpeechAPIFactory textToSpeechAPIFactory;
	 private TextToSpeechAPI managerAudio;
	 private String currentEncodingStr;
	 private StrategiesFactory strategiesFactory;
	 
	 
	 private JEditorPane textArea;
	 private JScrollPane scrollPane;
	 private JTextField docDetailsArea;
	 private JSeparator separator;
	 private JMenuItem textEncode_MenuItem;
	 private JMenuItem encodedLine;
	 private JMenu helpMenu;
	 private JMenuItem mntmTextHighlight_1;
	 private JMenuItem tips;
	 
	 private JInternalFrame internalFrame;
	 
	 /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppGUI window = new MainAppGUI();
					window.frmTextToSpeech.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public MainAppGUI() {
		
		System.out.println("** Main Application GUI loaded **\n");
		
		// Creating the CommandsFactory object
		commandsfactory = new CommandsFactory(this);
		
		// Initialize current Document to null
		currentDocument = null;
		
		textToSpeechAPIFactory = new TextToSpeechAPIFactory();
		
		// Factory's 'createTTSAPI' method returns a new object that implements TextToSpeechAPI interface
		managerAudio = textToSpeechAPIFactory.createTTSAPI("TTSAdapter");
		
		// Creating the strategiesFactory
		strategiesFactory = new StrategiesFactory();
		
		
		initialize();
	}
	
	
	// Set the details of the document with the given text
	public void setDocDetails(String details) {
		this.docDetailsArea.setText(details);
	}
	
	
	// Check if a line is selected in the document area
	public String getLineSelected() {
		if (textArea.getSelectedText() != "") {
			try {
				this.getHighlighter();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return textArea.getSelectedText();
		}else
			return "";
	}
	
	
	// Remove Document area and set current document -> null
	public void removeDocumentArea() {
		this.currentDocument = null;
		scrollPane.setVisible(false);
	}
	
	// Set tips frame visible
	public void enableTipsInternalFrame() {
		internalFrame.setVisible(true);
	}
	
	// Returns the maingui's strategies factory
	public StrategiesFactory getStrategiesFactory() {
		return this.strategiesFactory;
	}
	
	// Return current encoding string of mainGui
	public String getcurrentEncodingTechnique() {
		return currentEncodingStr;
	}
	
	// Set current encoding string of mainGui
	public void setCurrentEncodingTechnique(String encodingTech) {
		currentEncodingStr = encodingTech;
		
	}
	
	// Return the current textToSpeechAPI
	public TextToSpeechAPI getAPI() {
		return this.managerAudio;
	}
	
	// Highlight the selected text in the document area
	public void getHighlighter() throws Exception {
		DefaultHighlighter highlighter = (DefaultHighlighter) textArea.getHighlighter();
		DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlightPainter(Color.YELLOW);
		removeHighlights();
		
		int start = textArea.getSelectionStart();
		int end = textArea.getSelectionEnd();
		
		highlighter.addHighlight(start, end, painter);
	}
	
	
	// Removing all highlighted text of the document area
	public void removeHighlights() {
		Highlighter highlighter = textArea.getHighlighter();
		highlighter.removeAllHighlights();
	}
	
	
	// Returns the value from the volume Slider component.
	public int getVolumeValue() {
		int volume = volumeSlider.getValue();
		return volume;
	}
	
	// Returns the value of the rate Slider component
	public int getRateValue() {
		int rate = rateSlider.getValue();
		return rate;
	}
	
	// Returns the value of the pitch Slider component
	public int getPitchValue() {
		int pitch = pitchSlider.getValue();
		return pitch;
	}
	
	/* Close speech menu. An attempt to close Speech submenu before audio start to play.
		TODO:
	*/
	public void closeSpeechMenu () {
		menuSpeech.setVisible(false);
	}
	
	// Set document's details text
	public void setDocumentsDetailArea(String details) {
		docDetailsArea.setText(details);
	}
	
	// Get Document's area text
	public String getDocumentArea() {
		return textArea.getText();
	}
	
	// Get the current Document of the GUI
	public Document getCurrentDocument() {
		return currentDocument;
	}

	// Set the current Document of the GUI 
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}
	
	// Set visible the document area
	public void setDocAreaVisible() {
		scrollPane.setVisible(true);
		textArea.setVisible(true);
	}
	
	// Enable text area edit option
	public void enableDocEdit() {
		textArea.setEditable(true);
	}
	
	// Clear the document area
	public void clearTextArea() {
		textArea.setText("");
	}
	
	/**
	 * A simple pop up message
	 * @param message 
	 */
	public void popUpInformMessage(String message, String title) {
		JOptionPane.showMessageDialog(frmTextToSpeech, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	public void popUpWarningMessage(String message, String title) {
		JOptionPane.showMessageDialog(frmTextToSpeech, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	
	/**
	 * Function to show text on textArea 
	 */
	public void setTextArea(File file) throws MalformedURLException, IOException {
		this.textArea.setVisible(true);
		this.delete(file.getAbsolutePath(), 0, 4);
		textArea.setPage(file.toURI().toURL());
	}

	void delete(String filename, int startline, int numlines)
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(filename));
 
			//String buffer to store contents of the file
			StringBuffer sb=new StringBuffer("");
 
			//Keep track of the line number
			int linenumber=1;
			String line;
 
			while((line=br.readLine())!=null)
			{
				//Store each valid line in the string buffer
				if(linenumber<startline||linenumber>=startline+numlines)
					sb.append(line+"\n");
				linenumber++;
			}
			if(startline+numlines>linenumber)
				System.out.println("End of file reached.");
			br.close();
 
			FileWriter fw=new FileWriter(new File(filename));
			//Write entire string buffer into the file
			fw.write(sb.toString());
			fw.close();
		}
		catch (Exception e)
		{
			System.out.println("Something went horribly wrong: "+e.getMessage());
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextToSpeech = new JFrame();
		frmTextToSpeech.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmTextToSpeech.setLocation(dim.width/2-frmTextToSpeech.getSize().width/2, dim.height/2-frmTextToSpeech.getSize().height/2);
		frmTextToSpeech.setVisible(true);
		frmTextToSpeech.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmTextToSpeech.setBackground(Color.WHITE);
		frmTextToSpeech.setTitle("Text to Speech Editor");
		frmTextToSpeech.setFont(new Font("Ubuntu Light", Font.BOLD, 20));
		frmTextToSpeech.setBounds(500, 500, 1024, 728);
		frmTextToSpeech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToSpeech.getContentPane().setLayout(null);
		

		//Create the menu bar.
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(192, 192, 192));
		menuBar.setFont(new Font("League Spartan Semibold", Font.BOLD, 14));
		menuBar.setBackground(new Color(211, 211, 211));
		menuBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		//Build the first menu.
		menuFile = new JMenu("File");
		menuFile.setForeground(new Color(0, 0, 0));
		menuFile.setBackground(new Color(192, 192, 192));
		menuFile.setActionCommand("File\n");
		menuFile.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuFile.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menuFile);

		
		
		mntmNewFile = new JMenuItem("New Document",KeyEvent.VK_T);
		mntmNewFile.setForeground(new Color(0, 0, 0));
		mntmNewFile.setBackground(new Color(211, 211, 211));
		mntmNewFile.addActionListener(commandsfactory.createCommand("NewDocument"));
		mntmNewFile.setIcon(new ImageIcon("ImageSource/NewIcon.png"));
		mntmNewFile.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		mntmNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewFile.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuFile.add(mntmNewFile);
		
		//a group of JMenuItems
		mntmOpenFile = new JMenuItem("Load Document");
		mntmOpenFile.setMnemonic(KeyEvent.VK_CONTROL);
		mntmOpenFile.setForeground(new Color(0, 0, 0));
		mntmOpenFile.setBackground(new Color(211, 211, 211));
		mntmOpenFile.addActionListener(commandsfactory.createCommand("OpenDocument"));
		mntmOpenFile.setIcon(new ImageIcon("ImageSource/folder.png"));
		mntmOpenFile.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmOpenFile.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuFile.add(mntmOpenFile);

		removeDocument = new JMenuItem("Remove Document", new ImageIcon("ImageSource/delete.png"));
		removeDocument.addActionListener(commandsfactory.createCommand("RemoveDoc"));
		removeDocument.setForeground(new Color(0, 0, 0));
		removeDocument.setBackground(new Color(211, 211, 211));
		removeDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		removeDocument.setFont(new Font("Ubuntu Light", Font.BOLD, 16));

		
		mntmSaveFile = new JMenuItem(new ImageIcon("ImageSource/saveIcon.png"));
		mntmSaveFile.setForeground(new Color(0, 0, 0));
		mntmSaveFile.setBackground(new Color(211, 211, 211));
		mntmSaveFile.addActionListener(commandsfactory.createCommand("SaveDocument"));
		mntmSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSaveFile.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		mntmSaveFile.setText("Save Document");
		mntmSaveFile.setMnemonic(KeyEvent.VK_D);
		menuFile.add(mntmSaveFile);
		removeDocument.setMnemonic(KeyEvent.VK_B);
		menuFile.add(removeDocument);
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_4, ActionEvent.ALT_MASK));
		ButtonGroup group = new ButtonGroup();


		// SECOND TOP MENU BAR
			
		//Build second menu in the menu bar.
		menuEdit = new JMenu("Edit");
		menuEdit.setForeground(new Color(0, 0, 0));
		menuEdit.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuEdit.setMnemonic(KeyEvent.VK_N);
		menuEdit.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menuEdit);
		
		frmTextToSpeech.setJMenuBar(menuBar);
		
		
		mntmTextHighlight = new JMenuItem("Text Highlight",KeyEvent.VK_T);
		mntmTextHighlight.addActionListener(commandsfactory.createCommand("TextHighlight"));
		mntmTextHighlight.setBackground(new Color(211, 211, 211));
		mntmTextHighlight.setForeground(new Color(0, 0, 0));
		mntmTextHighlight.setIcon(null);
		mntmTextHighlight.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		mntmTextHighlight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmTextHighlight.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		
		editMenuItem = new JMenuItem("Edit Document");
		editMenuItem.setBackground(new Color(211, 211, 211));
		editMenuItem.setForeground(new Color(0, 0, 0));
		editMenuItem.addActionListener(commandsfactory.createCommand("EditDocument"));
		editMenuItem.setIcon(new ImageIcon("ImageSource/edit.png"));
		editMenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuEdit.add(editMenuItem);
		menuEdit.add(mntmTextHighlight);
		
		
		
		//Build the third menu.
		menuSpeech = new JMenu("Speech");
		menuSpeech.setForeground(new Color(0, 0, 0));
		menuSpeech.setActionCommand("Speech\n");
		menuSpeech.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuSpeech.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuSpeech.setMnemonic(KeyEvent.VK_5);
		menuSpeech.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menuSpeech);
		
		text2speech_MenuItem = new JMenuItem("Text to speech");
		text2speech_MenuItem.addActionListener(commandsfactory.createCommand("TextToSpeech"));
		text2speech_MenuItem.setBackground(new Color(211, 211, 211));
		text2speech_MenuItem.setForeground(new Color(0, 0, 0));
		text2speech_MenuItem.setIcon(new ImageIcon("ImageSource/speech.png"));
		text2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
		text2speech_MenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSpeech.add(text2speech_MenuItem);
		
		highlight2speech_MenuItem = new JMenuItem("Highlighted text to speech");
		highlight2speech_MenuItem.addActionListener(commandsfactory.createCommand("HighlightTextToSpeech"));
		highlight2speech_MenuItem.setBackground(new Color(211, 211, 211));
		highlight2speech_MenuItem.setForeground(new Color(0, 0, 0));
		highlight2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
		highlight2speech_MenuItem.setIcon(null);
		highlight2speech_MenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSpeech.add(highlight2speech_MenuItem);
		
		menuSpeech.addSeparator();
		
		reverseText2speech_MenuItem = new JMenuItem("Reverse text to speech");
		reverseText2speech_MenuItem.addActionListener(commandsfactory.createCommand("ReversedTextToSpeech"));
		reverseText2speech_MenuItem.setBackground(new Color(211, 211, 211));
		reverseText2speech_MenuItem.setForeground(new Color(0, 0, 0));
		reverseText2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		reverseText2speech_MenuItem.setIcon(new ImageIcon("ImageSource/reversespeech.png"));
		reverseText2speech_MenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSpeech.add(reverseText2speech_MenuItem);
		
		reverseLine2SpeechMenuItem = new JMenuItem("Reverse line to speech");
		reverseLine2SpeechMenuItem.addActionListener(commandsfactory.createCommand("ReversedLineToSpeech"));
		reverseLine2SpeechMenuItem.setBackground(new Color(211, 211, 211));
		reverseLine2SpeechMenuItem.setForeground(new Color(0, 0, 0));
		reverseLine2SpeechMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
		reverseLine2SpeechMenuItem.setIcon(null);
		reverseLine2SpeechMenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSpeech.add(reverseLine2SpeechMenuItem);
		
		menuSpeech.addSeparator();
		
		textEncode_MenuItem = new JMenuItem("Encoded text to speech");
		textEncode_MenuItem.addActionListener(commandsfactory.createCommand("EncodeDocument"));
		textEncode_MenuItem.setIcon(new ImageIcon("ImageSource/encodineAudio.png"));
		textEncode_MenuItem.setForeground(Color.BLACK);
		textEncode_MenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		textEncode_MenuItem.setBackground(new Color(211, 211, 211));
		menuSpeech.add(textEncode_MenuItem);
		
		encodedLine = new JMenuItem("Encoded line to speech");
		encodedLine.addActionListener(commandsfactory.createCommand("EncodeLine"));
		encodedLine.setForeground(Color.BLACK);
		encodedLine.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		encodedLine.setBackground(new Color(211, 211, 211));
		menuSpeech.add(encodedLine);
		
		
		// BUILD THE 4TH TOP BAR MENU ELEMENT
		
		//Build the fourth menu.
		menuEncoding = new JMenu("Encoding");
		menuEncoding.setForeground(new Color(0, 0, 0));
		menuEncoding.setActionCommand("Encoding\n");
		menuEncoding.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuEncoding.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuEncoding.setMnemonic(KeyEvent.VK_5);
		menuEncoding.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menuEncoding);
		encodingGroup = new ButtonGroup();
		
		atbshaMenuItem = new JRadioButtonMenuItem("Atbash encoding");
		atbshaMenuItem.addActionListener(commandsfactory.createCommand("AtBash"));
		atbshaMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		atbshaMenuItem.setIcon(new ImageIcon("ImageSource/atbash.png"));
		atbshaMenuItem.setBackground(new Color(211, 211, 211));
		atbshaMenuItem.setForeground(new Color(0, 0, 0));
		atbshaMenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		atbshaMenuItem.setSelected(true);
		atbshaMenuItem.setMnemonic(KeyEvent.VK_R);
		encodingGroup.add(atbshaMenuItem);
		menuEncoding.add(atbshaMenuItem);

		rot13MenuItem = new JRadioButtonMenuItem("Rot-13 encoding");
		rot13MenuItem.addActionListener(commandsfactory.createCommand("Rot13"));
		rot13MenuItem.setIcon(new ImageIcon("ImageSource/rot13.jpg"));
		rot13MenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		rot13MenuItem.setBackground(new Color(211, 211, 211));
		rot13MenuItem.setForeground(new Color(0, 0, 0));
		rot13MenuItem.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		rot13MenuItem.setMnemonic(KeyEvent.VK_O);
		encodingGroup.add(rot13MenuItem);
		menuEncoding.add(rot13MenuItem);
		
		
		// BUILD THE SETTINGS TOP BAR MENU ELEMENT
		
		menuSettings = new JMenu("Settings");
		menuSettings.setHorizontalAlignment(SwingConstants.CENTER);
		menuSettings.setForeground(new Color(0, 0, 0));
		menuSettings.setActionCommand("Settings\n");
		menuSettings.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuSettings.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuSettings.setMnemonic(KeyEvent.VK_5);
		menuSettings.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menuSettings);
		
		volumeSubmenu = new JMenu("Volume");
		volumeSubmenu.setBackground(new Color(211, 211, 211));
		volumeSubmenu.setForeground(new Color(0, 0, 0));
		volumeSubmenu.setIcon(new ImageIcon("ImageSource/volume.png"));
		volumeSubmenu.setToolTipText("Adjust the volume");
		volumeSubmenu.setMnemonic(KeyEvent.VK_S);
		volumeSubmenu.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSettings.add(volumeSubmenu);
		
		mntmSetVolume = new JMenuItem("Set Volume");
		mntmSetVolume.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmSetVolume.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSetVolume.addActionListener(commandsfactory.createCommand("VolumeSettings"));
		mntmSetVolume.setBackground(new Color(211, 211, 211));
		mntmSetVolume.setForeground(new Color(0, 0, 0));
		mntmSetVolume.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		volumeSubmenu.add(mntmSetVolume);
		
		volumeSubmenu.addSeparator();
		
		volumeSlider = new JSlider(0, 100, 100);
		volumeSlider.setBackground(new Color(211, 211, 211));
		volumeSlider.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
		volumeSlider.setForeground(new Color(0, 0, 0));
		volumeSlider.setPreferredSize(new Dimension(160, 50));
		volumeSlider.setPaintTrack(true);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setMinorTickSpacing(5);
		volumeSlider.setMaximumSize(new Dimension(3276, 16));
		volumeSlider.setMajorTickSpacing(50);
		volumeSubmenu.add(volumeSlider);
		
		rateSubmenu = new JMenu("Ratio");
		rateSubmenu.setBackground(new Color(211, 211, 211));
		rateSubmenu.setForeground(new Color(0, 0, 0));
		rateSubmenu.setIcon(new ImageIcon("ImageSource/ratio.png"));
		rateSubmenu.setMnemonic(KeyEvent.VK_S);
		rateSubmenu.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSettings.add(rateSubmenu);
		
		mntmSetRate = new JMenuItem("Set Ratio");
		mntmSetRate.addActionListener(commandsfactory.createCommand("RatioSettings"));
		mntmSetRate.setBackground(new Color(211, 211, 211));
		mntmSetRate.setForeground(new Color(0, 0, 0));
		mntmSetRate.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		rateSubmenu.add(mntmSetRate);
		
		rateSubmenu.addSeparator();
		rateSlider = new JSlider(60, 300, 185);
		rateSlider.setBackground(new Color(211, 211, 211));
		rateSlider.setForeground(new Color(0, 0, 0));
		rateSlider.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
		rateSlider.setToolTipText("Adjust rate level");
		rateSlider.setPreferredSize(new Dimension(260, 60));
		rateSlider.setPaintTrack(true);
		rateSlider.setPaintTicks(true);
		rateSlider.setPaintLabels(true);
		rateSlider.setMinorTickSpacing(5);
		rateSlider.setMaximumSize(new Dimension(3276, 16));
		rateSlider.setMajorTickSpacing(50);
		rateSubmenu.add(rateSlider);
		
		pitchSubmenu = new JMenu("Voice Pitch");
		pitchSubmenu.setBackground(new Color(211, 211, 211));
		pitchSubmenu.setForeground(new Color(0, 0, 0));
		pitchSubmenu.setIcon(new ImageIcon("ImageSource/pitch.png"));
		pitchSubmenu.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		menuSettings.add(pitchSubmenu);
		
		mntmSetPitch = new JMenuItem("Set Pitch");
		mntmSetPitch.addActionListener(commandsfactory.createCommand("PitchSettings"));
		mntmSetPitch.setBackground(new Color(211, 211, 211));
		mntmSetPitch.setForeground(new Color(0, 0, 0));
		mntmSetPitch.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		pitchSubmenu.add(mntmSetPitch);
		
		separator = new JSeparator();
		pitchSubmenu.add(separator);
		
		pitchSlider = new JSlider(50, 300, 100);
		pitchSlider.setBackground(new Color(211, 211, 211));
		pitchSlider.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
		pitchSlider.setForeground(new Color(0, 0, 0));
		pitchSlider.setToolTipText("Adjust pitch level");
		pitchSlider.setPreferredSize(new Dimension(300, 60));
		pitchSlider.setPaintTrack(true);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setPaintLabels(true);
		pitchSlider.setMinorTickSpacing(5);
		pitchSlider.setMaximumSize(new Dimension(3276, 16));
		pitchSlider.setMajorTickSpacing(50);
		pitchSubmenu.add(pitchSlider);
		
		helpMenu = new JMenu("Help");
		helpMenu.setIcon(new ImageIcon("ImageSource/help.png"));
		helpMenu.setMnemonic(KeyEvent.VK_F1);
		helpMenu.setForeground(Color.BLACK);
		helpMenu.setFont(new Font("Ubuntu", Font.PLAIN, 19));
		menuBar.add(helpMenu);
		
		tips = new JMenuItem("Tips", KeyEvent.VK_T);
		tips.addActionListener(commandsfactory.createCommand("Tips"));
		tips.setIcon(new ImageIcon("ImageSource/tips.png"));
		tips.setForeground(Color.BLACK);
		tips.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		tips.setBackground(new Color(211, 211, 211));
		helpMenu.add(tips);
		
		mntmTextHighlight_1 = new JMenuItem("Information", KeyEvent.VK_T);
		mntmTextHighlight_1.setIcon(new ImageIcon("ImageSource/info.png"));
		mntmTextHighlight_1.setForeground(Color.BLACK);
		mntmTextHighlight_1.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
		mntmTextHighlight_1.setBackground(new Color(211, 211, 211));
		helpMenu.add(mntmTextHighlight_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(58, 186, 911, 449);
		frmTextToSpeech.getContentPane().add(scrollPane);
		
		textArea = new JEditorPane();
		textArea.setCaretColor(new Color(255, 0, 0));
		textArea.setBackground(new Color(245, 245, 245));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setSelectedTextColor(new Color(119, 136, 153));
		textArea.setVisible(false);
		textArea.setFont(new Font("Ubuntu", Font.BOLD, 16));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		docDetailsArea = new JTextField();
		docDetailsArea.setHorizontalAlignment(SwingConstants.CENTER);
		docDetailsArea.setBackground(new Color(176, 196, 222));
		docDetailsArea.setEditable(false);
		docDetailsArea.setForeground(new Color(25, 25, 112));
		docDetailsArea.setFont(new Font("Ubuntu Light", Font.BOLD, 18));
		scrollPane.setColumnHeaderView(docDetailsArea);
		docDetailsArea.setColumns(10);
		
		internalFrame = new JInternalFrame("Tips");
		internalFrame.setClosable(true);
		internalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrame.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		internalFrame.setFrameIcon(new ImageIcon("ImageSource/tips.png"));
		internalFrame.setBounds(308, 6, 399, 167);
		frmTextToSpeech.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel tips_background = new JLabel("");
		tips_background.setIcon(new ImageIcon("/home/vaggelisbarb/Επιφάνεια εργασίας/Untitled.png"));
		tips_background.setBounds(0, 0, 393, 139);
		internalFrame.getContentPane().add(tips_background);

		
		JLabel appBackground = new JLabel("");
		appBackground.setIcon(new ImageIcon("ImageSource/new.png"));
		appBackground.setBounds(0, -32, 1046, 718);
		frmTextToSpeech.getContentPane().add(appBackground);
	}
}
