package core.optaplaner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import core.output.Timeslot;
import server.Model.Lesson;
import server.Model.Professor;
import server.Model.Room;

@PlanningEntity
public class LessonOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<Lesson> {

    private String subject;
    private Professor teacher;
    private String studentGroup;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public LessonOptaPlaner() {
    }

    public LessonOptaPlaner(long id, String subject, Professor teacher, String studentGroup) {
        super(id);
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public LessonOptaPlaner(long id, String subject, Professor teacher, String studentGroup, Timeslot timeslot,
            Room room) {
        this(id, subject, teacher, studentGroup);
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    public String getSubject() {
        return subject;
    }

    public Professor getTeacher() {
        return this.teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public static LessonOptaPlaner fromInput(Lesson lesson) {
        // TODO Auto-generated method stub
        return null;
    }
}
