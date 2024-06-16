package ignore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;



public class ExcelFileHandle {

	public static void main(String[] args) throws Exception {
		FileInputStream a = new FileInputStream("");
		XSSFWorkbook book = new XSSFWorkbook(a);
		Sheet sheet =	book.getSheet("");
		for(Row row : sheet) {
			
			for(Cell cell: row) {
				
			if(	cell.getCellType()==CellType.STRING) {
				cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				cell.getNumericCellValue();
			}
				
			}
		}

	}

}
