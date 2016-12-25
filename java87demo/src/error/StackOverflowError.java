package error;

/**
 * Created by Len on 25/12/2016.
 */
public class StackOverflowError {

	private byte i = 0;

	public void inc(byte b){
		i += b;
		inc(i);
	}

	public static void main(String[] args) {

		StackOverflowError stackOverflowError = new StackOverflowError();
		stackOverflowError.inc((byte) 1);

	}
}
