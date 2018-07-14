package design_pattern.observer;

/**
 * Created by Len on 29/01/2017.
 */
public class ConcreteObserver implements Observer {

	private String observerState;

	@Override
	public void update(String state) {
		observerState = state;
		System.out.println("state = [" + observerState + "]");
	}
}
