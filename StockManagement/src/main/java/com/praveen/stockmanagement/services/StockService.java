/**
 * 
 */
package com.praveen.stockmanagement.services;

import java.util.List;

import com.praveen.stockmanagement.domains.Stock;

/**
 * @author Madan
 *
 */
public interface StockService extends CrudService<Stock, Long> {
	
	void saveStockList(List<Stock> stockList);

}
