/**
 * 
 */
package com.praveen.stockmanagement.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Madan
 *
 */
@Entity
@Table(name="stock")
public class Stock implements Serializable {

	private static final long serialVersionUID = 6606116934773300931L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String quantity;
	@Column
	private String productnumber;
	@Column
	private String price;
	@Column
	private String description;
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
	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", quantity=" + quantity + ", productnumber=" + productnumber
				+ ", price=" + price + ", description=" + description + "]";
	}
	

}
