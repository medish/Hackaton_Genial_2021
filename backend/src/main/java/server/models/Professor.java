package server.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor extends User {

    @ManyToMany
    @JoinTable(name = "professor_course", joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    @OneToMany(mappedBy = "professor")
    private Set<CourseSlot> slots;

    public Professor(int id, String name, String firstName, String email) {
        super(id, name, firstName, email, UserRole.PROFESSOR);
    }

    public Professor() {
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<CourseSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<CourseSlot> slots) {
        this.slots = slots;
    }
}
