package tests;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import util.ExtentManager;
import driverCreation.*;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SettingsPage;

import static driverCreation.MobileDriverHolder.getDriver;
import static driverCreation.MobileDriverHolder.setDriver;

public class BaseTest {
	protected static ExtentReports extent;
	public static ThreadLocal parentTest = new ThreadLocal();
	protected static ThreadLocal test = new ThreadLocal();
	public ExtentTest child;
	ExtentHtmlReporter htmlReporter;
	String ExtentReport = "Reports";
	LocalTime time = LocalTime.now();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate localDate = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
	String reportName = ExtentReport + time.format(formatter) + ".html";
	String HtmlReportPath = System.getProperty("user.dir") + File.separator + ExtentReport + File.separator
			+ File.separator + dtf.format(localDate) + File.separator + reportName;
	private final static Logger log = LogManager.getLogger();
	private final MobileDriverFactory driverFactory = new MobileDriverFactory();
	private final MobileDriverService driverService = driverFactory.getDriverService();
	LoginPage login;
	HomePage home;
	SettingsPage settings;
	LogoutPage logout;

	@BeforeSuite(alwaysRun = true)
	public void oneTimeSetup() {
		log.info("before suite");
		extent = ExtentManager.createInstance(HtmlReportPath);
		htmlReporter = new ExtentHtmlReporter(
				ExtentReport + File.separator + File.separator + dtf.format(localDate) + File.separator + reportName);
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() throws MalformedURLException {

		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
		
		
	}

	@BeforeMethod(alwaysRun = true)
	public void openApp() {
		driverService.spinUpDriver();
		setDriver(driverService.getDriver());
		getDriver().manage().timeouts().implicitlyWait(30, SECONDS);
		login = new LoginPage(getDriver());
		home = new HomePage(getDriver());
		settings = new SettingsPage(getDriver());
		logout = new LogoutPage(getDriver());
		test.set(child);

	}

	@AfterMethod(alwaysRun = true)
	public void closeApp(ITestResult res) {

		log.info("get status of test just run");
		if (res.getStatus() == ITestResult.SUCCESS) {
			log.info("test passes");
		} else if (res.getStatus() == ITestResult.FAILURE) {
			log.info("test failed");
			child.log(Status.FAIL, "Test failed ");
			System.out.println("status for fail");
		} else if (res.getStatus() == ITestResult.SKIP) {
			log.info("test skipped ");
			child.log(Status.SKIP, "Test skipped");
		} else {
			log.info("test finished with status other than (pass,fail,skip) ");
			child.log(Status.ERROR, "Test skipped");
		}
		log.info("close driver browser in after method");

		driverService.closeDriver();
	}
	/*
	 * @AfterClass(alwaysRun = true) public void closeDriver() {
	 * driverService.closeDriver();
	 * 
	 * }
	 */

	@AfterSuite(alwaysRun = true)
	public void endSuite() throws IOException {
		log.info("end report in after class");
		extent.flush();
		ExtentManager.updateReportFile(HtmlReportPath);

	}

}
