package com.broadviewsoft.btsdataservice.model;

import com.broadviewsoft.btsdataservice.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StockTest {
    @Autowired
    private StockRepository repository;

    @Test
    public void test() throws Exception {
        List<Stock> stocks = repository.findAll();
        assertTrue(stocks.size() > 0);
    }

}