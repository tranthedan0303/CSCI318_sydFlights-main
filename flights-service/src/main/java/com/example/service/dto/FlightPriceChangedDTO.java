package com.example.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightPriceChangedDTO {
    private Long flightId;
    private BigDecimal newPrice;
}
