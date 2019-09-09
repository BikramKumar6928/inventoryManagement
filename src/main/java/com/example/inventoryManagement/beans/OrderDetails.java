package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.TrackStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class OrderDetails extends ProductCountList{
	private Supplier supplier;
	private TrackStatus trackStatus;


	public OrderDetails(Supplier supplier, Owner owner, List<ProductCount> products) {
		super(owner,products);
		this.supplier = supplier;
	}
	public OrderDetails(Supplier supplier, Owner owner, List<ProductCount> products,TrackStatus trackStatus) {
		super(owner,products);
		this.supplier = supplier;
		this.trackStatus = trackStatus;
	}

	public void printOrderDetails(){
		System.out.println();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public TrackStatus getTrackStatus() {
		return trackStatus;
	}

	public void setTrackStatus(TrackStatus trackStatus) {
		this.trackStatus = trackStatus;
	}
}
