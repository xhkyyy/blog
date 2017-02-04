package design_pattern.bridge;

/**
 * Created by Len on 04/02/2017.
 */
public class DrawingAPI1 implements DrawingAPI{
	public void drawCircle(double x, double y, double radius) {
		System.out.println("x = [" + x + "], y = [" + y + "], radius = [" + radius + "]");
	}
}
