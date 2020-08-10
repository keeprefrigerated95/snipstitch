/*****************************************************************
 * OPERATING SYSTEM
 * I was going to use this in case there was some compatibility
 * issues between operating systems. I don't think I used it
 * but I'm keeping it here just in case it's needed later
 ****************************************************************/

package snipstitch;

public class OperatingSys {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	//if the operating system being used is windows it returns true
	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}
	
	//and so on
	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}
	
	//and so on
	public static boolean isLinux() {

		return (OS.indexOf("linux") >= 0);

	}
	
	//you get the picture
	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

	}

	//these comments are probably unnecessary
	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}
}
