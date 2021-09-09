package server.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Professor")
public class Professor implements Serializable {

    @Id
    @Column (name = "customer_id")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id", referencedColumnName =  "id")
    private Customer customer_id;

    @ManyToMany(mappedBy = "professors")
    private Set<Lesson> lessons = new HashSet<>();

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

    public Set<Lesson> getLessons()
    {
        return lessons;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter--------------------------------*/
    /*---------------------------------------------------------------*/

    public void setLessons(Set<Lesson> lessons)
    {
        this.lessons = lessons;
    }

    public void setCustomer_id(Customer customer_id)
    {
        this.customer_id = customer_id;
    }
}
