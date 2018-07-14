package design_pattern.observer;


/**
 * 具体的主体角色
 * Created by Len on 29/01/2017.
 */
public class ConcreteSubject extends Subject {

	private String state;

	public String getState() {
		return state;
	}

	public void change(String newState){
		state = newState;
		System.out.println("newState = [" + newState + "]");
		nodifyObservers(newState);
	}
}
