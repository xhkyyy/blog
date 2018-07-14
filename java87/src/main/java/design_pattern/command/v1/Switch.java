package design_pattern.command.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * The invoke class
 * Created by Len on 03/02/2017.
 */
public class Switch {

	private List<Command> history = new ArrayList<>();

	public void storeAndExecute(final Command command){
		this.history.add(command);
		command.execute();
	}

}
