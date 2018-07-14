package design_pattern.decorator;

/**
 * Created by Len on 31/01/2017.
 */
public class SimpleCoffee implements Coffee {
	@Override
	public double getCost() {
		return 1;
	}

	@Override
	public String getIngredients() {
		return "Coffee";
	}
}
