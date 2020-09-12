package com.broadviewsoft.btsdataservice.controller;

import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.repository.StockItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bts/api/v1/stockitems")
public class StockItemController {
    @Autowired
    private StockItemRepository repository;

    @GetMapping
    public List<StockItem> list() {
            return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public StockItem get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    public StockItem create(@RequestBody final StockItem stockItem){
        return repository.saveAndFlush(stockItem);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public StockItem update(@PathVariable Long id, @RequestBody StockItem stockItem) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        StockItem existingStockItem = repository.getOne(id);
        BeanUtils.copyProperties(stockItem, existingStockItem, "id");
        return repository.saveAndFlush(existingStockItem);
    }

}
