package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

@Value
public class CompanyDto {
  private Long id;

  private String name;
  private String address;
}
