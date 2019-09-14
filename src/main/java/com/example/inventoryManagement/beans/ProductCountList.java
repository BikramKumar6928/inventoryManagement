package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.TrackStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class ProductCountList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	@OneToOne
	private Owner owner;
	@OneToMany(cascade=CascadeType.ALL, targetEntity=ProductCount.class)
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
	
	public void setId(String id){
		this.id = id;
	}
	
	public TrackStatus getTrackStatus() {
		return trackStatus;
	}

	public void setProducts(List<ProductCount> products) {
		this.products = products;
	}

	public void setTrackStatus(TrackStatus trackStatus) {
		this.trackStatus = trackStatus;
	}
}
