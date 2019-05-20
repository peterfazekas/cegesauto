package hu.companycar.domain.service;

import java.util.Scanner;

/**
 * Adatbeolvasás billentyűzetről (3. 7. feladatokhoz)
 * @author Peter_Fazekas
 */
public class Console {

	private final Scanner scanner;

	public Console(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Nap sorszámának beolvasása (3. feladat)
	 * @param text a képernyőre kiírandó szöveg az adatolvasás előtt
	 * @return a nap sorszáma
	 */
	public int readInt(String text) {
		System.out.print(text);
		return scanner.nextInt();
	}

	/**
	 * Autó rendszámának beolvasása (7. feladat)
	 * @param text a képernyőre kiírandó szöveg az adatolvasás előtt
	 * @return a beolvasott rendszám
	 */
	public String read(String text) {
		System.out.print(text);
		return scanner.next();
	}
	
}
