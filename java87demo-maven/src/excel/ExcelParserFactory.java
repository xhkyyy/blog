package excel;

/**
 * Created by Len on 03/02/2017.
 */
public class ExcelParserFactory {

	public static AbExcelParser getExcelParser(){
		return POIExcelParser.getInstance();
	}

}
