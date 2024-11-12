package org.mw.mwws.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TOWNSHIP")
public class Township {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private District district;
}
