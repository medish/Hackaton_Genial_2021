package server.models;

import java.io.Serializable;

public class CourseGroupId implements Serializable {
    private int groupId;
    private Course course;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
