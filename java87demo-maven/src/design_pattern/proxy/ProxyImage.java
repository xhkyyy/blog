package design_pattern.proxy;

/**
 * Created by Len on 28/01/2017.
 */
public class ProxyImage implements Image {

	public ProxyImage() {
		this.realImage = new RealImage();
	}

	private RealImage realImage = null;


	@Override
	public void display() {
		System.out.println("Proxy Image");
		realImage.display();
	}
}
