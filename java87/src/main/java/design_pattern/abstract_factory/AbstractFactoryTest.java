package design_pattern.abstract_factory;

import org.junit.Test;

/**
 * Created by Len on 26/01/2017.
 */
public class AbstractFactoryTest {

	public static String randomAppearance() {
		int len = 2;
		String[] appearanceArr = new String[len];
		appearanceArr[0] = "osx";
		appearanceArr[1] = "windows";
		java.util.Random rand = new java.util.Random();
		int randNum = rand.nextInt(len);
		return appearanceArr[randNum];
	}

	@Test
	public void test() {
		IGUIFactory factory = null;

		String appearance = randomAppearance();//current operating system
		if (appearance.equals("osx")) {
			factory = new OSXFactory();
		} else if(appearance.equals("windows")) {
			factory = new WinFactory();
		}

		IButton button = factory.createButton();
		button.paint();
	}

}
