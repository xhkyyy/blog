package excel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 02/02/2017.
 */
public class ERow {

	private final List<ECell> eCellList;

	public ERow() {
		eCellList = new ArrayList<ECell>();
	}

	public List<ECell> geteCellList() {
		return eCellList;
	}

	public void addECell(ECell eCell) {
		eCellList.add(eCell);
	}
}
