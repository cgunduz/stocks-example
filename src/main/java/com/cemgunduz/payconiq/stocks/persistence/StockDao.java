package com.cemgunduz.payconiq.stocks.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cem on 26/12/17.
 */
public interface StockDao extends JpaRepository<StockDto, Long> {
}
