package core.optaplaner.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import server.models.Course;
import server.models.Date;
import server.models.Lesson;
import server.models.Output;
import server.models.Professor;
import server.models.Room;

@PlanningEntity
public class LessonOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<Lesson> {

    private Course subject;
    private Professor teacher;
    private Duration duration;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Date timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public LessonOptaPlaner() {
    }

    public LessonOptaPlaner(long id, Course subject, Professor teacher, Duration duration) {
        super(id);
        this.subject = subject;
        this.teacher = teacher;
        this.duration = duration;
    }

    public LessonOptaPlaner(long id, Course subject, Professor teacher, Duration duration, Date timeslot, Room room) {
        this(id, subject, teacher, duration);
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    public Course getSubject() {
        return subject;
    }

    public Professor getTeacher() {
        return this.teacher;
    }

    public Date getDate() {
        return timeslot;
    }

    public void setDate(Date timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isBeforeInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) < 0;
    }

    public boolean isCollideInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) == 0;
    }

    public boolean isAfterInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) > 0;
    }

    public boolean isBeforeInDay(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) != 0
                || (getStartTime().plus(duration).isBefore(lesson.getStartTime()));
    }

    public boolean isCollide(LessonOptaPlaner lesson) {
        if (getDay().compareTo(lesson.getDay()) != 0) {
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
        return getDay().compareTo(lesson.getDay()) != 0
                || (lesson.getStartTime().plus(duration).isAfter(getStartTime()));
    }

    public DayOfWeek getDay() {
        return timeslot.getDay();
    }

    public LocalTime getStartTime() {
        return timeslot.getHour();
    }

    public LocalTime getEndTime() {
        return timeslot.getHour().plus(duration);
    }

    public static LessonOptaPlaner fromInput(Lesson lesson) {
        Iterator<Professor> professors = lesson.getProfessors() != null ? lesson.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        LessonOptaPlaner lessonOptaPlaner = new LessonOptaPlaner(lesson.getId().hashCode(), lesson.getCourse(),
                professors.hasNext() ? professors.next() : null, lesson.getDuration());
        return lessonOptaPlaner;
    }

    public static LessonOptaPlaner fromInput(Output output) {
        Iterator<Professor> professors = output.getProfessors() != null ? output.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        LessonOptaPlaner lessonOptaPlaner = new LessonOptaPlaner(output.getId().hashCode(), output.getCourse(),
                professors.hasNext() ? professors.next() : null, output.getDuration(), output.getDate(),
                output.getRoom());
        return lessonOptaPlaner;
    }

    public Output toOutput() {
        return new Output(timeslot, room,
                new Lesson(id + "", duration, 0, subject, null, teacher == null ? null : Set.of(teacher)));
    }
}
