package design_pattern.bridge;

/**
 * Created by Len on 04/02/2017.
 */
public abstract class Shape {

	protected DrawingAPI drawingAPI;

	protected Shape(final DrawingAPI drawingAPI) {
		this.drawingAPI = drawingAPI;
	}

	public abstract void draw();

	public abstract void resizeByPercentage(final double pct);
}
