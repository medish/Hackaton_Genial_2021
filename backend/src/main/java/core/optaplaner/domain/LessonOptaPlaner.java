package core.optaplaner.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import core.output.Timeslot;
import server.models.Lesson;
import server.models.Professor;
import server.models.Room;

@PlanningEntity
public class LessonOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<Lesson> {

    private Duration duration;

    private String subject;
    private Professor teacher;
    private String studentGroup;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public LessonOptaPlaner() {
    }

    public LessonOptaPlaner(long id, String subject, Professor teacher, String studentGroup, Duration duration) {
        super(id);
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
        this.duration = duration;
    }

    public LessonOptaPlaner(long id, String subject, Professor teacher, String studentGroup, Timeslot timeslot,
            Duration duration, Room room) {
        this(id, subject, teacher, studentGroup, duration);
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

    public boolean isBeforeInWeek(LessonOptaPlaner lesson) {
        return getDayOfWeek().compareTo(lesson.getDayOfWeek()) < 0;
    }

    public boolean isCollideInWeek(LessonOptaPlaner lesson) {
        return getDayOfWeek().compareTo(lesson.getDayOfWeek()) == 0;
    }

    public boolean isAfterInWeek(LessonOptaPlaner lesson) {
        return getDayOfWeek().compareTo(lesson.getDayOfWeek()) > 0;
    }

    public boolean isBeforeInDay(LessonOptaPlaner lesson) {
        return getDayOfWeek().compareTo(lesson.getDayOfWeek()) != 0
                || (getStartTime().plus(duration).isBefore(lesson.getStartTime()));
    }

    public boolean isCollide(LessonOptaPlaner lesson) {
        if (getDayOfWeek().compareTo(lesson.getDayOfWeek()) != 0) {
            return false;
        }
        if (!getStartTime().isBefore(lesson.getStartTime()) && getStartTime().isBefore(lesson.getEndTime())) {
            return true;
        }
        if (!lesson.getStartTime().isBefore(getStartTime()) && lesson.getStartTime().isBefore(getEndTime())) {
            return true;
        }
        return false;
    }

    public boolean isAfterInDay(LessonOptaPlaner lesson) {
        return getDayOfWeek().compareTo(lesson.getDayOfWeek()) != 0
                || (lesson.getStartTime().plus(duration).isAfter(getStartTime()));
    }

    public DayOfWeek getDayOfWeek() {
        return timeslot.getDayOfWeek();
    }

    public LocalTime getStartTime() {
        return timeslot.getStartTime();
    }

    public LocalTime getEndTime() {
        return timeslot.getStartTime().plus(duration);
    }

    public static LessonOptaPlaner fromInput(Lesson lesson) {
    	LessonOptaPlaner lessonOptaPlaner = new LessonOptaPlaner();
    	lessonOptaPlaner.subject = lesson.getCourse().getName();
    	lessonOptaPlaner.teacher = lesson.getProfessors().iterator().next();
    	lessonOptaPlaner.studentGroup = lesson.getCourse().getDegrees().iterator().next().toString();
        return lessonOptaPlaner;
    }
}
