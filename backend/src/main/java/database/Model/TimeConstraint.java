package database.Model;

import javax.persistence.*;

@Entity

@Table(name = "TimeConstraint")
public class TimeConstraint {

    @Id
    @GeneratedValue
    private String id;

    private String selector;
    private boolean wants;

    @ManyToOne
    @JoinColumn(name = "time_constraint_day_id", nullable = false)
    private Day day;

    @ManyToOne
    @JoinColumn(name = "time_constraint_department", nullable = false)
    private Department department;

    private int hour_begin;
    private int priority;

    public TimeConstraint()
    {

    }

    public TimeConstraint(String id, String selector, boolean wants,
                          Day day, int hour_begin, Department department, int priority
    ) {
        this.id = id;
        this.selector = selector;
        this.wants = wants;
        this.day = day;
        this.hour_begin = hour_begin;
        this.department = department;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public boolean isWants() {
        return wants;
    }

    public void setWants(boolean wants) {
        this.wants = wants;
    }

    public Day getDayId() {
        return day;
    }

    public void setDayId(Day day) {
        this.day = day;
    }

    public int getHour_begin() {
        return hour_begin;
    }

    public void setHour_begin(int hour_begin) {
        this.hour_begin = hour_begin;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
