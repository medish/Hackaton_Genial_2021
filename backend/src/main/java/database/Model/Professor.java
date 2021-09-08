package database.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Professor")
public class Professor implements Serializable {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName =  "id")
    private Customer customer_id;

    @ManyToMany(mappedBy = "professors")
    private List<Lesson> lessons = new ArrayList<>();

    public Professor()
    {
        super();
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public Customer getCustomer_id()
    {
        return customer_id;
    }

    public List<Lesson> getLessons()
    {
        return lessons;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter--------------------------------*/
    /*---------------------------------------------------------------*/

    public void setLessons(List<Lesson> lessons)
    {
        this.lessons = lessons;
    }

    public void setCustomer_id(Customer customer_id)
    {
        this.customer_id = customer_id;
    }
}
