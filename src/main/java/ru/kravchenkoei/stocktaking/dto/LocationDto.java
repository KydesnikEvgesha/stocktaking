package ru.kravchenkoei.stocktaking.dto;

import lombok.Value;

@Value
public class LocationDto {
    private Long id;

    private String nameOffice;
    private String address;
}
