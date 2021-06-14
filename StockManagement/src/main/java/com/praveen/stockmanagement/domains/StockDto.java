/**
 * 
 */
package com.praveen.stockmanagement.domains;

import java.io.Serializable;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author Naveen
 *
 */
public class StockDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1017336091276442995L;
	private Long id;
	private String name;
	private String quantity;
	private String productnumber;
	private String price;
	private String description;
	private MultipartFile stockFile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductnumber() {
		return productnumber;
	}
	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getStockFile() {
		return stockFile;
	}
	public void setStockFile(MultipartFile stockFile) {
		this.stockFile = stockFile;
	}
	
	
}
