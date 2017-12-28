package com.cemgunduz.payconiq;

import com.cemgunduz.payconiq.exception.StockNotFoundException;
import com.cemgunduz.payconiq.stocks.api.StocksApiImpl;
import com.cemgunduz.payconiq.stocks.api.model.Stock;
import com.cemgunduz.payconiq.stocks.api.model.StockInput;
import com.cemgunduz.payconiq.stocks.persistence.StockDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by cem on 27/12/17.
 * <p>
 * Test coverage for the Stock Api
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("it")
@SpringBootTest(classes = {StocksApplication.class})
@TestPropertySource(value = "classpath:application-it.yml")
public class StockCrudTest {

    private static final String TEST_STOCK_NAME = "TestStock";

    @Autowired
    StocksApiImpl stocksApi;

    @Autowired
    StockDao stockDao;

    @Before
    public void clear() {
        stockDao.deleteAll();
    }

    @Test
    public void fetch() {
        List<Stock> stockList = stocksApi.getStocks();
        Assert.assertTrue(stockList.size() == 0);
    }

    @Test
    public void saveOneFetchOne() {

        postTestStock();

        List<Stock> stocks = stocksApi.getStocks();
        Assert.assertEquals(stocks.size(), 1);
        Assert.assertEquals(stocks.get(0).getName(), TEST_STOCK_NAME);
    }

    @Test(expected = StockNotFoundException.class)
    public void getByWrongId() {
        stocksApi.getStockById(1L);
    }

    @Test
    public void getById() {
        String newStockName = "TestStock";
        StockInput transientStock = createStock(newStockName);
        Long id = stocksApi.postStocks(transientStock);

        Stock detachedStock = stocksApi.getStockById(id);
        Assert.assertEquals(transientStock.getName(), detachedStock.getName());
        Assert.assertEquals(transientStock.getCurrentPrice(), detachedStock.getCurrentPrice());
        Assert.assertEquals(transientStock.getLastUpdate(), detachedStock.getLastUpdate());
    }

    @Test
    public void update() {

        Long id = postTestStock();
        Stock originalStock = stocksApi.getStockById(id);
        Assert.assertEquals(originalStock.getName(), TEST_STOCK_NAME);

        StockInput input = createStock();
        String differentName = "DifferentName";
        input.setName(differentName);

        stocksApi.updateStocksById(id, input);

        Stock updatedStock = stocksApi.getStockById(id);
        Assert.assertEquals(updatedStock.getName(), differentName);
    }

    private StockInput createStock() {
        return createStock(UUID.randomUUID().toString());
    }

    private Long postTestStock() {
        StockInput stockInput = createStock(TEST_STOCK_NAME);
        return stocksApi.postStocks(stockInput);
    }

    private StockInput createStock(String stockName) {
        return StockInput.builder()
                .name(stockName)
                .currentPrice(new BigDecimal(100.25))
                .lastUpdate(System.currentTimeMillis())
                .build();
    }
}
