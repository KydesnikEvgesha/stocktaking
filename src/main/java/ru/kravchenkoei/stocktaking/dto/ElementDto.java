package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

import java.util.List;

@Value
public class ElementDto {
  private Long id;

  private String name;

  private CompanyDto company;

  private TypeDto type;

  private List<SpecificationDto> specificationList;
}
