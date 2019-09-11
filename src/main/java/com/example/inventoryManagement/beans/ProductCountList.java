package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.TrackStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class ProductCountList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private Owner owner;
	private List<ProductCount> products;
	private double totalAmount = 0;
	private long totalProducts = 0;
	private TrackStatus trackStatus;
	private boolean isUpdated = false;
	
	public ProductCountList(Owner owner, List<ProductCount> products,TrackStatus trackStatus) {
		this.owner = owner;
		this.products = products;
		
		updateMembers();
	}

	public ProductCountList(Owner owner, List<ProductCount> products) {
		this(owner,products,TrackStatus.PROCESSING);
	}

	private void updateMembers() {
		if(isUpdated){
			return;
		}
		isUpdated = true;
		products.forEach(product ->{
			totalAmount += product.getPrice() * product.getCount();
			totalProducts += product.getCount();
		});
	}


	public void printOrderDetails(){
		System.out.println();
	}

	public Owner getOwner() {
		return owner;
	}

//	Add products after checking the IDs
	public void addProducts(ProductCount productCount){
		products.add(productCount);
	}

	public void addProducts(List<ProductCount> productCounts){
		products.addAll(productCounts);
	}

	public List<ProductCount> getProducts() {
		return products;
	}

	public double getTotalAmount() {
		updateMembers();
		return totalAmount;
	}

	public long getTotalProducts() {
		updateMembers();
		return totalProducts;
	}

	public String getId() {
		return id;
	}
	
	public TrackStatus getTrackStatus() {
		return trackStatus;
	}
	
	public void setTrackStatus(TrackStatus trackStatus) {
		this.trackStatus = trackStatus;
	}
}
