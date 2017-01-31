package design_pattern.decorator;

/**
 * Created by Len on 31/01/2017.
 */
public class WithMilk extends CoffeeDecorator {
	public WithMilk(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	@Override
	public double getCost() {
		return super.getCost() + 0.7;
	}

	@Override
	public String getIngredients() {
		return super.getIngredients() + ", Milk";
	}
}
