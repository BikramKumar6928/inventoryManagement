package beans;

import java.util.List;

public class OrderDetails {
	private String id;
	private Supplier supplier;
	private Owner owner;
	private List<ProductCount> products;
	private double totalAmount = 0;
	private long totalProducts = 0;
	private boolean isUpdated = false;


	public OrderDetails(Supplier supplier, Owner owner, List<ProductCount> products) {
		this.supplier = supplier;
		this.owner = owner;
		this.products = products;

		updateMembers();
	}

	private void updateMembers() {
		if(isUpdated){
			return;
		}
		isUpdated = true;
		products.forEach(product ->{
			totalAmount += product.getPrice();
			totalProducts += product.getCount();
		});
	}


	public void printOrderDetails(){
		System.out.println();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Owner getOwner() {
		return owner;
	}

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
}
