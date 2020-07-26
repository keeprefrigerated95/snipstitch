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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loadermenu {
	
	JFrame frame;
	JButton loadVideo;
	JButton findXml;
	JButton goBack;
	Mainmenu mainMenu;
	FileChooser fileChooser;
	
	public Loadermenu() {
		
		//the main frame
		frame = new JFrame("Snip-Stitch");
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(3, 4));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//find xml button
		findXml = new JButton();
		findXml.setPreferredSize(new Dimension(50, 50));
		findXml.setText("Find XML");
		findXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fileChooser
				findXml.setBackground(Color.green);
			}
		});
		frame.add(findXml);
		
		//find the video file
		loadVideo = new JButton();
		loadVideo.setPreferredSize(new Dimension(50, 50));
		loadVideo.setText("Find Video");
		loadVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fileChooser
				loadVideo.setBackground(Color.green);
			}
		});
		frame.add(loadVideo);
		
		//the button to go back to the main menu
		goBack = new JButton();
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