package com.cemgunduz.payconiq.stocks.api;

import com.cemgunduz.payconiq.stocks.api.model.StockInput;
import com.cemgunduz.payconiq.stocks.api.model.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cem on 26/12/17.
 */

@RequestMapping({"/stocks"})
@RestController
public interface StocksApi {

    @RequestMapping(method = {RequestMethod.GET}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    List<Stock> getStocks();

    @RequestMapping(method = {RequestMethod.GET}, value = {"/{stockId}"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    Stock getStockById(@PathVariable Long stockId);

    @RequestMapping(method = {RequestMethod.PUT}, value = {"/{stockId}"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    Stock updateStocksById(@PathVariable Long stockId, @RequestBody @Valid StockInput stockInput);

    @RequestMapping(method = {RequestMethod.POST}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    Long postStocks(@RequestBody @Valid StockInput stockInput);
}
