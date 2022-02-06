package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Workstation implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;

  private String name;

  @OneToOne @MapsId private Employee employee;

  @OneToOne @MapsId private Location location;

  @OneToMany(mappedBy = "workstation")
  private List<Element> elementList;
}
