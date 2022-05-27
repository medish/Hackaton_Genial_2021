package server.models;

import java.io.Serializable;

public class MajorCourseId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Course course;
    private Major major;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
