package com.broadviewsoft.btsdataservice.repository;

import com.broadviewsoft.btsdataservice.model.StockItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	
}