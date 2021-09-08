package database.Model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RoomId implements Serializable {

    @Column(name = "id", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "room_department_id", nullable = false)
    private Department department;

    public RoomId(String number, Department department_id)
    {
        this.number = number;
        this.department = department_id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Department getDepartment_id()
    {
        return department;
    }

    public void setDepartment_id(Department department)
    {
        this.department = department;
    }
}
