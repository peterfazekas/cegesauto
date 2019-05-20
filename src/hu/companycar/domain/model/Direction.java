package hu.companycar.domain.model;

import java.util.Arrays;

/**
 * DTO - Enum
 * A parkolóból való gépjárműmozgás iránya (kihajtás - 0, behajtás - 1)
 * @author Peter_Fazekas
 */
public enum Direction {

	AWAY(0, "ki"),
	BACK(1, "be");
	
	private final int id;
	private final String description;

	private Direction(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public static Direction getDirection(int id) {
		return Arrays.stream(Direction.values()).filter(direction -> direction.getId() == id).findAny().get();
	}
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
