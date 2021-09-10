package server.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Professor")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @ManyToMany(mappedBy = "professors")
    private Set<Lesson> lessons;

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public Professor(String id, String name, String firstName, String email, boolean is_admin) {
        this(id, name, firstName, email, is_admin, new HashSet<>());
    }

    public Professor(String id, String name, String firstName, String email, boolean is_admin, Set<Lesson> lessons) {
        this.customer_id = new Customer(id, name, firstName, email, is_admin);
        this.id = id;
        this.lessons = lessons;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter--------------------------------*/
    /*---------------------------------------------------------------*/

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getId() {
        return customer_id.getId();
    }

    public void setId(String id) {
        customer_id.setId(id);
    }

    public String getName() {
        return customer_id.getName();
    }

    public void setName(String name) {
        customer_id.setName(name);
    }

    public String getFirstName() {
        return customer_id.getFirstName();
    }

    public boolean getIsAdmin() {
        return customer_id.getIsAdmin();
    }

    public void setFirstName(String firstName) {
        customer_id.setFirstName(firstName);
    }

    public String getEmail() {
        return customer_id.getEmail();
    }

    public void setEmail(String email) {
        customer_id.setEmail(email);
    }

    public void setIsAdmin(boolean is_admin) {
        customer_id.setIsAdmin(is_admin);
    }
}
