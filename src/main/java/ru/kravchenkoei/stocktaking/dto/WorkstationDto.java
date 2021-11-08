package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

import java.util.List;

@Value
public class WorkstationDto {
  private Long id;

  private String name;

  private EmployeeDto employee;
  private LocationDto location;

  private List<ElementDto> elementList;
}
