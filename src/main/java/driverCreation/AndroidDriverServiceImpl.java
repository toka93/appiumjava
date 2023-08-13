package driverCreation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import java.net.MalformedURLException;
import java.net.URL;

import static driverCreation.DriverConstants.*;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.util.concurrent.TimeUnit.SECONDS;



public class AndroidDriverServiceImpl implements MobileDriverService {
    private AndroidDriver<MobileElement> androidDriver;
	private final static Logger log = LogManager.getLogger();


    @Override
    public void spinUpDriver() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(DEVICE_NAME, ANDROID_DEVICE_NAME);
        capabilities.setCapability(AUTOMATION_NAME, ANDROID_AUTOMATION_NAME);
        capabilities.setCapability(PLATFORM_NAME, ANDROID);
        capabilities.setCapability(PLATFORM_VERSION, ANDROID_PLATFORM_VERSION);
        capabilities.setCapability(APP_PACKAGE, ANDROID_APP_PACKAGE);
        capabilities.setCapability(APP_ACTIVITY, ANDROID_APP_ACTIVITY);
        capabilities.setCapability(NO_RESET, ANDROID_NO_RESET);
        capabilities.setCapability(FULL_RESET, ANDROID_FULL_RESET);
        //added to handle webview
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        

        try {
            androidDriver = new AndroidDriver<>(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
        	 log.info(e.getMessage());
            e.printStackTrace();
        }

        androidDriver.manage().timeouts().implicitlyWait(APPIUM_DRIVER_TIMEOUT_IN_SECONDS, SECONDS);
    }

    @Override
    public void closeDriver() {
        String appId = (String) androidDriver
                .getCapabilities()
                .getCapability(APP_PACKAGE);
        if (appId != null)
            androidDriver.terminateApp(appId);
    }

    public AppiumDriver<MobileElement> getDriver() {
        return androidDriver;
    }
}
