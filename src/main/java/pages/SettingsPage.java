package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import util.JsonDataReader;

public class SettingsPage extends BasePage {

	public SettingsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	



public boolean checkuserEmail()
{
	String expectedemail= JsonDataReader.getValue("email");
	String email= getText(MobileBy.id("nl.moboa.myclay:id/user_email"));
	
	return expectedemail.equals(email)?true:false;
	
	}



}
