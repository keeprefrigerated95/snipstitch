/****************************************************
 * LOAD MENU
 * the menu where you look for an xml file and a vido
 * file, and then use the info in the xml file to snip
 * and stitch the video file.
 ****************************************************/

package snipstitch;

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
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Loadermenu {
	
	JFrame frame = new JFrame("Snip-Stitch");
	JButton findXml = new JButton();
	JLabel chosenXml = new JLabel("No XML selected");
	String xmlFilepath = new String("NONE");
	JButton loadVideo = new JButton();
	JLabel chosenVideo = new JLabel("No Video Selected");
	String videoFilepath = new String("NONE");
	JButton goBack = new JButton();
	JButton editVideo = new JButton();
	Mainmenu mainMenu;
	JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	Editor editor;
	
	public Loadermenu() {
		
		//the main frame
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(3, 4));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//find xml button
		findXml.setPreferredSize(new Dimension(50, 50));
		findXml.setText("Find XML");
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
		frame.add(findXml);
		
		//displays the chosen xml file
		frame.add(chosenXml);
		
		//find the video file
		//loadVideo = new JButton();
		loadVideo.setPreferredSize(new Dimension(50, 50));
		loadVideo.setText("Find Video");
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
		frame.add(loadVideo);
		
		//displays chosen video file
		frame.add(chosenVideo);
		
		//Edit Video!
		editVideo.setPreferredSize(new Dimension(50, 50));
		editVideo.setText("Edit Video!");
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
				//editor.displaySnippets();
				
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
				}
				
			}
		});
		frame.add(editVideo);
		
		//the button to go back to the main menu
		goBack.setPreferredSize(new Dimension(50, 50));
		goBack.setText("Go Back");
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				mainMenu = new Mainmenu();
			}
		});
		frame.add(goBack);
	}
}