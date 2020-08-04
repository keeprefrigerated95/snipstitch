package snipstitch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Settings {
	
	static String ffmpegPath = new String("ffmpeg"); //default filepath
	Boolean isDefault = true; //the use can choose their own filepath
	OperatingSys OS = new OperatingSys();
	
	Settings() throws IOException {
		//by default, search in the terminal for the filepath to ffmpeg
		if(OS.isMac() || OS.isUnix()) {
			
			ProcessBuilder builder = new ProcessBuilder("type", "-p", "ffmpeg");
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream is = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			ffmpegPath = reader.readLine();
			
			/*
			String line = null;
			while ((line = reader.readLine()) != null) {
			   System.out.println(line);
			}
			*/
		}
	}
}
