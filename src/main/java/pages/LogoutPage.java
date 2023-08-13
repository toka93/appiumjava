package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import util.JsonDataReader;

public class LogoutPage extends BasePage {

	public LogoutPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	


public boolean checkLogoutMessage()
{
	String expectedMessage= JsonDataReader.getValue("logoutMessage");
	String logoutMessage= getText(MobileBy.id("nl.moboa.myclay:id/login_info"));
	
	return expectedMessage.equals(logoutMessage)?true:false;
	
	}



}
