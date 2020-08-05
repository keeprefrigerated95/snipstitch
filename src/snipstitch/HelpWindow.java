package snipstitch;

import java.awt.BorderLayout;

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
				
				JTextPane txtpnTextHere = new JTextPane();
				txtpnTextHere.setText("XML Files:\n"
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
						+ "You can view and edit XML files in your basic Notepad or Text editor, but I would reccomend using something like notepad++ or visual studio code\n\n\n"
						+ "Finding ffmpeg:\n"
						+ "if Snip-Stitch cannot find ffmpeg, you'll have to type in its filepath directly. If you don't know where it is you can enter:\n\n"
						+ "type ffmpeg\n\n"
						+ "into your terminal if you are using Mac or Linux.\n"
						+ "I don't know how to find it in Windows yet, but I haven't had any problems running it in Windows either, sorry");
				txtpnTextHere.setBounds(10, 11, 781, 490);
				panel.add(txtpnTextHere);
				
				JButton goBackButton = new JButton("Back");
				goBackButton.setBounds(10, 520, 89, 23);
				panel.add(goBackButton);
			}
		});
	}
}
