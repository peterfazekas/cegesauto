package hu.companycar.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import hu.companycar.domain.model.CarLogEntry;
import hu.companycar.domain.model.SingleCarLogEntry;

/**
 * Controller logika a feladatok megoldásához! 
 * @author Peter_Fazekas
 */
public class CompanyCars {

	private List<SingleCarLogEntry> singleCarLogEntries;
	private List<CarLogEntry> carLogEntries;

	public CompanyCars(List<SingleCarLogEntry> singleCarLogEntries, List<CarLogEntry> carLogEntries) {
		this.singleCarLogEntries = singleCarLogEntries;
		this.carLogEntries = carLogEntries;
	}

	/**
	 * 2. feladat: Adja meg, hogy melyik autót vitték el utoljára a parkolóból! 
	 * Az eredményt a mintának megfelelően írja a képernyőre!
	 * @return a válasz szöveges formában
	 */
	public String getLastAwayCar() {
		SingleCarLogEntry lastAwayCarEntry = getLastAwayCarEntry();
		return String.format("%d.nap rendszám: %s", lastAwayCarEntry.getDay(), lastAwayCarEntry.getLicensePlateNumber());
	}
	
	private SingleCarLogEntry getLastAwayCarEntry() {
		return singleCarLogEntries.stream()
				.filter(SingleCarLogEntry::isAway)
				.sorted(Comparator.reverseOrder())
				.findFirst()
				.get();
	}

	/**
	 * 3. feladat: Kérjen be egy napot és írja ki a képernyőre a minta szerint, 
	 * hogy mely autókat vitték ki és hozták vissza az adott napon!
	 * @param day a nap sorszáma
	 * @return a válasz szöveges formában
	 */
	public String getDailyTraffic(int day) {
		return String.format("Forgalom a(z) %d. napon:\n%s", day, getCarLogEntriesByDay(day));
	}

	private String getCarLogEntriesByDay(int day) {
		return singleCarLogEntries.stream()
				.filter(log -> log.getDay() == day)
				.map(SingleCarLogEntry::toString)
				.collect(Collectors.joining("\r\n"));
	}
	
	/**
	 * 4. feladat: Adja meg, hogy hány autó nem volt bent a hónap végén a parkolóban!
	 * @return a válasz szöveges formában
	 */
	public String getAwayCarCountAtTheEndOfMonth() {
		return String.format("A hónap végén %d autót nem hoztak vissza.", countAwayCarCountAtTheEndOfMonth());
	}
	
	private Long countAwayCarCountAtTheEndOfMonth() {
		return carLogEntries.stream().filter(CarLogEntry::isStillAway).count();
	}
	
	/**
	 * 5. feladat: Készítsen statisztikát, és írja ki a képernyőre mind a 10 autó esetén az ebben a hónapban 
	 * megtett távolságot kilométerben! 
	 * A hónap végén még kint lévő autók esetén az utolsó rögzített kilométerállással számoljon! 
	 * A kiírásban az autók sorrendje tetszőleges lehet.
	 * @return a válasz szöveges formában
	 */
	
	public String getTotalDistancesByCar() {
		return getCarTotalDistanceMap().entrySet().stream()
				.map(i -> i.getKey() + " " + i.getValue() + " km")
				.collect(Collectors.joining("\r\n"));
	}
	
	private Map<String, Integer> getCarTotalDistanceMap() {
		return carLogEntries.stream()
				.collect(Collectors.groupingBy(CarLogEntry::getLicensePlateNumber, () -> new TreeMap<String, Integer>(),Collectors.summingInt(CarLogEntry::getDistance)));
	}
	
	/**
	 * 6. feladat: Határozza meg, melyik személy volt az, aki az autó egy elvitele alatt a 
	 * leghosszabb távolságot tette meg! A személy azonosítóját és a megtett kilométert a 
	 * minta szerint írja a képernyőre! (Több legnagyobb érték esetén bármelyiket kiírhatja.)
	 * @return  a válasz szöveges formában
	 */
	public String getLongestDistanceUserIdPerSingleUsage() {
		CarLogEntry logEntry = getLongestDistanceEntryLogPerSingleUsage();
		return String.format("Leghosszabb út: %d km, személy: %d", logEntry.getDistance(), logEntry.getUser());
	}
	
	private CarLogEntry getLongestDistanceEntryLogPerSingleUsage() {
		return carLogEntries.stream()
				.sorted(Comparator.comparing(CarLogEntry::getDistance).reversed())
				.findFirst()
				.get();
	}
	
	/**
	 * 7. feladat: Az autók esetén egy havi menetlevelet kell készíteni! 
	 * Kérjen be a felhasználótól egy rendszámot! 
	 * Készítsen egy X_menetlevel.txt állományt, amelybe elkészíti az adott rendszámú autó menetlevelét! 
	 * (Az X helyére az autó rendszáma kerüljön!) 
	 * A fájlba soronként tabulátorral elválasztva a személy azonosítóját, a kivitel időpontját (nap. óra:perc), 
	 * a kilométerszámláló állását, a visszahozatal időpontját (nap. óra:perc), 
	 * és a kilométerszámláló állását írja a minta szerint!
	 * @return a válasz szöveges formában
	 */
	public List<String> getJournesForm(String licensePlateNumber) {
		return carLogEntries.stream()
				.filter(log -> log.getLicensePlateNumber().equals(licensePlateNumber))
				.map(CarLogEntry::toString)
				.collect(Collectors.toList());
	}

	
}
