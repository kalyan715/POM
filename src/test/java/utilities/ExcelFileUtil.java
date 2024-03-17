package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.reporters.jq.Main;

public class ExcelFileUtil 
{

	Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi=new FileInputStream(Excelpath);
		wb= WorkbookFactory.create(fi);
	}
	// method for counting no of rows in a sheet
	public int rowcount(String SheetName) 
	{
		return wb.getSheet(SheetName).getLastRowNum();
		
	}
	// method for reading cell data
	public String getCellData(String SheetName,int row,int column)
	{
		String data="";
		if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata= (int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
		
	}
		
	//method for writing cell data
	public void setCellData(String SheetName, int row, int column, String status, String WriteExcel) throws Throwable
	{
		//get sheet from wb
		Sheet ws=wb.getSheet(SheetName);
		//get row from sheet
		Row rownum=ws.getRow(row);
		//create cell
		Cell cell= rownum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font= wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font= wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);	
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style=wb.createCellStyle();
			Font font= wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream(WriteExcel);
		wb.write(fo);
		
	}
	
	
}
