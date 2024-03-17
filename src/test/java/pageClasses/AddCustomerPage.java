package pageClasses;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class AddCustomerPage {
WebDriver driver;
public AddCustomerPage(WebDriver driver)
{
	this.driver=driver;
	
}
//define Repository
@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
WebElement ClickcustomerLink;
@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
WebElement ClickAddIconButton;
@FindBy(name = "x_Customer_Number")
WebElement CustomerNumber;
@FindBy(name = "x_Customer_Name")
WebElement CustomerName;
@FindBy(name = "x_Address")
WebElement Address;
@FindBy(name = "x_City")
WebElement City;
@FindBy(name = "x_Country")
WebElement Country;
@FindBy(name = "x_Contact_Person")
WebElement Contactperson;
@FindBy(name = "x_Phone_Number")
WebElement phoneNumber;
@FindBy(name = "x__Email")
WebElement Email;
@FindBy(name = "x_Mobile_Number")
WebElement MobileNumber;
@FindBy(name = "x_Notes")
WebElement Notes;
@FindBy(id ="btnAction")
WebElement ClickAddbutton;
@FindBy(xpath ="//button[normalize-space()='OK!']")
WebElement clickconformOk;
@FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
WebElement clickAlertOk;
@FindBy(xpath = "//span[@data-caption='Search']")
WebElement clickSearchPanel;
@FindBy(xpath = "//input[@name='psearch']")
WebElement EnterSearchTextbox;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement clickSearchbutton;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
WebElement webtable;

public boolean add_customer(String cname, String Address, String city, String country, String cperson, String pnumber,
		String email, String mnumber, String notes) throws Throwable
{
    Actions ac=new Actions(driver);
	ac.moveToElement(ClickcustomerLink).click().perform();
	ac.moveToElement(this.ClickAddIconButton).click().perform();
	String Exp_Data = this.CustomerNumber.getAttribute("value");
	this.CustomerName.sendKeys(cname);
	this.Address.sendKeys(Address);
	this.City.sendKeys(city);
	this.Country.sendKeys(country);
	this.Contactperson.sendKeys(cperson);
	this.phoneNumber.sendKeys(pnumber);
	this.Email.sendKeys(email);
	this.MobileNumber.sendKeys(mnumber);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,250)", "");
	this.Notes.sendKeys(notes);
	
	this.ClickAddbutton.click();
	//ac.moveToElement(ClickAddbutton).click().perform();
	Thread.sleep(3000);
	this.clickconformOk.click();
	Thread.sleep(3000);
	this.clickAlertOk.click();
	if(!this.EnterSearchTextbox.isDisplayed())
	this.clickSearchPanel.click();
	this.EnterSearchTextbox.clear();
	this.EnterSearchTextbox.sendKeys(Exp_Data);
	ac.moveToElement(clickSearchbutton).click().perform();
	String Act_data= webtable.getText();
	if(Exp_Data.equals(Act_data))
	{
		Reporter.log("customer number matching::"+Exp_Data+"  "+Act_data,true);
		return true;
	}
	else
	{
		Reporter.log("customer number Not matching::"+Exp_Data+"  "+Act_data,true);
		return false;
	}
	
}


}
