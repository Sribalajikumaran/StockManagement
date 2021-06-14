/**
 * 
 */
package com.praveen.stockmanagement.services;

import java.util.List;

/**
 * @author Madan
 *
 */
public interface CrudService<T,ID> {
	
	List<T> findAll();
	T findById(ID id);
	T save(T t);
	void delete(T t);
	void deleteById(ID id);

}
