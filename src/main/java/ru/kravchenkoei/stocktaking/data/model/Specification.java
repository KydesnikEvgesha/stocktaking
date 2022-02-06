package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Specification extends AbstractModel {

  private String name;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private Type type;
}
