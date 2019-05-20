package hu.companycar.domain.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Adatok kiírása (7. feladat)
 * @author Peter_Fazekas
 */
public class DataWriter {
	
	private static final String FILE_NAME_PATTERN = "%s_menetlevel.txt";
	private static final String SUCCESS = "Menetlevél kész.";

	/**
	 * A menetlevél kiírása állományba
	 * @param licensePlateNumber az autó rendszáa
	 * @param lines az állományba kiírandó sorok listája
	 * @return
	 */
	public String printAll(String licensePlateNumber, List<String> lines) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(createFileName(licensePlateNumber)))){
			lines.forEach(pw::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private String createFileName(String licensePlateNumber) {
		return String.format(FILE_NAME_PATTERN, licensePlateNumber);
	}

}
