package excel;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Len on 02/02/2017.
 */
public class POIExcelParser extends AbExcelParser {

	private POIExcelParser() {
	}

	private static volatile POIExcelParser poiExcelParser;

	public static POIExcelParser getInstance() {
		if (poiExcelParser == null) {
			synchronized (POIExcelParser.class) {
				if (poiExcelParser == null) {
					poiExcelParser = new POIExcelParser();
				}
			}
		}

		return poiExcelParser;
	}

	@Override
	protected List<ERow> parseXls(String filePath, int sheetIndex) {
		FileInputStream file = null;
		List<ERow> eRowList = null;
		try {
			file = new FileInputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			eRowList = parse(sheet);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return eRowList;
	}

	@Override
	protected List<ERow> parseXlsx(String filePath, int sheetIndex) {
		FileInputStream file = null;
		List<ERow> eRowList = null;
		try {
			file = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			eRowList = parse(sheet);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return eRowList;
	}

	private List<ERow> parse(Sheet sheet) {
		List<ERow> eRowList = new ArrayList<>();
		int cellIndex = 0;
		String value;
		for (Row row : sheet) {
			Iterator<Cell> cellIterator = row.cellIterator();
			ERow eRow = new ERow();
			eRowList.add(eRow);

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellTypeEnum()) {
					case NUMERIC:
						value = parseNumeric(cell);
						break;
					case FORMULA:
						value = parseFormulaResult(cell);
						break;
					default:
						value = cell.toString();
				}

				eRow.addECell(new ECell(++cellIndex, value));
			}

			cellIndex = 0;
		}
		return eRowList;
	}

	private String parseNumeric(Cell cell) {
		String value;
		if (DateUtil.isCellDateFormatted(cell)) {
			DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
			sdf.setTimeZone(LocaleUtil.getUserTimeZone());
			value = sdf.format(cell.getDateCellValue());
		} else {
			value = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
		}
		return value;
	}


	private String parseFormulaResult(Cell cell) {
		String cellContent = null;
		switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cellContent = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
				break;
			case Cell.CELL_TYPE_STRING:
				cellContent = String.valueOf(cell.getRichStringCellValue());
				break;
		}
		return cellContent;
	}

}
