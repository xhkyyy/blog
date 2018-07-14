package design_pattern.strategy;

import org.junit.Test;

/**
 * Created by Len on 01/02/2017.
 */
public class StrategyTest {

	@Test
	public void test(){
		Customer customer = new Customer(new NormalStrategy());
		customer.add(1.0, 1);

		customer.setStrategy(new HappyHourStrategy());
		customer.add(1.0, 1);

		customer.setStrategy(new HappyHourStrategy());
		customer.add(2.0, 1);

		customer.printBill();

	}

}
