package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
	protected final static Logger log = LogManager.getLogger();
    public final AppiumDriver<MobileElement> driver;
    public final WebDriverWait wait;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 80);
    }

    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public boolean checkIfElementVisible(By by) {
       return driver.findElement(by).isDisplayed();
    		 
    }
    public void tap(By by) {
        waitUntilElementVisible(by);
        driver.findElement(by).click();
    }
    public void tap(MobileElement element) {
        element.click();
    }
    public void inputText(By by, String text) {
        waitUntilElementVisible(by);
        driver.findElement(by).sendKeys(text);
    }
    
    public String getText(By by) {
        waitUntilElementVisible(by);
        return driver.findElement(by).getText();
    }
    
    
    public int getListSize(By by) {
        waitUntilElementVisible(by);
        List<MobileElement> sites = driver.findElements(by);
        return sites.size();
        
        
    }
    public void taponElementByIndex(By by,int index) {
        waitUntilElementVisible(by);
        List<MobileElement> sites = driver.findElements(by);
            tap( sites.get(index)   );     
        
    }
    
    public void swipeUp() {
    	
    	  Dimension size = driver.manage().window().getSize();

    	  int startX = size.width / 2;
          int startY = (int) (size.height * 0.8);
          int endX = size.width / 2;
          int endY = (int) (size.height * 0.2);

              TouchAction action = new TouchAction(driver);
              action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(endX, endY))
                    .release()
                    .perform();

              // Wait for the page to load and check if the end of the page has been reached
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
            
    	
    }
    }
    
    
//}
