package server.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "professor")
public class Professor extends User {

    @ManyToMany
    private Set<Course> courses;

    @OneToMany
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
