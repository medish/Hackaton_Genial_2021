package database.Model;

import javax.persistence.*;


@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "is_admin", columnDefinition = "boolean default false")
    private boolean is_admin;

    public Customer(String id, String name, String firstName, String email, boolean is_admin)
    {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.is_admin = is_admin;
    }

    public Customer()
    {

    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public boolean getIsAdmin()
    {
        return is_admin;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter--------------------------------*/
    /*---------------------------------------------------------------*/

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setIsAdmin(boolean is_admin)
    {
        this.is_admin = is_admin;
    }
}
