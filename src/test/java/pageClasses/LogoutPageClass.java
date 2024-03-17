package pageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPageClass {
	
	@FindBy(xpath = "(//a[starts-with(text(),' Logout')])[2]")
	WebElement objLogout;
	
	public void adminLogout()
	{
		objLogout.click();
	}

}
