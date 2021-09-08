package database.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "constraint")
public class Constraint {

    @Id
    private String id;
    private String name;
    private String firstName;

    @Column(unique = true)
    private String email;

    private boolean is_admin;

    public Constraint()
    {

    }

    public Constraint(String id, String name, String firstName, String email)
    {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
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
