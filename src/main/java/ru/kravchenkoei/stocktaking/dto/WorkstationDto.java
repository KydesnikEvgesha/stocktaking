package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;
import ru.kravchenkoei.stocktaking.model.Employee;
import ru.kravchenkoei.stocktaking.model.Location;

@Value
public class WorkstationDto {
    private Long id;


    private Employee employee;
    private Location location;

}
