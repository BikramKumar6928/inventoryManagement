package service;

import beans.OrderDetails;
import beans.Owner;
import beans.ProductCount;
import beans.Supplier;

import java.util.ArrayList;
import java.util.List;

public class OwnerService {

	public String requestBuyStock(ProductCount productCount, Owner owner, Supplier supplier, OrderDetails orderDetails){
		List<ProductCount> productCounts = new ArrayList<>();
		productCounts.add(productCount);
		return requestBuyStock(productCounts,owner,supplier,orderDetails);
	}

	public String requestBuyStock(List<ProductCount> productCounts, Owner owner, Supplier supplier, OrderDetails orderDetails){
		if(orderDetails == null){
			orderDetails = new OrderDetails(supplier,owner,new ArrayList<>());
		}
		orderDetails.addProducts(productCounts);

		return orderDetails.getId();
	}

	public void sellStock(Owner owner,ProductCount productCount){
		List<ProductCount> productCounts = new ArrayList<>();
		productCounts.add(productCount);
		sellStock(owner,productCounts);
	}

	public void sellStock(Owner owner, List<ProductCount> productCount){
		owner.sellProducts(productCount);
	}



	public void paySupplier(OrderDetails orderDetails){

	}

	public void returnStock(OrderDetails orderDetails){

	}

	public void reportIssue(OrderDetails orderDetails){

	}



}
