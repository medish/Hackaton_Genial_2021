package server.models;

import java.io.Serializable;

public class CourseSlotId implements Serializable {
    private static final long serialVersionUID = 1L;

    private CourseGroup courseGroup;
    private Planning planning;

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }
}
