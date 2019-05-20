package hu.companycar.domain.model;

/**
 * DTO
 * A forrásállomány adat reprezentációja
 * Tartalmazza:
 * - dateTime - A ki- vagy a behajtás napja és időpontja
 * - licensePlateNumber - az autó rendszáma
 * - userId - az autót igénybe vevő dolgozó azonosítója
 * - kmCounter - a km számláló állása
 * - direction - ki- vagy behajtását iránya
 * @author Peter_Fazekas
 */
public class SingleCarLogEntry implements Comparable<SingleCarLogEntry>{

	private final DateTime dateTime;
	private final String licensePlateNumber;
	private final int userId;
	private final int kmCounter;
	private final Direction direction;
	
	public SingleCarLogEntry(DateTime dateTime, String licensePlateNumber, int userId, int kmCounter, Direction direction) {
		this.dateTime = dateTime;
		this.licensePlateNumber = licensePlateNumber;
		this.userId = userId;
		this.kmCounter = kmCounter;
		this.direction = direction;
	}

	public DateTime getDateTime() {
		return dateTime;
	}
	
	public int getDay() {
		return dateTime.getDay();
	}
	
	public String getTime() {
		return dateTime.getTime();
	}
	
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public int getUserId() {
		return userId;
	}

	public int getKmCounter() {
		return kmCounter;
	}

	public boolean isAway() {
		return direction == Direction.AWAY;
	}
	
	public boolean isBack() {
		return direction == Direction.BACK;
	}
	
	public boolean isLater(DateTime otherDateTime) {
		return dateTime.isLater(otherDateTime);
	}

	@Override
	public int compareTo(SingleCarLogEntry o) {
		return this.getDateTime().compareTo(o.getDateTime());
	}

	@Override
	public String toString() {
		return String.format("%s %s %d %s", dateTime.getTime(), licensePlateNumber, userId, direction.getDescription());
	}
	
	
}
