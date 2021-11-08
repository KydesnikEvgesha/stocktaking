package ru.kravchenkoei.stocktaking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Company implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;

  private String name;
  private String address;

  @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Element element;
}
