package com.cemgunduz.payconiq.stocks.api;

import com.cemgunduz.payconiq.stocks.api.model.StockInput;
import com.cemgunduz.payconiq.exception.StockNotFoundException;
import com.cemgunduz.payconiq.stocks.persistence.StockDao;
import com.cemgunduz.payconiq.stocks.api.model.Stock;
import com.cemgunduz.payconiq.stocks.persistence.StockDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by cem on 26/12/17.
 * <p>
 * Implementation class for the Stocks Api.
 */
@RestController
@Transactional
public class StocksApiImpl implements StocksApi {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Stock> getStocks(@RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        PageRequest pageRequest = new PageRequest(from, size);
        return stockDao.findAll(pageRequest).getContent().stream()
                .map(stockDto -> modelMapper.map(stockDto, Stock.class))
                .collect(Collectors.toList());
    }

    @Override
    public Stock getStockById(@PathVariable Long stockId) {

        StockDto stockDto = findById(stockId);
        return modelMapper.map(stockDto, Stock.class);
    }

    @Override
    public Stock updateStocksById(@PathVariable Long stockId, @RequestBody @Valid StockInput stockInput) {

        stockInput.validate();

        StockDto detachedStock = findById(stockId);
        modelMapper.map(stockInput, detachedStock);
        stockDao.save(detachedStock);

        return modelMapper.map(detachedStock, Stock.class);
    }

    @Override
    public Stock postStocks(@RequestBody @Valid StockInput stockInput) {

        stockInput.validate();

        StockDto stockDto = modelMapper.map(stockInput, StockDto.class);
        return modelMapper.map(stockDao.save(stockDto), Stock.class);
    }

    private StockDto findById(Long stockId) {
        return Optional.ofNullable(stockDao.findOne(stockId)).orElseThrow(() ->
                new StockNotFoundException("The requested stock with the given id : "
                        .concat(stockId.toString())
                        .concat(" is not found")));
    }
}
