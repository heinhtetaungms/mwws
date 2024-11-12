package org.mw.mwws.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DISTRICT")
public class District {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Division division;
}
