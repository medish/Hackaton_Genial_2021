package server.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Professor")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "customer_id")
    private String id;

    @OneToOne(cascade = {CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public Professor(String id, String name, String firstName, String email, boolean is_admin, String password) {
        this.customer = new Customer(id, name, firstName, email, is_admin, password);
        this.id = id;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter--------------------------------*/
    /*---------------------------------------------------------------*/

    public Professor() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer_id) {
        this.customer = customer_id;
    }

    @JsonIgnore
    public String getId() {
        return customer.getId();
    }

    @JsonIgnore
    public void setId(String id) {
        customer.setId(id);
    }

    @JsonIgnore
    public String getName() {
        return customer.getName();
    }

    @JsonIgnore
    public void setName(String name) {
        customer.setName(name);
    }

    @JsonIgnore
    public String getFirstName() {
        return customer.getFirstName();
    }

    @JsonIgnore
    public boolean getIsAdmin() {
        return customer.getIsAdmin();
    }

    @JsonIgnore
    public void setFirstName(String firstName) {
        customer.setFirstName(firstName);
    }

    @JsonIgnore
    public String getEmail() {
        return customer.getEmail();
    }

    @JsonIgnore
    public void setEmail(String email) {
        customer.setEmail(email);
    }

    @JsonIgnore
    public void setIsAdmin(boolean is_admin) {
        customer.setIsAdmin(is_admin);
    }
}
