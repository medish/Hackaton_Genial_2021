package server.models;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Date")
public class Date {

    @EmbeddedId
    private DateId dateId;

    public Date(DateId dateId) {
        this.dateId = dateId;
    }

    @OneToMany(mappedBy = "date", targetEntity = Output.class)
    private List<Output> outputs;

    @OneToMany(mappedBy = "date", targetEntity = TimeConstraint.class)
    private List<TimeConstraint> timeConstraints;

    public DateId getDateId() {
        return dateId;
    }

    public void setDateId(DateId dateId) {
        this.dateId = dateId;
    }
}
