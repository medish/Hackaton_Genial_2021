package core.dataclasses;

public class Department implements Input {
	protected int departmentId;
	protected String name;

	public Department(int departmentId, String name) {
		super();
		this.departmentId = departmentId;
		this.name = name;
	}

}
