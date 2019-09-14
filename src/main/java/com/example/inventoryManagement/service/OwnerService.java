package com.example.inventoryManagement.service;

import com.example.inventoryManagement.beans.*;
import com.example.inventoryManagement.enums.TrackStatus;
import com.example.inventoryManagement.exceptions.LesserItemsInInventory;
import com.example.inventoryManagement.repository.IssueRepository;
import com.example.inventoryManagement.repository.OrderRepository;
import com.example.inventoryManagement.repository.OwnerRepository;
import com.example.inventoryManagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	

	public String requestBuyStock(OrderDetails orderDetails){
		orderDetails.setTrackStatus(TrackStatus.PROCESSING);
		synchronized (orderRepository){
			orderDetails = orderRepository.save(orderDetails);
		}
		return orderDetails.getId();
	}

	public void sellStock(ProductCountList productCountList) {
		Owner owner = productCountList.getOwner();
		List<ProductCount> productCount = productCountList.getProducts();
		double totalAmount = productCountList.getTotalAmount();
		removeStocks(owner,productCount);
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

		removeStocks(owner,itemList);
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
	
	public void trackOrder(OrderDetails orderDetails){
		orderDetails.printOrderDetails();
	}


	public ProductCount getStockWithId(Owner owner,String productId) {
		Owner owner1 = ownerRepository.findById(owner.getId()).orElse(null);
		if(owner1 == null){
			return null;
		}
		return owner1.getStocks().stream()
				.filter(stock ->{
					return stock.getProductId() == productId;
				})
				.findAny()
				.orElse(null);

	}

	public int getCountWithProductId(Owner owner,String productId) {
		ProductCount productCount = getStockWithId(owner,productId);
		if(productCount == null){
			return 0;
		}
		return productCount.getCount();
	}



	private void checkItemsAvailable(Owner owner,List<ProductCount> productCount) {
		boolean areItemsAvailable = productCount.stream().filter(productCount1 -> {
			ProductCount stock = getStockWithId(owner,productCount1.getProductId());
			return (
					stock != null &&
							stock.getCount() > productCount1.getCount()
			);
		}).findAny().orElse(null) == null;

		if (!areItemsAvailable){
			throw new LesserItemsInInventory();
		}
	}

	public void removeStocks(Owner owner,List<ProductCount> itemList){
		checkItemsAvailable(owner,itemList);
		List<ProductCount> updatedStocks =
				owner.getStocks().stream().
						map(productCount1 -> {
							int originalCount =  getCountWithProductId(owner,productCount1.getProductId());
							productCount1.setCount(originalCount - productCount1.getCount());
							return productCount1;
						})
						.collect(Collectors.toList());
		owner.setStocks(updatedStocks);
	}

	public void addStocks(Owner owner,List<ProductCount> itemList){
		List<ProductCount> updatedStocks =
				owner.getStocks().stream().
						map(productCount1 -> {
							int originalCount =  getCountWithProductId(owner,productCount1.getProductId());
							productCount1.setCount(originalCount + productCount1.getCount());
							return productCount1;
						})
						.collect(Collectors.toList());
		owner.setStocks(updatedStocks);
	}


//	public void addStocks(Owner owner, List<ProductCount> productCounts){
//
//	}
	public List<OrderDetails> getStocksWithStatus(Owner owner, TrackStatus status) {
		List<OrderDetails> ownerOrders = orderRepository.findAllByOwner(owner.getId());
		List<OrderDetails> statusOrders = ownerOrders.stream().filter(orderDetails -> {
			return orderDetails.getTrackStatus() == status;
		}).collect(Collectors.toList());
		return statusOrders;
	}


}
