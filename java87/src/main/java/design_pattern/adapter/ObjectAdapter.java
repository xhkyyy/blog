package design_pattern.adapter;

/**
 * Created by Len on 28/01/2017.
 */
public class ObjectAdapter implements Target {
	private Adaptee adaptee;

	public ObjectAdapter() {
		adaptee = new Adaptee();
	}

	@Override
	public void request() {
		System.out.println("ObjectAdapter");
		adaptee.specificRequest();
	}
}
