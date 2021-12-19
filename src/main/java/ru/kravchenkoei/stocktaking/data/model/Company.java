package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company extends AbstractModel {

  private String name;
  private String address;

  @OneToMany(mappedBy = "company")
  @PrimaryKeyJoinColumn
  private List<Element> elements;
}
