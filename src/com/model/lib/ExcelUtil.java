package com.model.lib;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 *  ����excel��
 * @author Admin
 *
 */
public class ExcelUtil {
	
	public static XSSFSheet excelSheet;
	public static XSSFWorkbook excelBook;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	/**
	 *  ����excel
	 * @param path excel�ļ�·��
	 */
	public static void setExcelFile(String path) {
		FileInputStream excelFile;
		
		try {
			excelFile = new FileInputStream(path);
			excelBook = new XSSFWorkbook(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ��ȡexcel�ж�Ӧ��Ԫ���ֵ
	 * @param rownum �У���0��ʼ��
	 * @param cellnum �У���0��ʼ��
	 * @param sheetName sheet��
	 * @return
	 */
	public static String getCellData(int rownum,int cellnum,String sheetName) {
		excelSheet = excelBook.getSheet(sheetName);
		cell = excelSheet.getRow(rownum).getCell(cellnum);
		String cellData = cell.getStringCellValue();
		return cellData;
	}
	
	/**
	 *  �����Խ��д��excel
	 * @param result ���Խ��
	 * @param rownum �У���0��ʼ��
	 * @param cellnum �У���0��ʼ��
	 * @param path excel�ļ�·��
	 * @param sheetName sheet��
	 */
	public static void setCellData(String result,int rownum,int cellnum,String path,String sheetName) {
		try {
			excelSheet = excelBook.getSheet(sheetName);
			row = excelSheet.getRow(rownum);
			cell = row.getCell(cellnum);
			if (cell == null) {
				cell = row.createCell(cellnum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			excelBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			excelBook = new XSSFWorkbook(new FileInputStream(path));
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * ��ȡexcel��sheet�����һ��
	 * @param sheetName
	 * @return
	 */
	public static int getLastRownum(String sheetName) {
		int row = 0;
		try {
			excelSheet = excelBook.getSheet(sheetName);
			row = excelSheet.getLastRowNum();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return row;
	}
	
 
}
