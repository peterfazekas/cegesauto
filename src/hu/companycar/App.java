package hu.companycar;

import java.util.List;
import java.util.Scanner;

import hu.companycar.controller.CompanyCars;
import hu.companycar.domain.model.CarLogEntry;
import hu.companycar.domain.model.SingleCarLogEntry;
import hu.companycar.domain.service.CarLogEntryFactory;
import hu.companycar.domain.service.Console;
import hu.companycar.domain.service.DataReader;
import hu.companycar.domain.service.DataWriter;

/**
 * Emelt szintű érettségi: 2019. május 13. (Céges autók)
 * URL: https://www.oktatas.hu/bin/content/dload/erettsegi/feladatok_2019tavasz_emelt/e_inf_19maj_fl.pdf
 * Sources: https://www.oktatas.hu/bin/content/dload/erettsegi/feladatok_2019tavasz_emelt/e_inffor_19maj_fl.zip
 * @author Peter_Fazekas
 */
public class App {

	private final CompanyCars companyCars;
	private final DataReader dataReader;
	private final DataWriter dataWriter;
	private final Console console;

	public App() {
		dataReader = new DataReader();
		CarLogEntryFactory carLogEntryFactory = new CarLogEntryFactory();
		List<SingleCarLogEntry> singleCarLogEntries = dataReader.getData("autok.txt");
		List<CarLogEntry> carLogEntries = carLogEntryFactory.create(singleCarLogEntries);
		companyCars = new CompanyCars(singleCarLogEntries, carLogEntries);
		console = new Console(new Scanner(System.in));
		dataWriter = new DataWriter();
	}

	public static void main(String[] args) {
		new App().run();
	}
	
	private void run() {
		System.out.println("2. feladat: " + companyCars.getLastAwayCar());
		int day = console.readInt("3. feladat: kérem adja meg egy nap sorszámát: ");
		System.out.println(companyCars.getDailyTraffic(day));
		System.out.println("4. feladat: " + companyCars.getAwayCarCountAtTheEndOfMonth());
		System.out.println("5. feladat:\r\n" + companyCars.getTotalDistancesByCar());
		System.out.println("6. feladat: " + companyCars.getLongestDistanceUserIdPerSingleUsage());
		String licensePlateNumber = console.read("7. feladat: Adjon meg egy rendszámot: ");
		System.out.println(dataWriter.printAll(licensePlateNumber, companyCars.getJournesForm(licensePlateNumber)));
	}
}
