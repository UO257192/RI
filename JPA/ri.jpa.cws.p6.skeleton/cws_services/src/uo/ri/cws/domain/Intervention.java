package uo.ri.cws.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tInterventions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "workOrder_id", "mechanic_id"
        })
})
public class Intervention extends BaseEntity{
    @ManyToOne
    private WorkOrder workOrder;
    @ManyToOne
    private Mechanic mechanic;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int minutes;

    @OneToMany
    private Set<Substitution> substitucions = new HashSet<>();

    public Intervention(Mechanic mechanic, WorkOrder workOrder) {
        super();
        Associations.Intervene.link(workOrder, this, mechanic);
    }
    public Intervention(Mechanic mechanic, WorkOrder workOrder, int minutes) {
        this(mechanic, workOrder);
        this.minutes = minutes;
    }

    public Intervention() {

    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    void _setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    void _setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public int getMinutes() {
        return minutes;
    }

    public Set<Substitution> getSubstitutions() {
        return new HashSet<>(substitucions);
    }

    Set<Substitution> _getSubstitutions() {
        return substitucions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intervention that = (Intervention) o;
        return workOrder.equals(that.workOrder) &&
                mechanic.equals(that.mechanic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workOrder, mechanic);
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "workOrder=" + workOrder +
                ", mechanic=" + mechanic +
                ", date=" + date +
                ", minutes=" + minutes +
                '}';
    }

    public double getAmount() {
        double amount = 0L;
        for (Substitution substitution : substitucions)
            amount += substitution.getImporte();
        amount += workOrder.getVehicle().getVehicleType().getPricePerHour() * ((double) minutes / 60L);
        return amount;
    }
}
