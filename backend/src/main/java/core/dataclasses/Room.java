package core.dataclasses;

public class Room implements Input {

	protected int RoomNbr;
	protected int departmentId;
	protected int capacity;
	protected RoomType roomType;
	private String name;

	public Room(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
