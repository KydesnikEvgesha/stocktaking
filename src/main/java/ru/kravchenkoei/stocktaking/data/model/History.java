package ru.kravchenkoei.stocktaking.data.model;

import lombok.Getter;
import lombok.Setter;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Getter
@Setter
public class History extends AbstractModel {

    @Lob
    @Column(name = "old_value")
    private String oldValue;

    @Lob
    @Column(name = "new_value")
    private String newValue;

    @ManyToOne
    @JoinColumn(name = "specification_value_id")
    private SpecificationValue specificationValue;

}