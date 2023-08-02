package com.upp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.upp.base.Constants;

public class ExcelReader {

	public static String excelFilePath = System.getProperty("user.dir")
			+ "//src//main//resources//upp-automation-testdata.xlsx";
	// public static String excelFilePath
	// =Thread.currentThread().getContextClassLoader().getResource("upp-automation-testdata.xlsx").getFile();

//	public  URL pathSource =this.getClass().getResource("upp-automation-testdata.xlsx");
//	InputStream is = getClass().getClassLoader().getResourceAsStream("upp-automation-testdata.xlsx");
//	URL resource = getClass().getClassLoader().getResource("upp-automation-testdata.xlsx");
	public static int rowNum;
	public static Sheet sheet;
	public static FileInputStream inputStream;

	public void init() throws EncryptedDocumentException, IOException {
		if (false) {

		} else {

			WorkbookFactory.create(new File(excelFilePath));

		}
	}

	public String getFieldData(String TSID, String worksheetName, String fieldName)
			throws InvalidFormatException, IOException {
		inputStream = new FileInputStream(excelFilePath);
		sheet = getWorkBook(excelFilePath).getSheet(worksheetName);
		rowNum = ExcelReader.findrownum(worksheetName, TSID);

		String cellData = null;
		XSSFRow row = (XSSFRow) sheet.getRow(0);
		int column_Number = 0;
		XSSFCell cell;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(fieldName))
				column_Number = i;
			cell = (XSSFCell) sheet.getRow(rowNum).getCell(column_Number);
			if (cell.getCellType() == CellType.STRING) {
				cellData = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				cellData = cell.getNumericCellValue() + "";
			}
		}
		inputStream.close();
		return cellData;

	}

	public String getFieldData(String excelFilePath, String worksheetName, String fieldName, int k)
			throws InvalidFormatException, IOException {
		inputStream = new FileInputStream(excelFilePath);
		sheet = getWorkBook(excelFilePath).getSheet(worksheetName);
//		rowNum = ExcelReader.findrownum(worksheetName, TSID);
		rowNum = k;
		String cellData = null;
		XSSFRow row = (XSSFRow) sheet.getRow(0);
		int column_Number = 0;
		XSSFCell cell;
		for (int i = 0; i < row.getLastCellNum(); i++) {

			if (row.getCell(i).getStringCellValue().trim().equals(fieldName)) {
				column_Number = i;
			}

			cell = (XSSFCell) sheet.getRow(rowNum).getCell(column_Number);

			if (cell.getCellType() == CellType.STRING) {
				cellData = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				cellData = cell.getNumericCellValue() + "";
			} else if (cell.getCellType() == CellType.FORMULA) {
				cellData = cell.getNumericCellValue() + "";
			}
		}
		inputStream.close();
		return cellData;

	}

	public static int findrownum(String workSheetName, String TSID) {

//    	try {
//			sheet = getWorkBook(excelFilePath).getSheet(workSheetName);
//		} catch (Exception e) {
//				} 
		boolean check = true;
		int i = 1;
		while (check) {
			Row rowH = sheet.getRow(i);
			Cell cell = rowH.getCell(0);
			String cellvalue = null;
		
			if (cell.getCellType() == CellType.STRING) {
				cellvalue = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				cellvalue = cell.getNumericCellValue() + "";
			}
			
			if (cellvalue.equalsIgnoreCase(TSID)) {
				check = false;
			} else {
				i = i + 1;
			}
		}

		return i;
	}

	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
			throws InvalidFormatException, IOException {
		Sheet sheet = getSheetByName(excelFilePath, sheetName);
		return readSheet(sheet);
	}

	public List<Map<String, String>> getData(String excelFilePath, int sheetNumber)
			throws InvalidFormatException, IOException {
		Sheet sheet = getSheetByIndex(excelFilePath, sheetNumber);
		return readSheet(sheet);
	}

	private Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
		Sheet sheet = getWorkBook(excelFilePath).getSheet(sheetName);
		return sheet;
	}

	private Sheet getSheetByIndex(String excelFilePath, int sheetNumber) throws IOException, InvalidFormatException {
		Sheet sheet = getWorkBook(excelFilePath).getSheetAt(sheetNumber);
		return sheet;
	}

	private static Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
		return WorkbookFactory.create(inputStream);
	}

	private List<Map<String, String>> readSheet(Sheet sheet) {
		Row row;
		int totalRow = sheet.getPhysicalNumberOfRows();
		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		int headerRowNumber = getHeaderRowNumber(sheet);
		if (headerRowNumber != -1) {
			int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
			int setCurrentRow = 0;
			if (sheet instanceof HSSFSheet) {
				setCurrentRow = 1;
			}
			for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
				row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
				LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
				}
				excelRows.add(columnMapdata);
			}
		}
		return excelRows;
	}

	private int getHeaderRowNumber(Sheet sheet) {
		Row row;
		int totalRow = sheet.getLastRowNum();
		for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
			row = getRow(sheet, currentRow);
			if (row != null) {
				int totalColumn = row.getLastCellNum();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					Cell cell;
					cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if (cell.getCellType() == CellType.STRING) {
						return row.getRowNum();

					} else if (cell.getCellType() == CellType.NUMERIC) {
						return row.getRowNum();

					} else if (cell.getCellType() == CellType.BOOLEAN) {
						return row.getRowNum();
					} else if (cell.getCellType() == CellType.ERROR) {
						return row.getRowNum();
					}
				}
			}
		}
		return (-1);
	}

	private Row getRow(Sheet sheet, int rowNumber) {
		return sheet.getRow(rowNumber);
	}

	private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
		LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
		Cell cell;
		if (row == null) {
			if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.getCellType() != CellType.BLANK) {
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();
				columnMapdata.put(columnHeaderName, "");
			}
		} else {
			cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			if (cell.getCellType() == CellType.STRING) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, cell.getStringCellValue());
				}
			} else if (cell.getCellType() == CellType.NUMERIC) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
				}
			} else if (cell.getCellType() == CellType.BLANK) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, "");
				}
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
				}
			} else if (cell.getCellType() == CellType.ERROR) {
				if (sheet.getRow(sheet.getFirstRowNum())
						.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
						.getCellType() != CellType.BLANK) {
					String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
							.getStringCellValue();
					columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
				}
			}
		}
		return columnMapdata;
	}

	public void writeDataToExcel(String fileName, String sheetName, int rownum, String columnHeader, Object data)
			throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		FileOutputStream fos = null;
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow row = null;
		XSSFCell cell = null;
		int col_Num = -1;
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(columnHeader)) {
				col_Num = i;
			}
		}

		row = sheet.getRow(rownum);
		if (row == null)
			row = sheet.createRow(rownum);

		cell = row.getCell(col_Num);
		if (cell == null)
			cell = row.createCell(col_Num);

		cell.setCellValue((String) data);

		fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
	}

	public String getFieldData_From_DownloadedExcel(String filename, String TSID, String worksheetName,
			String fieldName) throws InvalidFormatException, IOException {

		inputStream = new FileInputStream(filename);
		sheet = getWorkBook(filename).getSheet(worksheetName);
		rowNum = ExcelReader.findrownum(worksheetName, TSID);

		String cellData = null;
		XSSFRow row = (XSSFRow) sheet.getRow(0);
		int column_Number = 0;
		XSSFCell cell;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(fieldName))
				column_Number = i;
			cell = (XSSFCell) sheet.getRow(rowNum).getCell(column_Number);
			if (cell.getCellType() == CellType.STRING) {
				cellData = cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				cellData = cell.getNumericCellValue() + "";
			}
		}
		inputStream.close();
		return cellData;

	}

	public static int getRowCount(String excelFilePath, String sheetName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		workbook.close();
		fileInputStream.close();
		return rowCount;
	}
}