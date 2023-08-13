package driverCreation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;



public class MobileDriverHolder {
    private static final ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();

    public static AppiumDriver<MobileElement> getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver<MobileElement> driver) {
        MobileDriverHolder.driver.set(driver);
    }
}
