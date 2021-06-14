/**
 * 
 */
package com.praveen.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.stockmanagement.domains.Stock;

/**
 * @author Madan
 *
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

}
