/****************************************************
 * LOAD MENU
 * the menu where you look for an xml file and a vido
 * file, and then use the info in the xml file to snip
 * and stitch the video file.
 ****************************************************/

package snipstitch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public Loadermenu() {
		//the main frame
		JFrame frame = new JFrame("Snip-Stitch");
		frame.setBounds(100, 100, 487, 186);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//the panel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//find the video file
		JButton loadVideo = new JButton("Find Video");
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
		JButton findXml = new JButton("Find XML");
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
		JButton editVideo = new JButton("Edit Video");
		editVideo.setBounds(10, 93, 100, 30);
		editVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor = new Editor(xmlFilepath, videoFilepath);
				//load the snippets
				try {
					editor.uploadSnippets();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//snip out the clips from the original video
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
				editor.cleanup();
			}
		});
		panel.add(editVideo);
		
		//the button to go back to the main menu
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(146, 93, 100, 30);
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Mainmenu mainMenu = new Mainmenu();
			}
		});
		panel.add(goBack);
		
		//displays chosen video file
		chosenVideo.setBounds(146, 19, 301, 14);
		panel.add(chosenVideo);
				
		//displays the chosen xml file
		chosenXml.setBounds(146, 60, 301, 14);
		panel.add(chosenXml);
	}
}