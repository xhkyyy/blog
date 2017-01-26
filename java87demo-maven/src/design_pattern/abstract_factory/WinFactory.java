package design_pattern.abstract_factory;

/**
 * Created by Len on 26/01/2017.
 */
public class WinFactory implements IGUIFactory {
	@Override
	public IButton createButton() {
		return new WinButton();
	}
}
