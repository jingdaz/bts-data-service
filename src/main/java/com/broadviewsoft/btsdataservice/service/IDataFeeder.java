package com.broadviewsoft.btsdataservice.service;

import java.util.Date;
import java.util.List;

import com.broadviewsoft.btsdataservice.model.Period;
import com.broadviewsoft.btsdataservice.model.PriceType;
import com.broadviewsoft.btsdataservice.model.StockItem;


public interface IDataFeeder {
	public StockItem getYesterdayItem(String symbol, int index);

	public List<StockItem> getHistoryData(String symbol, Period period, Date cutTime);

	public StockItem getItemByIndex(String symbol, Period period, int index);

	public double getPriceByIndex(String symbol, Period period, int index, PriceType type);

	// FIXME StockItem timestamp cannot be null and mins with size > 1
	public double getPrice(String symbol, Date timestamp, Period period, PriceType type);

	public int getCurItemIndex(String symbol, Date curTime, Period period);
}
