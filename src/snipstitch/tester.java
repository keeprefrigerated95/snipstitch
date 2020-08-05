package snipstitch;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class tester {

	private JFrame frame;
	private JTextField vidInputFeild;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tester window = new tester();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//find the video file
		JButton loadVideo = new JButton("Find Video");
		loadVideo.setBounds(10, 11, 100, 30);
		panel.add(loadVideo);

		//find xml button
		JButton findXml = new JButton("Find XML");
		findXml.setBounds(10, 52, 100, 30);
		panel.add(findXml);
				
		//Edit Video!
		JButton editVideo = new JButton("Edit Video");
		editVideo.setBounds(10, 414, 100, 30);
		panel.add(editVideo);
		
		//the button to go back to the main menu
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(324, 414, 100, 30);
		panel.add(goBack);
		
		//displays chosen video file
		JLabel chosenVideo = new JLabel("No video chosen");
		chosenVideo.setBounds(146, 19, 301, 14);
		panel.add(chosenVideo);
				
		//displays the chosen xml file
		JLabel chosenXml = new JLabel("No XML chosen");
		chosenXml.setBounds(146, 60, 301, 14);
		panel.add(chosenXml);
		
		JLabel lblNewLabel_1 = new JLabel("Non-Default ffmpeg directory (probably not needed)");
		lblNewLabel_1.setBounds(10, 358, 262, 14);
		panel.add(lblNewLabel_1);
		
		JLabel fileTypeTag = new JLabel("Output File Type:");
		fileTypeTag.setBounds(10, 151, 100, 14);
		panel.add(fileTypeTag);
		
		JFormattedTextField ffmpegInput = new JFormattedTextField();
		ffmpegInput.setBounds(10, 383, 398, 20);
		panel.add(ffmpegInput);
		
		JRadioButton mp4Radio = new JRadioButton("mp4");
		mp4Radio.setSelected(true);
		mp4Radio.setBounds(10, 172, 201, 23);
		panel.add(mp4Radio);
		
		JRadioButton movRadio = new JRadioButton("mov");
		movRadio.setBounds(10, 198, 201, 23);
		panel.add(movRadio);
		
		JRadioButton wmvRadio = new JRadioButton("wmv");
		wmvRadio.setBounds(10, 224, 201, 23);
		panel.add(wmvRadio);
		
		JRadioButton flvRadio = new JRadioButton("flv");
		flvRadio.setBounds(10, 250, 201, 23);
		panel.add(flvRadio);
		
		JRadioButton aviRadio = new JRadioButton("avi");
		aviRadio.setBounds(10, 276, 201, 23);
		panel.add(aviRadio);
		
		JRadioButton webmRadio = new JRadioButton("webm");
		webmRadio.setBounds(10, 302, 201, 23);
		panel.add(webmRadio);
		
		JRadioButton mkvRadio = new JRadioButton("mkv");
		mkvRadio.setBounds(10, 328, 201, 23);
		panel.add(mkvRadio);
		
		JLabel vidNameLabel = new JLabel("New Video Name");
		vidNameLabel.setBounds(10, 95, 118, 14);
		panel.add(vidNameLabel);
		
		vidInputFeild = new JTextField();
		vidInputFeild.setBounds(10, 120, 398, 20);
		panel.add(vidInputFeild);
		vidInputFeild.setColumns(10);
		
	}
}
