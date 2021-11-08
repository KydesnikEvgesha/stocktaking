package ru.kravchenkoei.stocktaking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Type implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private Long id;

  private String name;

  @OneToOne(mappedBy = "type", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Element element;
}
