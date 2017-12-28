package com.cemgunduz.payconiq.stocks.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cem on 26/12/17.
 *
 * Stock object in its business object form, also capitalized on response payload. Can later be separated once there is
 * business logic and an associated service layer
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Stock {

    private Long id;
    private String name;
    private BigDecimal currentPrice;
    private Long lastUpdate;
}
