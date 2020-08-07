/****************************************************
 * LOAD MENU
 * the menu where you look for an xml file and a vido
 * file, and then use the info in the xml file to snip
 * and stitch the video file.
 ****************************************************/

package snipstitch;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Loadermenu {
	
	JLabel chosenXml = new JLabel("No XML selected");
	String xmlFilepath = new String("NONE");
	JLabel chosenVideo = new JLabel("No Video Selected");
	String videoFilepath = new String("NONE");
	JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	Editor editor;
	JRadioButton mp4Radio;
	JRadioButton movRadio;
	JRadioButton wmvRadio;
	JRadioButton flvRadio;
	JRadioButton aviRadio;
	JRadioButton webmRadio;
	JRadioButton mkvRadio;
	JFormattedTextField ffmpegInput;
	JFormattedTextField fileInput;
	JLabel vidNameLabel;
	JLabel statusLabel;
	
	ButtonGroup fileTypes = new ButtonGroup();
	public Loadermenu() {
		//the main frame
		JFrame frame = new JFrame("Snip-Stitch");
		frame.setBounds(100, 100, 450, 523);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//the panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//find the video file
		JButton loadVideo = new JButton("Video");
		loadVideo.setBounds(10, 11, 100, 30);
		loadVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fileChooser
				// invoke the showsSaveDialog function to show the save dialog 
	            int r = fileChooser.showOpenDialog(null);
	            
	            if (r == JFileChooser.APPROVE_OPTION) { 
	                // set the label to the path of the selected directory
	            	videoFilepath = fileChooser.getSelectedFile().getAbsolutePath();
	                chosenVideo.setText(videoFilepath); 
	            } 
	            // if the user cancelled the operation 
	            else {}
	                //chosenVideo.setText("the user cancelled the operation"); 
			}
		});
		panel.add(loadVideo);

		//find xml button
		JButton findXml = new JButton("XML");
		findXml.setBounds(10, 52, 100, 30);
		findXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fileChooser
				
				// invoke the showsSaveDialog function to show the save dialog 
	            int r = fileChooser.showOpenDialog(null);
	            
	            if (r == JFileChooser.APPROVE_OPTION) { 
	                // set the label to the path of the selected directory 
	                xmlFilepath = fileChooser.getSelectedFile().getAbsolutePath();
	                chosenXml.setText(xmlFilepath);
	            	
	            } 
	            // if the user cancelled the operation 
	            else {}
	                //chosenXml.setText("the user cancelled the operation"); 
			}
		});
		panel.add(findXml);
				
		//Edit Video!
		JButton editVideo = new JButton("Edit!");
		editVideo.setBounds(10, 414, 100, 30);
		editVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snipAndStitch();
			}
		});
		panel.add(editVideo);
		
		//the button to go back to the main menu
		JButton goBack = new JButton("Back");
		goBack.setBounds(324, 414, 100, 30);
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainMenu mainMenu = new MainMenu();
			}
		});
		panel.add(goBack);
		
		//displays chosen video file
		chosenVideo.setBounds(146, 19, 301, 14);
		panel.add(chosenVideo);
				
		//displays the chosen XML file
		chosenXml.setBounds(146, 60, 301, 14);
		panel.add(chosenXml);
		
		//chooses a filetype
		JLabel lblNewLabel_1 = new JLabel("Path to ffmpeg");
		lblNewLabel_1.setBounds(10, 358, 262, 14);
		panel.add(lblNewLabel_1);
		
		//choose non-default location for ffmpeg
		ffmpegInput = new JFormattedTextField();
		ffmpegInput.setBounds(10, 383, 398, 20);
		ffmpegInput.setText("ffmpeg");
		panel.add(ffmpegInput);
		
		JLabel fileTypeTag = new JLabel("Output File Type:");
		fileTypeTag.setBounds(10, 151, 100, 14);
		panel.add(fileTypeTag);
		
		mp4Radio = new JRadioButton("mp4");
		mp4Radio.setSelected(true);
		mp4Radio.setBounds(10, 172, 201, 23);
		panel.add(mp4Radio);
		
		movRadio = new JRadioButton("mov");
		movRadio.setBounds(10, 198, 201, 23);
		panel.add(movRadio);
		
		wmvRadio = new JRadioButton("wmv");
		wmvRadio.setBounds(10, 224, 201, 23);
		panel.add(wmvRadio);
		
		flvRadio = new JRadioButton("flv");
		flvRadio.setBounds(10, 250, 201, 23);
		panel.add(flvRadio);
		
		aviRadio = new JRadioButton("avi");
		aviRadio.setBounds(10, 276, 201, 23);
		panel.add(aviRadio);
		
		webmRadio = new JRadioButton("webm");
		webmRadio.setBounds(10, 302, 201, 23);
		panel.add(webmRadio);
		
		mkvRadio = new JRadioButton("mkv");
		mkvRadio.setBounds(10, 328, 201, 23);
		panel.add(mkvRadio);
		
		//ButtonGroup fileTypes = new ButtonGroup();
		fileTypes.add(mp4Radio);
		fileTypes.add(movRadio);
		fileTypes.add(wmvRadio);
		fileTypes.add(flvRadio);
		fileTypes.add(aviRadio);
		fileTypes.add(webmRadio);
		fileTypes.add(mkvRadio);
		
		vidNameLabel = new JLabel("Name the new Video");
		vidNameLabel.setBounds(10, 95, 118, 14);
		panel.add(vidNameLabel);
		
		//the name of the new file that will be created
		fileInput = new JFormattedTextField();
		fileInput.setBounds(10, 120, 398, 20);
		fileInput.setText("newVideo");
		panel.add(fileInput);
		fileInput.setColumns(10);
		
        statusLabel = new JLabel("Ready...");
        statusLabel.setBounds(20, 455, 404, 14);
        panel.add(statusLabel);
	}
	
	//methods
	
	String getFiletype() {
		String output = new String();
		
		if(mp4Radio.isSelected()) {
			output = ".mp4";
		}
		
		else if (movRadio.isSelected()) {
			output = ".mov";
		}
		
		else if (wmvRadio.isSelected()) {
			output = ".wmv";
		}
		
		else if (flvRadio.isSelected()) {
			output = ".flv";
		}
		
		else if (aviRadio.isSelected()) {
			output = ".avi";
		}
		
		else if (webmRadio.isSelected()) {
			output = ".webm";
		}
		
		else if (mkvRadio.isSelected()) {
			output = ".mkv";
		}
		
		return output;
	}
	
	String getFfmpegPath() {
		return ffmpegInput.getText();
	}
	
	String getNewVidName() {
		return fileInput.getText();
	}
	
	private void snipAndStitch() {
		SwingWorker<Void, String> worker = new SwingWorker<Void, String> () {

			@Override
			protected Void doInBackground() throws Exception {
				
				//UploadDisplay snippetD = new UploadDisplay("Loading Snippets");
				editor = new Editor(xmlFilepath, videoFilepath, getFiletype(), getFfmpegPath(), getNewVidName());
				//load the snippets
				publish("Uploading Snippets from XML...");
				try {
					editor.uploadSnippets();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//UploadDisplay snipD = new UploadDisplay("Loading...\nThis could take several minutes");
				//snip out the clips from the original video
				publish("Snipping the Source Video (this takes the longest)...");
				try {
					editor.snip();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//stitch the snipped slips back together
				publish("Stitching.... If you're stuck here check for a duplicate file.");
				try {
					editor.stitch();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//delete unwanted files
				publish("Cleaning up unwanted files...");
				editor.cleanup();
				//UploadDisplay snipF = new UploadDisplay("Finished!");
				publish("All Done! :)");
				return null;
			}

			@Override
			protected void process(List<String> chunks) {
				// TODO Auto-generated method stub
				//super.process(chunks);
				statusLabel.setText(chunks.get(chunks.size() - 1));
			}
		};
		worker.execute();
	}
	
}