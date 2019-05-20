package hu.companycar.domain.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.companycar.domain.model.DateTime;
import hu.companycar.domain.model.Direction;
import hu.companycar.domain.model.SingleCarLogEntry;

/**
 * Adatok beolvasása (1. feladat)
 * @author Peter_Fazekas
 */
public class DataReader {

	/**
	 * Adatolvasás:
	 * @param fileName az olvasandó állomány neve
	 * @return {@link SingleCarLogEntry} elemek listája
	 */
	public List<SingleCarLogEntry> getData(String fileName) {
		return parseData(read(fileName));
	}
	
	private List<SingleCarLogEntry> parseData(List<String> lines) {
		return lines.stream().map(this::createSingleCarLogEntry).collect(Collectors.toList());
	}

	private SingleCarLogEntry createSingleCarLogEntry(String line) {
		String[] items = line.split(" ");
		DateTime dateTime = createDateTime(items[0], items[1]);
		String licensePlateNumber = items[2];
		int userId = getValue(items[3]);
		int kmCounter = getValue(items[4]);
		Direction direction = Direction.getDirection(getValue(items[5]));
		return new SingleCarLogEntry(dateTime, licensePlateNumber, userId, kmCounter, direction);
	}
	
	private DateTime createDateTime(String day, String time) {
		String[] items = time.split(":");
		return new DateTime(getValue(day), getValue(items[0]), getValue(items[1]));
	}
	
	private int getValue(String text) {
		return Integer.parseInt(text);
	}
	
	private List<String> read(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
