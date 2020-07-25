package snipstitch;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Loadermenu {
	
	JFrame frame;
	//JButton loadFile;
	//JButton findXml;
	JButton goBack;
	Mainmenu mainMenu;
	
	public Loadermenu() {

		frame = new JFrame("Snip-Stitch");
		frame.setSize(400, 400);
		frame.setLayout(new GridLayout(3, 4));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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