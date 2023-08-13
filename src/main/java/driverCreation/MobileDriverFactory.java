package driverCreation;

import static driverCreation.DriverConstants.ANDROID;
import static driverCreation.DriverConstants.IOS;
import static util.ConfigReader.MOBILE_PLATFORM_NAME;

import java.security.InvalidParameterException;


public class MobileDriverFactory {
	public MobileDriverService getDriverService() {
        MobileDriverService driver;

        switch (MOBILE_PLATFORM_NAME) {
            case ANDROID:
                driver = new AndroidDriverServiceImpl();
                break;
            case IOS:
                driver = new IosDriverServiceImpl();
                break;
            default:
                throw new InvalidParameterException("Please use platform as '" + ANDROID + "' or '" + IOS + "'");
        }

        return driver;
    }

}
