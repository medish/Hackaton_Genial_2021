package server.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "planning")
public class Planning implements IInput{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "planning")
    private Set<CourseSlot> slots;

    public Planning(String name, LocalDate createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Planning(Set<CourseSlot> slots){
        this.createdAt = LocalDate.now();
        this.slots = slots;
    }
    public Planning() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<CourseSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<CourseSlot> slots) {
        this.slots = slots;
    }

    public int getId() {
        return id;
    }
}
