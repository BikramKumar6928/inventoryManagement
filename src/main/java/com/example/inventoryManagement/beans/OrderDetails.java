package com.example.inventoryManagement.beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderDetails extends ProductCountList{

	@Id
	private String id;
	@OneToOne
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
