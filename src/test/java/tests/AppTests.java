package tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class AppTests extends BaseTest {

	private final static Logger log = LogManager.getLogger();

	String methodname;

	@Test(priority = 1)
	public void validateUserLoginWithValidCredentials(Method method) throws InterruptedException {
		methodname = method.getName();
		log.info("get test name to use it in extent report , name is : " + methodname);
		child = ((ExtentTest) parentTest.get()).createNode(methodname);
		login.tapOnCont();
		boolean flag1 = login.validateWelcomeMessage();

		child.info("Validate Welcome Message" + flag1);

		login.enterUser();
		login.tapOnCont();
		login.waituntiluserredirectstowebview();
		

		/*use it for the first run only 
		 * login.acceptTerms(); login.noSync();
		 */
	
		 if ( login.changeView("WEBVIEW"))
				child.pass("user is redirected to web page to enter password");
		login.enterPassword();
		login.submit();
	
		if (login.changeView("NATIVE_APP"))
			child.pass("user is logged in suuccesfully and redirected to App");
		else
			child.fail("user failed to login");
		String sitesText = login.getSitesText();
		child.info("App is opened and  Message is shown :" + sitesText);
		int sitesNo = login.getSites();
		if (sitesNo == 6)
			child.pass("Sites appear as expected");

		login.chooseLastSite();
		home.skip();
		boolean activityflag=home.checkActivityPage();
		if (activityflag)
			child.pass("user is logged in suuccesfully and redirected to Lock page");
		else
			child.fail("user failed to login");
		assertTrue(activityflag);

	}

	
	@Test(priority = 2)
	public void checkSettingsandLogout(Method method) {

		methodname = method.getName();
		log.info("get test name to use it in extent report , name is : " + methodname);
		child = ((ExtentTest) parentTest.get()).createNode(methodname);

		home.openSettings();

		boolean flag = settings.checkuserEmail();
		if (flag)
			child.pass("user email appears as expected");
		else
			child.fail("user email is incorrect");
		home.logout();
		boolean flag2 = logout.checkLogoutMessage();
		if (flag2)
			child.pass("user logged out succesfully");
		else
			child.fail("user logged out succesfully");

		assertTrue(flag && flag2);

	}
}
