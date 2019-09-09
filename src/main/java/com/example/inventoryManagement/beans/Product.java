package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.Quality;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private double price;
	private Date manufactureDate;
	private Date expiryDate;
	private Quality quality;

	public Product(double price, Date manufactureDate, Date expiryDate, Quality quality) {
		this.price = price;
		this.manufactureDate = manufactureDate;
		this.expiryDate = expiryDate;
		this.quality = quality;
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public Quality getQuality() {
		return quality;
	}
}
