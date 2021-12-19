package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Type extends AbstractModel implements Serializable {
  private String name;

  @OneToMany(mappedBy = "type", cascade = CascadeType.MERGE)
  @PrimaryKeyJoinColumn
  private List<Element> elements;
}
