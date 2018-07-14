package design_pattern.decorator;

import org.junit.Test;

/**
 * Created by Len on 31/01/2017.
 */
public class DecoratorTest {

	public static void printInfo(Coffee c) {
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
	}

	@Test
	public void test(){
		Coffee c = new SimpleCoffee();
		printInfo(c);

		c = new WithMilk(c);
		printInfo(c);

	}

}
