package com.straltsou.currencyconversionservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionBean {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiplier;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private int port;
}
