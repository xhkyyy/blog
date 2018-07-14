package best_practice.clone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 07/02/2017.
 */
public class ListCloneTest {

	@Test
	public void test1() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		Integer i1 = 1000;
		Integer i1_1 = 1000;

		System.out.println(i1 == i1_1);

		List<Integer> list = new ArrayList<>();
		list.add(i1);
		list.add(i1_1);

		List<Integer> cloneList = new ArrayList<>();

		CollectionUtils.addAll(cloneList, list.iterator());

		System.out.println(list.get(0) == cloneList.get(0));

	}

}
