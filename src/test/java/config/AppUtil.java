package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageClasses.LoginPageClass;
import pageClasses.LogoutPageClass;

public class AppUtil {
	public static Properties conpro;
	public static WebDriver driver;
	
	@BeforeTest
	public static void setup() throws Throwable
	{
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoginPageClass login= PageFactory.initElements(driver,LoginPageClass.class);
			login.adminLogin("admin","master");
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.name("")).click();
			LoginPageClass login= PageFactory.initElements(driver,LoginPageClass.class);
			login.adminLogin("admin","master");
		}
	}
	
@AfterTest
public static void tearDown()
{
	LogoutPageClass logout=PageFactory.initElements(driver,LogoutPageClass.class);
	logout.adminLogout();
	driver.quit();
}
}
