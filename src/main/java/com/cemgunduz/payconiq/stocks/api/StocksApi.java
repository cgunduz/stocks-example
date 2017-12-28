package com.cemgunduz.payconiq.stocks.api;

import com.cemgunduz.payconiq.stocks.api.model.StockInput;
import com.cemgunduz.payconiq.stocks.api.model.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cem on 26/12/17.
 *
 * Stock api that currently allows users to view, create and update stocks.
 * Follows restful conventions and acts as an entrypoint for stock related crud operations
 */
@RequestMapping({"/stocks"})
@RestController
public interface StocksApi {

    /**
     * Method used to retrieve all the stocks in the system
     *
     * @return List<Stock>
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    List<Stock> getStocks();

    /**
     * Method used to retrieve single stock by id
     *
     * @exception com.cemgunduz.payconiq.exception.StockNotFoundException if no stock with given id is found
     * @param stockId Numeric id of the stock
     * @return Stock
     */
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{stockId}"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    Stock getStockById(@PathVariable Long stockId);

    /**
     * Method used to update the information of a single stock by id
     *
     * @exception com.cemgunduz.payconiq.exception.StockNotFoundException if no stock with given id is found
     * @param stockId Numeric id of the stock
     * @param stockInput Changeset of the stock information
     * @return Stock
     */
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/{stockId}"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    Stock updateStocksById(@PathVariable Long stockId, @RequestBody @Valid StockInput stockInput);

    /**
     * Method to add new stocks to the system
     *
     * @param stockInput Information containing the fields of the newly added stock
     * @return Long
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    Long postStocks(@RequestBody @Valid StockInput stockInput);
}
