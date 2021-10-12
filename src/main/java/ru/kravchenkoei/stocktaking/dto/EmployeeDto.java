package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

@Value
public class EmployeeDto {

  private Long id;

  private String name;
  private String email;
  private String titleJob;
  private String phone;
}
