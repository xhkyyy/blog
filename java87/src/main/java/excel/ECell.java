package excel;

/**
 * Created by Len on 02/02/2017.
 */
public class ECell {

	private int index;

	private String content;

	public ECell(int index, String content) {
		this.index = index;
		this.content = content;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
