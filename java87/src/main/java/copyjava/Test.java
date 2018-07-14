package copyjava;

/**
 * Created by Len on 03/12/2016.
 */
public class Test {

	public static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;

		System.out.println(n);

		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= 9999999) ? 9999999 : n + 1;
	}


	public static void main(String[] args) {

		/*
		System.out.println( tableSizeFor(17) );

		System.out.println( (131071 >>> 1) );
		*/

		Par p = new Par();

	}

}