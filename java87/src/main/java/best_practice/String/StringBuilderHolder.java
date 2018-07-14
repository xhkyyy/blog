package best_practice.String;

/**
 * Created by Len on 02/02/2017.
 */
public class StringBuilderHolder {

	private static ThreadLocal<StringBuilder> globalStringBuilder = new ThreadLocal<StringBuilder>(){

		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder(512);
		}

	};

	private ThreadLocal<StringBuilder> stringBuilder = new ThreadLocal<StringBuilder>(){
		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder(initSize);
		}
	};

	private int initSize;

	public StringBuilderHolder(int initSize) {
		this.initSize = initSize;
	}

	public static StringBuilder getGlobal(){
		StringBuilder sb = globalStringBuilder.get();
		sb.setLength(0);
		return sb;
	}

	public StringBuilder get(){
		StringBuilder sb = stringBuilder.get();
		sb.setLength(0);
		return sb;
	}
}
