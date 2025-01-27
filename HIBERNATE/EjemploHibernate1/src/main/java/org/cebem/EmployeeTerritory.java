package org.cebem;

import jakarta.persistence.*;

@Entity
@Table(name = "EmployeeTerritories")
public class EmployeeTerritory {
    @EmbeddedId
    private EmployeeTerritoryId id;

    @MapsId("employeeID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EmployeeID", nullable = false)
    private Employee employeeID;

    @MapsId("territoryID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TerritoryID", nullable = false)
    private Territory territoryID;

    public EmployeeTerritoryId getId() {
        return id;
    }

    public void setId(EmployeeTerritoryId id) {
        this.id = id;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Territory getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(Territory territoryID) {
        this.territoryID = territoryID;
    }

    @Override
    public String toString() {
        return "EmployeeTerritory{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", territoryID=" + territoryID +
                '}';
    }

}