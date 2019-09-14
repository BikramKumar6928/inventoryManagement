package com.example.inventoryManagement.beans;

import com.example.inventoryManagement.enums.TrackStatus;
import com.example.inventoryManagement.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private String id;
	@OneToOne
	private Owner owner;

	public Manager(Owner owner){
		this.owner = owner;
	}


	@Autowired
	OwnerService ownerService;

	public void maintainLedger(){
//		List<ProductCount> delivered = ownerService.getStocksWithStatus(owner,TrackStatus.DELIVERED);

	}

	public boolean qualityCheck(){
		return true;
	}

	public void updateStatus(){

	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
