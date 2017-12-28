package com.cemgunduz.payconiq.stocks.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cem on 26/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class StockInput {

    private String name;
    private BigDecimal currentPrice;

    // TODO : Timestamp demis ama ?
    private Long lastUpdate;

    // TODO : Buraya validator eklenmeli mi ?
}
