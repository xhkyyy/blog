package design_pattern.strategy;

/**
 * Created by Len on 01/02/2017.
 */
public class HappyHourStrategy implements BillingStrategy {
	@Override
	public double getActPrice(double rawPrice) {
		return rawPrice * 0.5;
	}
}
