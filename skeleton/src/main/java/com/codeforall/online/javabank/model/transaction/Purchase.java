package com.codeforall.online.javabank.model.transaction;

import com.codeforall.online.javabank.model.AbstractModel;
import com.codeforall.online.javabank.model.Establishment;
import jakarta.persistence.*;

/**
 * A class with represents a purchase
 */
@Entity
@Table(name = "purchases")
public class Purchase extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "establishment_id" , nullable = false)
    private Establishment establishment;

    /**
     * Get the establishment
     * @return the establishment
     */
    public Establishment getEstablishment() {
        return establishment;
    }

    /**
     * Set the establishment to the given establishment
     * @param establishment
     */
    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
