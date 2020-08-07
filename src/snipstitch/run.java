package snipstitch;


import javax.swing.SwingUtilities;


public class run {
	
	public run() {
	}
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainMenu mainmenu = new MainMenu();		
			}
			
		});
		
		
	}
}
