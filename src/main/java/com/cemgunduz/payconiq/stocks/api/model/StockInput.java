package com.cemgunduz.payconiq.stocks.api.model;

import com.cemgunduz.payconiq.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cem on 26/12/17.
 *
 * Stock input pojo, used to create and update a new stock
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class StockInput {

    private String name;
    private BigDecimal currentPrice;
    private Long lastUpdate;

    public void validate()
    {
        if(lastUpdate != null && lastUpdate > System.currentTimeMillis()){
            throw new BadRequestException("Last update timestamp can not be bigger than now");
        }

        if(currentPrice != null && currentPrice.doubleValue() < 0){
            throw new BadRequestException("A stock price can not be negative");
        }
    }
}
