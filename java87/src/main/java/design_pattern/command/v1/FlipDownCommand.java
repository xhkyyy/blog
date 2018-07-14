package design_pattern.command.v1;

/**
 * Created by Len on 03/02/2017.
 */
public class FlipDownCommand implements Command {

	private Light theLight;

	public FlipDownCommand(Light theLight) {
		this.theLight = theLight;
	}

	@Override
	public void execute() {
		theLight.turnOff();
	}
}
