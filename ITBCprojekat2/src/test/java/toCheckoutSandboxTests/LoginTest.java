package toCheckoutSandboxTests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toCheckoutSandbox.Locators;
import toCheckoutSandbox.Login;
import toCheckoutSandbox.TestData;

public class LoginTest {

	public static WebDriver wd;
	public static SoftAssert sa;
	public static HashMap<String, String> Xpaths = Locators.findXpaths();

	@BeforeMethod
	public static void settingNew() {
		wd = new ChromeDriver();
		sa = new SoftAssert();
	}

	@AfterMethod
	public static void closeDriver() {
		wd.close();
	}

	@Test
	public void login_1_ExistingUser() {

		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterUsername(wd, 3, 0, 0, Xpaths);
		Login.enterPassword(wd, 3, 0, 1, Xpaths);
		Login.logIn(wd, Xpaths);

		String expectedName = TestData.getData(3, 0, 0);
		String name = Login.findLoggedUser(wd, Xpaths);

		sa.assertNotNull(name);
		sa.assertEquals(name, expectedName);

		sa.assertAll();
	}

	@Test
	public void login_2_UnexistingUser() {

		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterUsername(wd, 3, 1, 0, Xpaths);
		Login.enterPassword(wd, 3, 1, 1, Xpaths);
		Login.logIn(wd, Xpaths);

		String expectedMessage = "Incorrect username or password.";
		String message = Login.getLoginMessage(wd, Xpaths);

		sa.assertNotNull(message);
		sa.assertEquals(message, expectedMessage);

		sa.assertAll();
	}

	@Test
	public void login_3_MissingUsername() {

		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterPassword(wd, 3, 0, 1, Xpaths);
		Login.logIn(wd, Xpaths);

		String expectedMessage = "Missing username or password.";
		String message = Login.getLoginMessage(wd, Xpaths);

		sa.assertNotNull(message);
		sa.assertEquals(message, expectedMessage);

		sa.assertAll();
	}

	@Test
	public void login_4_MissingPassword() {
		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterUsername(wd, 3, 0, 0, Xpaths);
		Login.logIn(wd, Xpaths);

		String expectedMessage = "Missing username or password.";
		String message = Login.getLoginMessage(wd, Xpaths);

		sa.assertNotNull(message);
		sa.assertEquals(message, expectedMessage);

		sa.assertAll();
	}

	@Test
	public void login_5_WrongPassword() {
		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterUsername(wd, 3, 0, 0, Xpaths);
		Login.enterPassword(wd, 3, 1, 1, Xpaths);
		Login.logIn(wd, Xpaths);

		String expectedMessage = "Incorrect username or password.";
		String message = Login.getLoginMessage(wd, Xpaths);

		sa.assertNotNull(message);
		sa.assertEquals(message, expectedMessage);

		sa.assertAll();
	}

	@Test
	public void login_6_Blank() {
		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.logIn(wd, Xpaths);

		String expectedMessage = "Missing username or password.";
		String message = Login.getLoginMessage(wd, Xpaths);

		sa.assertNotNull(message);
		sa.assertEquals(message, expectedMessage);

		sa.assertAll();
	}
}
