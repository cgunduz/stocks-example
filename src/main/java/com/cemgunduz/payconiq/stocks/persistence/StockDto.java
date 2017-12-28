package com.cemgunduz.payconiq.stocks.persistence;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cem on 26/12/17.
 *
 * Dto form of stock model.
 */
@Data
@Entity
@Table(name = "stock")
public class StockDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal currentPrice;

    // TODO : Timestamp demis ama ?
    private Long lastUpdate;
}
