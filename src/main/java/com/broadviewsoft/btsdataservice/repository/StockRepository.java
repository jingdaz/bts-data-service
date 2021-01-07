package com.broadviewsoft.btsdataservice.repository;

import com.broadviewsoft.btsdataservice.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
	Stock findBySymbol(String symbol);
}