package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class HomePage extends BasePage {

	public HomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void openSettings() {
		tap(MobileBy.AccessibilityId("Settings"));

	}

	public void logout() {

		int x = 0;
		while ( x < 5) {
			swipeUp();
			x++;

		}

		tap(MobileBy.id("nl.moboa.myclay:id/logout_view"));
	}

	public void skip() throws InterruptedException {
		tap(MobileBy.id("nl.moboa.myclay:id/skip_button"));
		wait(5000);

	}

	public boolean checkActivityPage() {
		String activity = getText(MobileBy.id("nl.moboa.myclay:id/activity_title"));
        log.info(activity);
		return activity.equals("Locks") ? true : false;

	}

}
