package ru.kravchenkoei.stocktaking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Element implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;

  private String name;

  @OneToOne @MapsId private Company company;

  @OneToOne @MapsId private Type type;

  @OneToMany(mappedBy = "element")
  private List<Specification> specificationList;

  @ManyToOne @PrimaryKeyJoinColumn private Workstation workstation;
}
