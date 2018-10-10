package com.csv.to.xls.CsvToXlsx;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

public class ConvertCsvToXlsx {

	private static final char FILE_DELIMITER = ',';
	private static final String FILE_EXTN = ".xlsx";

	public static void main(String[] args) throws IOException {
		String csvPath = "C:\\Users\\Downloads\\Import_User_Sample_en.csv";
		SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = (SXSSFSheet) workBook.createSheet("sheet1");
		// XSSFWorkbook workBook=new XSSFWorkbook();
		// XSSFSheet sheet=workBook.createSheet("sheet1");
		String[] nextLine = null;
		int rowNum = 0;
		CSVReader reader = new CSVReader(new FileReader(csvPath), FILE_DELIMITER);
		while ((nextLine = reader.readNext()) != null) {
			Row currentRow = sheet.createRow(rowNum++);
			for (int i = 0; i < nextLine.length; i++) {
				if (NumberUtils.isDigits(nextLine[i])) {
					currentRow.createCell(i).setCellValue(Integer.parseInt(nextLine[i]));
				} else if (NumberUtils.isNumber(nextLine[i])) {
					currentRow.createCell(i).setCellValue(Double.parseDouble(nextLine[i]));
				} else {
					currentRow.createCell(i).setCellValue(nextLine[i]);
				}

			}

		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMMM");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		System.out.println("today's date " + todayDate);
		final String FILE_NaME = "sample_Excel_" + todayDate;
		System.out.println("file name " + FILE_NaME);
		String xlsxPath = "C:\\Users\\Downloads\\" + FILE_NaME + FILE_EXTN;
		FileOutputStream ouputFile = new FileOutputStream(xlsxPath.trim());
		workBook.write(ouputFile);
		ouputFile.close();
		System.out.println("created");
	}

}
