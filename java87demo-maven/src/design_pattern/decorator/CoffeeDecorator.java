package design_pattern.decorator;

/**
 * Created by Len on 31/01/2017.
 */
public class CoffeeDecorator implements Coffee {

	protected final Coffee decoratedCoffee;

	public CoffeeDecorator(Coffee decoratedCoffee) {
		this.decoratedCoffee = decoratedCoffee;
	}

	@Override
	public double getCost() {
		return decoratedCoffee.getCost();
	}

	@Override
	public String getIngredients() {
		return decoratedCoffee.getIngredients();
	}
}
