package hu.companycar.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hu.companycar.domain.model.CarLogEntry;
import hu.companycar.domain.model.SingleCarLogEntry;

/**
 * 
 * @author Peter_Fazekas
 *
 */
public class CarLogEntryFactory {

	public List<CarLogEntry> create(List<SingleCarLogEntry> singleCarLogEntries) {
		return getAwayCarEntries(singleCarLogEntries).stream()
				.map(i -> createCarLogEntry(singleCarLogEntries, i))
				.collect(Collectors.toList());
	}
	
	private CarLogEntry createCarLogEntry(List<SingleCarLogEntry> singleCarLogEntries, SingleCarLogEntry away) {
		return getBackCarEntry(singleCarLogEntries, away)
				.map(back -> new CarLogEntry(away, back))
				.orElse(new CarLogEntry(away));
	}
	
	private List<SingleCarLogEntry> getAwayCarEntries(List<SingleCarLogEntry> singleCarLogEntries) {
		return singleCarLogEntries.stream()
				.filter(SingleCarLogEntry::isAway)
				.collect(Collectors.toList());
	}
	
	private List<SingleCarLogEntry> getBackCarEntries(List<SingleCarLogEntry> singleCarLogEntries) {
		return singleCarLogEntries.stream()
				.filter(SingleCarLogEntry::isBack)
				.collect(Collectors.toList());
	}

	private Optional<SingleCarLogEntry> getBackCarEntry(List<SingleCarLogEntry> singleCarLogEntries, SingleCarLogEntry awayCarLogEntry) {
		return getBackCarEntries(singleCarLogEntries).stream()
				.filter(i -> isMatch(i, awayCarLogEntry))
				.sorted()
				.findFirst();
	}
	
	private boolean isMatch(SingleCarLogEntry singleCarLogEntry, SingleCarLogEntry awayCarLogEntry) {
		return singleCarLogEntry.getLicensePlateNumber().equals(awayCarLogEntry.getLicensePlateNumber())
				&& singleCarLogEntry.getUserId() == awayCarLogEntry.getUserId()
				&& singleCarLogEntry.isLater(awayCarLogEntry.getDateTime());
	}
	
}
