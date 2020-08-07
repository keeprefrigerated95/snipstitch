package snipstitch;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class uploadTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uploadTest window = new uploadTest();
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
	public uploadTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final int MAX = 100;
		final JFrame frame = new JFrame("Snip-Stitch");
		frame.setBounds(100, 100, 606, 93);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JProgressBar pb = new JProgressBar();
        pb.setMinimum(0);
        pb.setMaximum(MAX);
        pb.setStringPainted(true);
        
        panel.add(pb);
        
	}
}
