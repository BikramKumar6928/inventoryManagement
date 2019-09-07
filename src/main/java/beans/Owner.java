package beans;

import java.util.List;

public class Owner {
	private String name;
	private double money;
	private List<StockAmount> stocks;
	private Manager manager;

	class StockAmount{
		Product product;
		int number;
	}
}

