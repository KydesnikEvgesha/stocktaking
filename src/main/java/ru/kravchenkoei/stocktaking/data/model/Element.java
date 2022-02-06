package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Element extends AbstractModel {

  private String name;

  @ManyToOne private Company company;

  @ManyToOne private Type type;

  @ManyToOne @PrimaryKeyJoinColumn private Workstation workstation;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "element", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<SpecificationValue> specificationValues = new LinkedHashSet<>();
}
