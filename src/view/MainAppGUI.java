package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import model.Document;
import commands.CommandsFactory;
import commands.OpenDocument;

import java.awt.event.InputEvent;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class MainAppGUI {

	private JFrame frmTextToSpeech;
	JMenuBar menuBar;
	JMenu menuFile;
	private JMenu menuEdit;
	JMenuItem menuItem;
	private JMenuItem mntmNewFile;
	private JMenuItem mntmSaveFile;
	private JMenuItem mntmSaveFileAs;
	private JMenuItem mntmOpenFile;
	JCheckBoxMenuItem cbMenuItem;
	private JCheckBoxMenuItem cbMenuItem_1;
	
	private JMenu menuSpeech;
	private JMenuItem mntmTextHighlight; 
	private JMenuItem reverseText2speech_MenuItem;
	private JMenuItem reverseHighlight2speech_MenuItem_1;
	
	private JMenu menuEncoding; 
	private JMenuItem textEncode_MenuItem_1;
	private JMenuItem text2speech_MenuItem_2;
	private JRadioButtonMenuItem atbshaMenuItem;
	private JRadioButtonMenuItem rot13MenuItem;
	
	 private JMenu menuSettings;
	 private JMenu volumeSubmenu;
	 private JMenuItem mntmDefaultVolume;
	 private JSlider volumeSlider;
	 private JMenu rateSubmenu;
	 private JSlider rateSlider;
	 private JMenuItem mntmDefaultRate;
	 private JMenu pitchSubmenu;
	 private JMenuItem mntmDefaultPitch;
	 private JSlider pitchSlider;
	
	 private JMenuItem editMenuItem;
	 
	 
	 // Initializations
	 private CommandsFactory commandsfactory;
	 private Document currentDocument;
	 private JEditorPane textArea;
	 private JScrollPane scrollPane;
	 
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
		currentDocument = new Document();
		currentDocument = null;
		
		initialize();
	}
	
	
	public void enableDocEdit() {
		this.textArea.setEditable(true);
	}
	
	/**
	 * A simple pop up message
	 * @param message 
	 */
	public void popUpMessage(String message) {
		JOptionPane.showMessageDialog(frmTextToSpeech, message);
	}
	
	
	/**
	 * Function to show text on textArea 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public void setTextArea(File file) throws MalformedURLException, IOException {
		this.textArea.setVisible(true);
		textArea.setPage(file.toURI().toURL());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextToSpeech = new JFrame();
		frmTextToSpeech.setResizable(false);
		frmTextToSpeech.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		//frmTextToSpeech.setExtendedState(frmTextToSpeech.MAXIMIZED_BOTH);
		frmTextToSpeech.setVisible(true);
		frmTextToSpeech.setBackground(Color.WHITE);
		frmTextToSpeech.setTitle("Text to Speech Editor");
		frmTextToSpeech.setFont(new Font("League Spartan Semibold", Font.PLAIN, 17));
		frmTextToSpeech.setBounds(500, 500, 900, 602);
		frmTextToSpeech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToSpeech.getContentPane().setLayout(null);
		

		//Create the menu bar.
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("League Spartan Semibold", Font.BOLD, 14));
		menuBar.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		menuBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		//Build the first menu.
		menuFile = new JMenu("File");
		menuFile.setToolTipText("Choose a file option");
		menuFile.setActionCommand("File\n");
		menuFile.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		menuFile.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menuFile);

		
		
		mntmNewFile = new JMenuItem("New Document",KeyEvent.VK_T);
		mntmNewFile.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/add.png"));
		mntmNewFile.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		mntmNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewFile.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuFile.add(mntmNewFile);
		
		//a group of JMenuItems
		mntmOpenFile = new JMenuItem("Load Document");
		mntmOpenFile.addActionListener(commandsfactory.createCommand("OpenDocument"));
		mntmOpenFile.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/file.png"));
		mntmOpenFile.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmOpenFile.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuFile.add(mntmOpenFile);

		mntmSaveFileAs = new JMenuItem("Save Document as",
		                         new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/icons8-save-as-24.png"));
		mntmSaveFileAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmSaveFileAs.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.ALT_MASK));
		mntmSaveFileAs.setMnemonic(KeyEvent.VK_B);
		menuFile.add(mntmSaveFileAs);

		mntmSaveFile = new JMenuItem(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/icons8-save-24.png"));
		mntmSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSaveFile.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		mntmSaveFile.setText("Save Document");
		mntmOpenFile.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_4, ActionEvent.ALT_MASK));
		mntmSaveFile.setMnemonic(KeyEvent.VK_D);
		menuFile.add(mntmSaveFile);
		ButtonGroup group = new ButtonGroup();

		//a group of check box menu items
		menuFile.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menuFile.add(cbMenuItem);

		cbMenuItem_1 = new JCheckBoxMenuItem("Another one");
		cbMenuItem_1.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		cbMenuItem_1.setMnemonic(KeyEvent.VK_H);
		menuFile.add(cbMenuItem_1);

		// SECOND TOP MENU BAR
			
		//Build second menu in the menu bar.
		menuEdit = new JMenu("Edit");
		menuEdit.setToolTipText("Select tools");
		menuEdit.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		menuEdit.setMnemonic(KeyEvent.VK_N);
		menuEdit.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menuEdit);
		
		frmTextToSpeech.setJMenuBar(menuBar);
		
		
		mntmTextHighlight = new JMenuItem("Text Highlight",KeyEvent.VK_T);
		mntmTextHighlight.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/textHigh.png"));
		mntmTextHighlight.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		mntmTextHighlight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmTextHighlight.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		
		editMenuItem = new JMenuItem("Edit Document");
		editMenuItem.addActionListener(commandsfactory.createCommand("EditDocument"));
		editMenuItem.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/edit.png"));
		editMenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuEdit.add(editMenuItem);
		menuEdit.add(mntmTextHighlight);
		
		
		
		//Build the third menu.
		menuSpeech = new JMenu("Speech");
		menuSpeech.setToolTipText("Transform into audio");
		menuSpeech.setActionCommand("Speech\n");
		menuSpeech.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		menuSpeech.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuSpeech.setMnemonic(KeyEvent.VK_5);
		menuSpeech.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menuSpeech);
		
		JMenuItem text2speech_MenuItem = new JMenuItem("Text to speech");
		text2speech_MenuItem.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/textSpeech.png"));
		text2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
		text2speech_MenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSpeech.add(text2speech_MenuItem);
		
		JMenuItem highlight2speech_MenuItem = new JMenuItem("Highlighted text to speech");
		highlight2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
		highlight2speech_MenuItem.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/HighlightSpeech.png"));
		highlight2speech_MenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSpeech.add(highlight2speech_MenuItem);
		
		menuSpeech.addSeparator();
		
		reverseText2speech_MenuItem = new JMenuItem("Reverse text to speech");
		reverseText2speech_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		reverseText2speech_MenuItem.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/reverseText.png"));
		reverseText2speech_MenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSpeech.add(reverseText2speech_MenuItem);
		
		reverseHighlight2speech_MenuItem_1 = new JMenuItem("Reverse highlighted text to speech");
		reverseHighlight2speech_MenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
		reverseHighlight2speech_MenuItem_1.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/reverseHighlighted.png"));
		reverseHighlight2speech_MenuItem_1.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSpeech.add(reverseHighlight2speech_MenuItem_1);
		
		
		// BUILD THE 4TH TOP BAR MENU ELEMENT
		
		//Build the fourth menu.
		menuEncoding = new JMenu("Encoding");
		menuEncoding.setToolTipText("Select the encoding style");
		menuEncoding.setActionCommand("Encoding\n");
		menuEncoding.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		menuEncoding.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuEncoding.setMnemonic(KeyEvent.VK_5);
		menuEncoding.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menuEncoding);
		
		textEncode_MenuItem_1 = new JMenuItem("Text encode");
		textEncode_MenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK));
		textEncode_MenuItem_1.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/textEncode.png"));
		textEncode_MenuItem_1.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuEncoding.add(textEncode_MenuItem_1);
		
		text2speech_MenuItem_2 = new JMenuItem("Highlighted text encode");
		text2speech_MenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.SHIFT_MASK));
		text2speech_MenuItem_2.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/highlightedTextEncode.png"));
		text2speech_MenuItem_2.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuEncoding.add(text2speech_MenuItem_2);
		
		
		menuEncoding.addSeparator();
		ButtonGroup encodingGroup = new ButtonGroup();
		atbshaMenuItem = new JRadioButtonMenuItem("Atbash encoding");
		atbshaMenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		atbshaMenuItem.setSelected(true);
		atbshaMenuItem.setMnemonic(KeyEvent.VK_R);
		encodingGroup.add(atbshaMenuItem);
		menuEncoding.add(atbshaMenuItem);

		rot13MenuItem = new JRadioButtonMenuItem("Rot-13 encoding");
		rot13MenuItem.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		rot13MenuItem.setMnemonic(KeyEvent.VK_O);
		encodingGroup.add(rot13MenuItem);
		menuEncoding.add(rot13MenuItem);
		
		
		// BUILD THE SEETINGS TOP BAR MENU ELEMENT
		
		menuSettings = new JMenu("Settings");
		menuSettings.setActionCommand("Settings\n");
		menuSettings.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		menuSettings.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuSettings.setMnemonic(KeyEvent.VK_5);
		menuSettings.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menuSettings);
		
		volumeSubmenu = new JMenu("Speech Volume");
		volumeSubmenu.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/volumeAdjust.png"));
		volumeSubmenu.setToolTipText("Adjust the volume");
		volumeSubmenu.setMnemonic(KeyEvent.VK_S);
		volumeSubmenu.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSettings.add(volumeSubmenu);
		mntmDefaultVolume = new JMenuItem("Default Volume");
		mntmDefaultVolume.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		volumeSubmenu.add(mntmDefaultVolume);
		
		volumeSubmenu.addSeparator();
		
		volumeSlider = new JSlider(0, 200, 100);
		volumeSlider.setToolTipText("Adjust volume level");
		volumeSlider.setPreferredSize(new Dimension(160, 50));
		volumeSlider.setPaintTrack(true);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setMinorTickSpacing(5);
		volumeSlider.setMaximumSize(new Dimension(3276, 16));
		volumeSlider.setMajorTickSpacing(50);
		volumeSubmenu.add(volumeSlider);
		
		rateSubmenu = new JMenu("Speech Rate");
		rateSubmenu.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/RateAdjust.png"));
		rateSubmenu.setToolTipText("Adjust the rate");
		rateSubmenu.setMnemonic(KeyEvent.VK_S);
		rateSubmenu.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSettings.add(rateSubmenu);
		
		mntmDefaultRate = new JMenuItem("Default Rate");
		mntmDefaultRate.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		rateSubmenu.add(mntmDefaultRate);
		
		rateSubmenu.addSeparator();
		rateSlider = new JSlider(0, 200, 100);
		rateSlider.setToolTipText("Adjust rate level");
		rateSlider.setPreferredSize(new Dimension(160, 50));
		rateSlider.setPaintTrack(true);
		rateSlider.setPaintTicks(true);
		rateSlider.setPaintLabels(true);
		rateSlider.setMinorTickSpacing(5);
		rateSlider.setMaximumSize(new Dimension(3276, 16));
		rateSlider.setMajorTickSpacing(50);
		rateSubmenu.add(rateSlider);
		
		pitchSubmenu = new JMenu("Speech Pitch");
		pitchSubmenu.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/pitchAdjust.png"));
		pitchSubmenu.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		menuSettings.add(pitchSubmenu);
		
		mntmDefaultPitch = new JMenuItem("Default Pitch");
		mntmDefaultPitch.setFont(new Font("League Spartan Semibold", Font.PLAIN, 15));
		pitchSubmenu.add(mntmDefaultPitch);
		
		pitchSlider = new JSlider(0, 200, 100);
		pitchSlider.setToolTipText("Adjust pitch level");
		pitchSlider.setPreferredSize(new Dimension(160, 50));
		pitchSlider.setPaintTrack(true);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setPaintLabels(true);
		pitchSlider.setMinorTickSpacing(5);
		pitchSlider.setMaximumSize(new Dimension(3276, 16));
		pitchSlider.setMajorTickSpacing(50);
		pitchSubmenu.add(pitchSlider);
		
		JLabel applicationLabel = new JLabel("");
		applicationLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		applicationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		applicationLabel.setIcon(new ImageIcon("/home/vaggelisbarb/Εικόνες/text2speechLogo.png"));
		applicationLabel.setBounds(307, 12, 285, 122);
		frmTextToSpeech.getContentPane().add(applicationLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 178, 807, 334);
		frmTextToSpeech.getContentPane().add(scrollPane);
		
		textArea = new JEditorPane();
		textArea.setFont(new Font("League Spartan Semibold", Font.PLAIN, 16));
		textArea.setVisible(false);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel appBackground = new JLabel("");
		appBackground.setIcon(new ImageIcon("/home/vaggelisbarb/eclipse-workspace/Text2SpeechEditor/ImageSource/AI-Programming.png"));
		appBackground.setBounds(-45, -24, 945, 600);
		frmTextToSpeech.getContentPane().add(appBackground);
	}
}
