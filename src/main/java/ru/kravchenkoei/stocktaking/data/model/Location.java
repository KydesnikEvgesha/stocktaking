package ru.kravchenkoei.stocktaking.data.model;

import lombok.Data;
import ru.kravchenkoei.stocktaking.data.AbstractModel;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Location extends AbstractModel implements Serializable {

    private String nameOffice;
    private String address;

//    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Workstation workstation;
}
