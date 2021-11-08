package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

@Value
public class EmployeeDto {

  private Long id;

  private String firstName;
  private String secondName;
  private String email;
  private String titleJob;
  private String phone;
}
