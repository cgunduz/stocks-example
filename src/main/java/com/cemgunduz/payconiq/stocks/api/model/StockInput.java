package com.cemgunduz.payconiq.stocks.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cem on 26/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StockInput {

    private String name;
    private BigDecimal currentPrice;

    // TODO : Timestamp demis ama ?
    private Long lastUpdate;
}
