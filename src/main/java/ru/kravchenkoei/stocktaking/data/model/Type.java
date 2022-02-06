package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Type extends AbstractModel implements Serializable {
  private String name;

//  @OneToMany(mappedBy = "type", cascade = CascadeType.)
//  @PrimaryKeyJoinColumn
//  private List<Element> elements;
}
