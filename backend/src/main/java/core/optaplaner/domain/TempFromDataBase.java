package core.optaplaner.domain;

import java.util.ArrayList;
import java.util.List;

import server.models.Course;
import server.models.Day;
import server.models.Department;
import server.models.Lesson;
import server.models.Professor;
import server.models.Room;
import server.models.RoomId;

public class TempFromDataBase {

    public List<Course> getAllCourses() {
        return new ArrayList<Course>();
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<Department>();
    }

    public List<Lesson> getAllLessons() {
        return new ArrayList<Lesson>();
    }

    public List<Professor> getAllProfessor() {
        return new ArrayList<Professor>();
    }

    public List<Room> getAllRooms() {
        return new ArrayList<Room>();
    }

    public List<Course> getAllCourse() {
        return new ArrayList<Course>();
    }

    public List<Day> getAllDay() {
        return new ArrayList<Day>();
    }

    public List<RoomId> getAllRoomId() {
        return new ArrayList<RoomId>();
    }
}
