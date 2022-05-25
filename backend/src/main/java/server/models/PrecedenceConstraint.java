package server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "precedence_constraint")
public class PrecedenceConstraint implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String selector;
    private boolean wants;
    @Column(name = "when_constraint")
    private String whenConstraint;
    private boolean strict;
    private String target;
    private int priority;

    public PrecedenceConstraint() {
    }

    public PrecedenceConstraint(int id, String selector, boolean wants, String whenConstraint, boolean strict,
            String target, int priority) {
        this.id = id;
        this.selector = selector;
        this.wants = wants;
        this.whenConstraint = whenConstraint;
        this.strict = strict;
        this.target = target;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public boolean isWants() {
        return wants;
    }

    public void setWants(boolean wants) {
        this.wants = wants;
    }

    public String getWhen() {
        return whenConstraint;
    }

    public void setWhen(String when) {
        this.whenConstraint = when;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
