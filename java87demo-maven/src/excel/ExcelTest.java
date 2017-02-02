package excel;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import java.util.List;

/**
 * Created by Len on 02/02/2017.
 */
public class ExcelTest {

	@Test
	public void test1(){
		AbExcelParser parser = POIExcelParser.getInstance();

		List<ERow> eRowList = parser.parseExcel("/Users/Len/Desktop/excel_demo.xlsx", 0);
		printERow(eRowList);
	}

	@Test
	public void test2(){
		AbExcelParser parser = POIExcelParser.getInstance();

		List<ERow> eRowList = parser.parseExcel("/Users/Len/Desktop/Workbook1.xls", 0);
		printERow(eRowList);

	}

	private void printERow(List<ERow> eRowList){
		if(CollectionUtils.isEmpty(eRowList)){
			System.out.println("Excel is empty!");
		} else {
			for (ERow eRow : eRowList) {
				for (ECell eCell : eRow.geteCellList()) {
					System.out.print(eCell.getIndex() + "-" + eCell.getContent() + "   ");
				}
				System.out.println("");
			}
		}
		System.out.println("\n\n\n");
	}

}
