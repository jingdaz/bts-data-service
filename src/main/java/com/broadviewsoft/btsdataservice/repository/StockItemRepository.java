package com.broadviewsoft.btsdataservice.repository;

import com.broadviewsoft.btsdataservice.model.Period;
import com.broadviewsoft.btsdataservice.model.StockItem;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	
	@Query("select max(timestamp) from StockItem item where item.stock.symbol = :symbol and item.period = :period")
	public Date findLastCreated(@Param("symbol") String symbol, @Param("period") Period period);
}