package design_pattern.adapter;

/**
 * Created by Len on 28/01/2017.
 */
public class ClassAdapter extends Adaptee implements Target {

	@Override
	public void request() {
		System.out.println("ClassAdapter.");
		super.specificRequest();
	}

}
