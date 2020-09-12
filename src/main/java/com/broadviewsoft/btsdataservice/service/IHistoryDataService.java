package com.broadviewsoft.btsdataservice.service;

import java.util.List;

import com.broadviewsoft.btsdataservice.model.DataException;
import com.broadviewsoft.btsdataservice.model.DataFileType;
import com.broadviewsoft.btsdataservice.model.Period;
import com.broadviewsoft.btsdataservice.model.StockItem;

/**
 * Interface to load stock data 
 * 
 * @author Jason
 *
 */
public interface IHistoryDataService {
	public List<StockItem> loadData(String symbol, Period period, DataFileType type)
			throws DataException;
	
//	public List<StockItem> loadData(String csvFilename,
//			DataFileType type) throws DataException;
}
