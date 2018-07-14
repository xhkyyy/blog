package design_pattern.bridge;

import org.junit.Test;

/**
 * Created by Len on 04/02/2017.
 */
public class BridgeTest {

	@Test
	public void test(){
		Shape[] shapes = new Shape[]{
			new CircleShape(new DrawingAPI1(), 1, 2, 3),
			new CircleShape(new DrawingAPI2(), 5, 7, 11)
		};

		for (Shape shape : shapes) {
			shape.resizeByPercentage(2.5);
			shape.draw();
		}
	}

}
