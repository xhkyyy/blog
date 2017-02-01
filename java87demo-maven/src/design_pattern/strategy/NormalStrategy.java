package design_pattern.strategy;

/**
 * Created by Len on 01/02/2017.
 */
public class NormalStrategy implements BillingStrategy {
	@Override
	public double getActPrice(double rawPrice) {
		return rawPrice;
	}
}
