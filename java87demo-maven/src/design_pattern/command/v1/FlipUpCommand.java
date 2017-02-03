package design_pattern.command.v1;

/**
 * Created by Len on 03/02/2017.
 */
public class FlipUpCommand implements Command {

	private Light theLight;

	public FlipUpCommand(Light theLight) {
		this.theLight = theLight;
	}

	@Override
	public void execute() {
		theLight.turnOn();
	}

}
