package ru.kravchenkoei.stocktaking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Workstation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @MapsId
    private Employee employee;

    @OneToOne
    @MapsId
    private Location location;
}
