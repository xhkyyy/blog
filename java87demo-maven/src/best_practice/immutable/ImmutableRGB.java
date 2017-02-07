package best_practice.immutable;

import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;

/**
 * Created by Len on 07/02/2017.
 */
public final class ImmutableRGB {

	private final int red;

	private final int green;

	private final int blue;

	private final String name;

	private final MutableObj mutableObj;

	public ImmutableRGB(int red, int green, int blue, String name, MutableObj mutableObj) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.name = name;
		this.mutableObj = mutableObj;
	}

	public ImmutableRGB invert() {
		return new ImmutableRGB(255 - red,
				255 - green,
				255 - blue,
				"Inverse of " + name,
				//对象的深度拷贝
				new MutableObj(new ArrayList<>(this.mutableObj.getList()))
		);
	}

	public int getREG() {
		return ((red << 16) | (green << 8) | blue);
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public String getName() {
		return name;
	}
}
