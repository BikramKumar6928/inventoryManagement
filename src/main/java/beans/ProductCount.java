package beans;

public class ProductCount {
		private Product product;
		private int count;

		public ProductCount(Product product, int count) {

			this.product = product;
			this.count = count;
		}

		public double getPrice(){
			return product.getPrice();
		}

		public double getCount(){
			return count;
		}

		public String getProductId(){
			return this.product.getId();
		}

}
