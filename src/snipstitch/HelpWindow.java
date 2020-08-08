package snipstitch;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class HelpWindow {
	private JFrame frame;
	
	
	public HelpWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 817, 600);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				ImageIcon icon = new ImageIcon("scissors2.png");
				frame.setIconImage(icon.getImage());
				
				JTextPane txtpnTextHere = new JTextPane();
				txtpnTextHere.setText("****************************XML Files****************************\n"
						+ "XML files are used to indicate which clips from the video you want to keep, not the ones that will be cut out.\n"
						+ "It must be formatted like so:\n\n"
						+ "<edits>\n"
						+ "    <snippet>\n"
						+ "        <description>a description of the clip</description>\n"
						+ "        <sHr>the start-hour of the clip</sHr> <sMin>start minute</sMin> <sSec>start second</sSec>\n"
						+ "        <eHr>the end hour</ehr> <eMin>the end minute</eMin> <eSec>The end second</eSec>\n"
						+ "    </snippet>\n"
						+ "    <snippet>\n"
						+ "        <description>one more example</description>\n"
						+ "        <sHr>0</sHr> <sMin>4</sMin> <sSec>20</sSec>\r\n"
						+ "        <eHr>1</eHr> <eMin>2</eMin> <eSec>1</eSec>\n"
						+ "    </snippets>\n"
						+ "</edits>\n\n"
						+ "You can view and edit XML files in your basic Notepad or Text editor, or something more intuitive like notepad++ or visual studio code\n\n"
						+ "****************************ffmpeg****************************\n"
						+ "You need ffmpeg for this to work. If you don't have it, download it here: https://ffmpeg.org/download.html\n"
						+ "Some computers have it by default. To check, type ffmpeg into your terminal (Mac/Linux) or command prompt (Windows)"
						+ "If you have ffmpeg and Snip-Stitch cannot find it, you'll have to type in its filepath directly. If you don't know where it is you can enter:\n\n"
						+ "type ffmpeg\n\n"
						+ "into your terminal if you are using Mac or Linux.\n"
						+ "For windows you may just have to add it to the windows path. Here's a good tutorial: https://windowsloop.com/install-ffmpeg-windows-10/#add-ffmpeg-to-Windows-path");
				txtpnTextHere.setBounds(10, 11, 781, 490);
				panel.add(txtpnTextHere);
				
				JButton goBackButton = new JButton("Back");
				goBackButton.setBounds(10, 520, 89, 23);
				
				goBackButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								MainMenu mainMenu= new MainMenu();
							}
						});
					}
				});		
				
				panel.add(goBackButton);
			}
		});
	}
}
