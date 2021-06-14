/**
 * 
 */
package com.praveen.stockmanagement.services.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.stockmanagement.domains.Stock;
import com.praveen.stockmanagement.repository.StockRepository;
import com.praveen.stockmanagement.services.StockService;

/**
 * @author Madan
 *
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository repository;

	@Override
	public List<Stock> findAll() {
		return repository.findAll();
	}

	@Override
	public Stock findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Stock save(Stock t) {
		return repository.save(t);
	}

	@Override
	public void delete(Stock t) {
		repository.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void saveStockList(List<Stock> stockList) {
		repository.saveAll(stockList);
	}
		
		
}

