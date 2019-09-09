package beans;

import java.util.Date;

public class Product {
	private String id;
	private double price;
	private Date manufactureDate;
	private Date expiryDate;
	private Quality quality;

	public Product(double price, Date manufactureDate, Date expiryDate, Quality quality) {
		this.price = price;
		this.manufactureDate = manufactureDate;
		this.expiryDate = expiryDate;
		this.quality = quality;
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public Quality getQuality() {
		return quality;
	}
}
