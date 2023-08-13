package driverCreation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;



public interface MobileDriverService {
    void spinUpDriver();

    void closeDriver();

    AppiumDriver<MobileElement> getDriver();
}
