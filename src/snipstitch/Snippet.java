package snipstitch;


public class Snippet {
	
	private int startHour;
	private int startMinute;
	private int startSecond;
	private int endHour;
	private int endMinute;
	private int endSecond;
	
	public Snippet() {
		
	}

	//gets the times and converts into compatible string
	public String getStartTime() {
		String returnString = startHour + ":" + startMinute + ":" + startSecond; 
		return returnString;
	}
	
	public String getEndTime() {
		String returnString = endHour + ":" + endMinute + ":" + endSecond;
		return returnString;
	}
	
	//setters
	public void setStartHour(int newStartHour) {
		startHour = newStartHour;
	}
	
	public void setStartMinute(int newStartMinute) {
		startMinute = newStartMinute;
	}
	
	public void setStartSecond(int newStartSecond) {
		startSecond = newStartSecond;
	}
	
	public void setEndHour(int newEndHour) {
		endHour = newEndHour;
	}
	
	public void setEndMinute(int newEndMinute) {
		endMinute = newEndMinute;
	}
	
	public void setEndSecond(int newEndSecond) {
		endSecond = newEndSecond;
	}
	
}
