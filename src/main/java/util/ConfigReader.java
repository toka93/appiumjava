package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	private final static Logger log = LogManager.getLogger();
	 public static final String MOBILE_PLATFORM_NAME = getPlatformName();
	//this class to read device capabilities and appium info
	public static String getAppiumConfig(String propertyName) {
        return getPropertyValue("appium.properties", propertyName);
    }

    public static String getDeviceConfig(String propertyName) {
        return getPropertyValue("device.properties", propertyName);
    }
    private static String getPropertyValue(String filename, String propertyName) {
        String propertyValue = null;

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
        	log.info(ex.toString());
            ex.printStackTrace();
        }

        return propertyValue;
    }
    
    public  static String getPlatformName() {
        String platform= System.getProperty("platform");
        String platformName;

        if (platform != null)
            platformName = platform;
        else {
        	log.info("The default platform: android will be enabled for this run.");
            platformName = "android";
        }

        return platformName.toLowerCase();
    }
}
