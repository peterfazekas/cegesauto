package hu.companycar.domain.model;

/**
 * DTO
 * Saját DateTime osztály.
 * Tartalmazza a 
 * - hónap napját (a hónap adott napja)
 * - órát (a ki- vagy a behajtás időpontja)
 * - percet (a ki- vagy a behajtás időpontja)
 * Rendezhető!
 * @author Peter_Fazekas
 */
public class DateTime implements Comparable<DateTime>{

	private final int day;
	private final int hour;
	private final int minute;
	
	public DateTime(int day, int hour, int minute) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	
	public String getTime() {
		return String.format("%02d:%02d", hour, minute);
	}
	
	public boolean isLater(DateTime dateTime) {
		return compareTo(dateTime) > 0;
	}
	
	private Integer toSecond() {
		return day * 86400 + hour * 3600 + minute * 60;
	}

	@Override
	public int compareTo(DateTime dateTime) {
		return this.toSecond().compareTo(dateTime.toSecond());
	}

}
