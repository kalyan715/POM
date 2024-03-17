package driverFactory;

import org.testng.annotations.Test;
import java.sql.Driver;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.AppUtil;
import pageClasses.AddCustomerPage;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath="./FileInput\\CustomerData.xlsx";
	String outputpath="./FileOutput/PomResults.xlsx";
	String TestData="Customer";
	ExtentReports report;
	ExtentTest logger;
	
	
	@Test
	public void startTest() throws Throwable
	{
		report= new ExtentReports("./target/Reports/customer.html");
		ExcelFileUtil x1=new ExcelFileUtil(inputpath);
		int rc=x1.rowcount(TestData);
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = report.startTest("Validate customer");
			String customername=x1.getCellData(TestData, i, 0);
			String Address=x1.getCellData(TestData, i, 1);
			String city=x1.getCellData(TestData, i, 2);
			String country=x1.getCellData(TestData, i, 3);
			String cperson=x1.getCellData(TestData, i, 4);
			String pnumber=x1.getCellData(TestData, i, 5);
			String email=x1.getCellData(TestData, i, 6);
			String mnumber=x1.getCellData(TestData, i, 7);
			String notes=x1.getCellData(TestData, i, 8);
			logger.log(LogStatus.INFO, customername+"===="+ Address+"===="+city+"===="+country+"===="+cperson+"===="+pnumber+"===="+ email+"===="+mnumber+"===="+notes );
			AddCustomerPage cus= PageFactory.initElements(driver, AddCustomerPage.class);
			boolean res=cus.add_customer(customername, Address, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				x1.setCellData(TestData, i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "New customer Added success");
				
			}
			else
			{
				x1.setCellData(TestData, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "New customer Added Fail");
			}
			report.endTest(logger);
			report.flush();
			
			
		}
	}

}
