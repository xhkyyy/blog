package design_pattern.observer;

import org.junit.Test;

/**
 * Created by Len on 29/01/2017.
 */
public class ObserverTest {

	@Test
	public void test(){
		ConcreteSubject subject = new ConcreteSubject();
		Observer observer = new ConcreteObserver();

		subject.addObserver(observer);
		subject.change("new State");
	}

}
