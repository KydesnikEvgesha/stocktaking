package ru.kravchenkoei.stocktaking.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(
    name = "specification_value",
    indexes = {
      @Index(name = "idx_specificationvalue", columnList = "specification_id, element_id, value")
    })
public class SpecificationValue extends AbstractModel {
  @ManyToOne
  @JoinColumn(name = "specification_id")
  private Specification specification;

  @ManyToOne
  @JoinColumn(name = "element_id")
  private Element element;

  @Lob
  @Column(name = "value")
  private String value;
}
