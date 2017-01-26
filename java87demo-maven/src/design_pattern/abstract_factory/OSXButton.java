package design_pattern.abstract_factory;

/**
 * Created by Len on 26/01/2017.
 */
public class OSXButton implements IButton {
	@Override
	public void paint() {
		System.out.println("OSX Button");
	}
}
