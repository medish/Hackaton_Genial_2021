package core.dataclasses;

import core.optaplaner.domain.AbstractPersistable;
import org.optaplanner.core.api.domain.entity.PlanningEntity;


public class User extends AbstractPersistable {
	private String name;

	public  User() {

	}
	public User(String name) {
		this.name = name;
	}

	public User(long id, String name) {
		super(id);
		this.name = name;
	}

	@Override
	public String toString() {
		return  "(" + id + ")" + " " + name ;
	}

	public String getName() {
		return name;
	}
}
