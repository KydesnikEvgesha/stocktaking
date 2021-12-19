package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Element extends AbstractModel {

  private String name;

  @ManyToOne private Company company;

  @ManyToOne private Type type;

//  @OneToMany(mappedBy = "element")
//  private List<Specification> specificationList;
//
//  @ManyToOne @PrimaryKeyJoinColumn private Workstation workstation;
}
