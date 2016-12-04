package copyjava;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Len on 04/12/2016.
 */
public class Sub<W1,W2> {
	public Sub() {
		Type[] types = getClass().getGenericInterfaces();
		if(types != null){
			for (Type type : types) {
				if(type instanceof ParameterizedType){
					//System.out.println("getGenericInterfaces=" + type + ", instanceof=" + (type instanceof ParameterizedType));
					ParameterizedType pt = (ParameterizedType) type;
					System.out.println("getRawType=" + pt.getRawType());
					Type[] at = pt.getActualTypeArguments();
					System.out.println("getActualTypeArguments=" + at[0].getTypeName());
					System.out.println("getActualTypeArguments=" + at[1].getTypeName());
				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
