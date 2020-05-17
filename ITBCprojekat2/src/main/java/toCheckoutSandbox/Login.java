package toCheckoutSandbox;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public static final String HOME_PAGE_URL = "https://sandbox.2checkout.com/sandbox";

	public static String USERNAME_INPUTFIELD;

	public static String PASSWORD_INPUTFIELD;

	public static String LOGIN_BUTTON;

	public static String VENDOR_ICON;

	public static String USER_NAME;
	
	public static String ERROR_MESSAGE;

	
	public static void logIn(WebDriver w, HashMap xpt) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "LOGIN_BUTTON"))).click();
	}

	public static void enterUsername(WebDriver w, int h, int i, int j, HashMap xpt) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "USERNAME_INPUTFIELD")))
				.sendKeys(TestData.getData(h, i, j));
	}
	
	public static void enterPassword(WebDriver w, int h, int i, int j, HashMap xpt) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "PASSWORD_INPUTFIELD")))
				.sendKeys(TestData.getData(h, i, j));
	}
		
	public static String findLoggedUser(WebDriver w, HashMap xpt) {

		if (w.getTitle().equals("Seller Area / Home")) {
			w.findElement(By.xpath(Locators.findLocatorValue(xpt, "VENDOR_ICON"))).click();
			return w.findElement(By.xpath(Locators.findLocatorValue(xpt, "USER_NAME"))).getText();
		} else
			return null;
	}
	
	public static String getLoginMessage(WebDriver w, HashMap xpt) {
		
		if (w.getTitle().equals("Seller Area")) {
			return w.findElement(By.xpath(Locators.findLocatorValue(xpt, "ERROR_MESSAGE"))).getText();
		} else
			return null;
	}
}
