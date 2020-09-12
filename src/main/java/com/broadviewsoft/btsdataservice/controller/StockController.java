package com.broadviewsoft.btsdataservice.controller;

import com.broadviewsoft.btsdataservice.model.Stock;
import com.broadviewsoft.btsdataservice.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bts/api/v1/stocks")
public class StockController {
    @Autowired
    private StockRepository repository;

    @GetMapping
    public List<Stock> list() {
            return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Stock get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    public Stock create(@RequestBody final Stock stock){
        return repository.saveAndFlush(stock);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public Stock update(@PathVariable Long id, @RequestBody Stock stock) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Stock existingStock = repository.getOne(id);
        BeanUtils.copyProperties(stock, existingStock, "id");
        return repository.saveAndFlush(existingStock);
    }

}
