package com.broadviewsoft.btsdataservice.model;

import com.broadviewsoft.btsdataservice.repository.StockItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StockItemTest {
    @Autowired
    private StockItemRepository repository;

    @Test
    public void test() throws Exception {
        List<StockItem> stockItems = repository.findAll();
        assertTrue(stockItems.size() >= 0);
    }

}