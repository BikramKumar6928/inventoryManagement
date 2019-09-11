package com.example.inventoryManagement.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class OrderDetails extends ProductCountList{

	private Supplier supplier;



	public OrderDetails(Supplier supplier, Owner owner, List<ProductCount> products) {
		super(owner,products);
		this.supplier = supplier;
	}



	public void printOrderDetails(){
		System.out.println();
	}

	public Supplier getSupplier() {
		return supplier;
	}


}
