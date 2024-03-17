package pageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageClass {
	
	//define Repository for Login
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement objReset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement objuser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement objpass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objLogin;
	
	//method for login
	public void adminLogin(String username,String password)
	{
		objReset.click();
		objuser.sendKeys(username);
		objpass.sendKeys(password);
		objLogin.click();
	}
}
