package com.cemgunduz.payconiq.stocks.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cem on 26/12/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Stock {

    private Long id;
    private String name;
    private BigDecimal currentPrice;

    // TODO : Timestamp demis ama ?
    private Long lastUpdate;
}
