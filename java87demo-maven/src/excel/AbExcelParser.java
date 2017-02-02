package excel;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 02/02/2017.
 */
public abstract class AbExcelParser {

	private final String EXCEL_SUFFIX_XLS = ".xls";

	private final String EXCEL_SUFFIX_XLSX = ".xlsx";

	protected abstract List<ERow> parseXls(String filePath, int sheetIndex);

	protected abstract List<ERow> parseXlsx(String filePath, int sheetIndex);

	public List<ERow> parseExcel(String filePath, int sheetIndex) {
		if (StringUtils.isBlank(filePath) ||
				(!filePath.endsWith(EXCEL_SUFFIX_XLS) && !filePath.endsWith(EXCEL_SUFFIX_XLSX)) ||
				sheetIndex < 0 ||
				sheetIndex >= Integer.MAX_VALUE) {
			return new ArrayList<>(0);
		}

		if(filePath.endsWith(EXCEL_SUFFIX_XLS)){
			return parseXls(filePath, sheetIndex);
		}

		return parseXlsx(filePath, sheetIndex);
	}

}
