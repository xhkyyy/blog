package design_pattern.proxy;

/**
 * Created by Len on 28/01/2017.
 */
public class RealImage implements Image {
	@Override
	public void display() {
		System.out.println("Real Image!");
	}
}
