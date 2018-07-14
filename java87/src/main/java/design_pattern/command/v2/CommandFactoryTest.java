package design_pattern.command.v2;

import org.junit.Test;

/**
 * Created by Len on 03/02/2017.
 */
public class CommandFactoryTest {

	@Test
	public void test(){
		final CommandFactory cf = CommandFactory.init();
		cf.executeCommand("Light on");
		cf.listCommands();
	}

}
