package design_pattern.bridge;

/**
 * Created by Len on 04/02/2017.
 */
public class CircleShape extends Shape {

	private double x, y, radius;

	public CircleShape(final DrawingAPI drawingAPI, final double x, final double y, final double radius) {
		super(drawingAPI);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public void draw() {
		drawingAPI.drawCircle(x, y ,radius);
	}

	public void resizeByPercentage(double pct) {
		radius *= (1.0 + pct / 100.0);
	}
}
