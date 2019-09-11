package com.example.inventoryManagement.service;

import com.example.inventoryManagement.beans.*;
import com.example.inventoryManagement.repository.IssueRepository;
import com.example.inventoryManagement.repository.OrderRepository;
import com.example.inventoryManagement.repository.OwnerRepository;
import com.example.inventoryManagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private IssueRepository issueRepository;

	public String requestBuyStock(ProductCount productCount, Owner owner, Supplier supplier, OrderDetails orderDetails){
		List<ProductCount> productCounts = new ArrayList<>();
		productCounts.add(productCount);
		return requestBuyStock(productCounts,owner,supplier,orderDetails);
	}

	public String requestBuyStock(List<ProductCount> productCounts, Owner owner, Supplier supplier, OrderDetails orderDetails){
		if(orderDetails == null){
			orderDetails = orderRepository.save(new OrderDetails(supplier,owner,productCounts));
		}
		orderDetails.addProducts(productCounts);
		orderDetails = orderRepository.save(orderDetails);
		return orderDetails.getId();
	}

	public void sellStock(ProductCountList productCountList) {
		Owner owner = productCountList.getOwner();
		List<ProductCount> productCount = productCountList.getProducts();
		double totalAmount = productCountList.getTotalAmount();
		owner.removeStocks(productCount);
		owner.incrementBankBalance(totalAmount);
		synchronized (ownerRepository){
			ownerRepository.save(owner);
		}
	}


	public void paySupplier(OrderDetails orderDetails){
		double totalAmount = orderDetails.getTotalAmount();
		Supplier supplier = orderDetails.getSupplier();
		Owner owner = orderDetails.getOwner();

		supplier.incrementBankBalance(totalAmount);
		owner.decrementBankBalance(totalAmount);
		synchronized (supplierRepository){
			supplierRepository.save(supplier);
		}
		synchronized (ownerRepository){
			ownerRepository.save(owner);
		}
	}



	public void returnStock(OrderDetails orderDetails){
		double totalAmount = orderDetails.getTotalAmount();
		Owner owner = orderDetails.getOwner();
		Supplier supplier = orderDetails.getSupplier();
		List<ProductCount> itemList = orderDetails.getProducts();

		owner.removeStocks(itemList);
		supplier.addStocks(itemList);

		owner.incrementBankBalance(totalAmount);
		supplier.decrementBankBalance(totalAmount);

		synchronized (supplierRepository){
			supplierRepository.save(supplier);
		}
		synchronized (ownerRepository){
			ownerRepository.save(owner);
		}
	}


	public void reportIssue(Issue issue){
		synchronized (issueRepository) {
			issueRepository.save(issue);
		}

	}



}
