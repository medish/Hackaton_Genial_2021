package core.dataclasses;

public class Course implements Input {
	private int courseId;
	private String courseName;

	public Course(int courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

}
