package driverCreation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static driverCreation.DriverConstants.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.util.concurrent.TimeUnit.SECONDS;


public class IosDriverServiceImpl implements MobileDriverService {
    private IOSDriver<MobileElement> iosDriver;
    private final static Logger log = LogManager.getLogger();

    @Override
    public void spinUpDriver() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(DEVICE_NAME, IOS_DEVICE_NAME);
        capabilities.setCapability(AUTOMATION_NAME, IOS_AUTOMATION_NAME);
        capabilities.setCapability(PLATFORM_NAME, IOS);
        capabilities.setCapability(PLATFORM_VERSION, IOS_PLATFORM_VERSION);
      

        try {
            iosDriver = new IOSDriver<>(new URL(APPIUM_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        iosDriver.manage().timeouts().implicitlyWait(APPIUM_DRIVER_TIMEOUT_IN_SECONDS, SECONDS);
    }

    @Override
    public void closeDriver() {
        iosDriver.closeApp();
    }

    @Override
    public AppiumDriver<MobileElement> getDriver() {
        return iosDriver;
    }
}
