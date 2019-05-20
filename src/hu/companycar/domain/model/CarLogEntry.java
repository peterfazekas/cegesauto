package hu.companycar.domain.model;

import java.util.Optional;

/**
 * DTO
 * Az autóhoz tartozó naplóbejegyzés párok
 * - away - az elvitt autó adatai
 * - back - a visszahozott autó adatai (null, ha nincs visszahozva!)
 * @author Peter_Fazekas
 */
public class CarLogEntry {
	
	private final SingleCarLogEntry away;
	private final SingleCarLogEntry back;

	public CarLogEntry(SingleCarLogEntry away) {
		this(away, null);
	}

	public CarLogEntry(SingleCarLogEntry away, SingleCarLogEntry back) {
		this.away = away;
		this.back = back;
	}
	
	public boolean isBack() {
		return back != null;
	}

	public boolean isStillAway() {
		return !isBack();
	}
	
	public int getUser() {
		return away.getUserId();
	}
	
	public String getLicensePlateNumber() {
		return away.getLicensePlateNumber();
	}

	public Integer getDistance() {
		return isBack() ? back.getKmCounter() - away.getKmCounter() : 0;
	}

	@Override
	public String toString() {
		return String.format("%d%s%s", getUser(), printEntryLog(away), printEntryLog(back));
	}
	
	private String printEntryLog(SingleCarLogEntry entry) {
		return Optional.ofNullable(entry)
				.map(log -> String.format("\t%d.\t%s\t%d km", log.getDay(), log.getTime(), log.getKmCounter()))
				.orElse("");
	}
	
	
}
