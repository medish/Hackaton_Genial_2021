package core.dataclasses;

public class Lesson  {
	protected int lessonId;
	protected double duration;
	protected RoomType roomType;
	
	public Lesson(int lessonId, double duration, RoomType roomType) {
		this.lessonId = lessonId;
		this.duration = duration;
		this.roomType = roomType;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	
}
