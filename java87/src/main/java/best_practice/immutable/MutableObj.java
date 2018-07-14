package best_practice.immutable;

import java.util.List;

/**
 * Created by Len on 07/02/2017.
 */
public class MutableObj {

	private List<String> list;

	public MutableObj(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}
}
