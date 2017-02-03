package design_pattern.command.v2;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by Len on 03/02/2017.
 */
public class CommandFactory {

	private final HashMap<String, CommandV2> commands;

	public CommandFactory() {
		this.commands = new HashMap<>();
	}

	public void addCommand(final String name, final CommandV2 command){
		commands.put(name, command);
	}

	public void executeCommand(String name){
		if(commands.containsKey(name)){
			commands.get(name).apply();
		}
	}

	public void listCommands(){
		System.out.println("Enable Command: " + commands.keySet().stream().collect(Collectors.joining(", ")));
	}

	public static CommandFactory init(){
		final CommandFactory cf = new CommandFactory();

		cf.addCommand("Light on", () -> System.out.println("Light turned on"));
		cf.addCommand("Light off", () -> System.out.println("Light turned off"));

		return cf;
	}
}
