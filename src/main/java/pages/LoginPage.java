package pages;

import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import util.JsonDataReader;

public class LoginPage extends BasePage {

	public LoginPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void tapOnCont() {
		tap(MobileBy.id("nl.moboa.myclay:id/login_button"));
		// inputText(MobileBy.id("nl.moboa.myclay:id/input"), "tototoot");
		// return this;
	}

	public void enterUser() {
		String email = JsonDataReader.getValue("email");
		inputText(MobileBy.id("nl.moboa.myclay:id/input"), email);
	}

	public void acceptTerms() {
		tap(MobileBy.id("com.android.chrome:id/terms_accept"));

	}

	public void noSync() {
		tap(MobileBy.id("com.android.chrome:id/negative_button"));

		// com.android.chrome:id/negative_button
	}

	public void enterPassword() {
		String pass = JsonDataReader.getValue("password");
		inputText(MobileBy.id("Password"), pass);
	}

	public void submit() {
		tap(MobileBy.id("SubmitButton"));
		// com.android.chrome:id/negative_button
	}

	public void waituntiluserredirectstowebview() {
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement((MobileBy.id("nl.moboa.myclay:id/login_button")))));

	}

	public boolean changeView(String view) {
		Set<String> contextNames = driver.getContextHandles();
		for (String context : contextNames) {
			log.info(context);
			if (context.contains(view)) {
				driver.context(context);
				return true;
			}
		}

		return false;

	}

	public String getSitesText() throws InterruptedException {
		wait(5000);
		return getText(MobileBy.id("nl.moboa.myclay:id/textView"));

	}

	public int getSites() {

		return getListSize(MobileBy.xpath("//*/android.view.ViewGroup[@content-desc]"));
	}

	public void chooseLastSite() throws InterruptedException {

		wait(5000);
		taponElementByIndex(MobileBy.xpath("//*/android.view.ViewGroup[@content-desc]"), 5);
		wait(5000);

	}

	public void closeSites() {

		tap(MobileBy.id("nl.moboa.myclay:id/close_button"));

	}

	public boolean validateWelcomeMessage() {
		String expectedwelcome = JsonDataReader.getValue("welcomeMessage");
		String message = getText(MobileBy.id("nl.moboa.myclay:id/login_info")).trim();
		log.info(message);
		return message == expectedwelcome ? true : false;

	}
}