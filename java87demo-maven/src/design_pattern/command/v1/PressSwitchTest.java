package design_pattern.command.v1;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Len on 03/02/2017.
 */
public class PressSwitchTest {

	Light lamp;

	@Before
	public void before1(){
		lamp = new Light();
	}


	@Test
	public void test1(){
		final Command switchUp = new FlipUpCommand(lamp);
		final Switch mySwitch = new Switch();
		mySwitch.storeAndExecute(switchUp);

	}


	@Test
	public void test2(){
		final Command switchDown = new FlipDownCommand(lamp);
		final Switch mySwitch = new Switch();
		mySwitch.storeAndExecute(switchDown);
	}


}
