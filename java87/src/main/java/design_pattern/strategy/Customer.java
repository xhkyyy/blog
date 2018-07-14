package design_pattern.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 01/02/2017.
 */
public class Customer {

	private List<Double> drinks;

	private BillingStrategy strategy;

	public Customer(BillingStrategy strategy) {
		this.strategy = strategy;
		this.drinks = new ArrayList<>();
	}

	public void add(final double price, final int quantity){
		drinks.add(strategy.getActPrice(price * quantity));
	}

	public void printBill(){
		double sum = 0;
		for (Double drink : drinks) {
			sum += drink;
		}
		System.out.println("Total due:" + sum);
		drinks.clear();
	}

	public void setStrategy(final BillingStrategy strategy){
		this.strategy = strategy;
	}


}
