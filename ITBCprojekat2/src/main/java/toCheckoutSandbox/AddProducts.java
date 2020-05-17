package toCheckoutSandbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProducts {

	public static String PRODUCTS_LINK;

	public static String VIEW_LINK;

	public static String ADDNEWPRODUCT_LINK;

	public static String PRODUCTNAME_INPUTFIELD;

	public static String PRICE_INPUTFIELD;

	public static String SUBMIT_BUTTON;

	public static String PL_ID;

	public static String PL_PRODUCTNAME;

	public static String PL_PRODUCTPRICE;

	public static HashMap<String, Float> PRODUCTS = new HashMap<String, Float>();

	public static String PL_CONFIRMATION_MESSAGE;

	public static ArrayList<String> PRODUCT_NAMES = new ArrayList<String>();

	public static void openForm(WebDriver w, HashMap<String, String> xpt) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "PRODUCTS_LINK"))).click();
		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "VIEW_LINK"))).click();
		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "ADDNEWPRODUCT_LINK"))).click();
	}

	public static void inputProductName(WebDriver w, HashMap<String, String> xpt, int i) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "PRODUCTNAME_INPUTFIELD")))
				.sendKeys(TestData.getData(4, i, 0));
	}

	public static void inputProductPrice(WebDriver w, HashMap<String, String> xpt, int i) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "PRICE_INPUTFIELD"))).sendKeys(TestData.getData(4, i, 1));
	}

	public static void submitProduct(WebDriver w, HashMap<String, String> xpt) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "SUBMIT_BUTTON"))).click();
	}

	public static HashMap<String, Float> listProducts(WebDriver w, HashMap<String, String> xpt, int n) {

		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "VIEW_LINK"))).click();

		for (int i = 2; i <= n + 1; i++) {

			PRODUCTS.put(
					w.findElement(By.xpath(Locators.fixXpath(i, Locators.findLocatorValue(xpt, "PL_PRODUCTNAME"))))
							.getText(),
					Float.valueOf(w
							.findElement(
									By.xpath(Locators.fixXpath(i, Locators.findLocatorValue(xpt, "PL_PRODUCTPRICE"))))
							.getText()));
		}
		return PRODUCTS;
	}

	public static int findNOProducts(WebDriver w, HashMap<String, String> xpt) {

		List<WebElement> rows = w.findElements(By.xpath(Locators.findLocatorValue(xpt, "PL_ID")));
		int n = rows.size();
		return n;
	}

	public static boolean findMessage(WebDriver w, HashMap<String, String> xpt) {

		try {
			WebElement e = w.findElement(By.xpath(Locators.findLocatorValue(xpt, "PL_CONFIRMATION_MESSAGE")));
			return true;
			
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static boolean findProduct(WebDriver w, HashMap<String, String> xpt, int n, String product) {

		boolean found = false;

		for (int i = 2; i <= n + 1; i++) {
			String temp = w
					.findElement(By.xpath(Locators.fixXpath(i, Locators.findLocatorValue(xpt, "PL_PRODUCTNAME"))))
					.getText();
			if (product.equals(temp))
				found = true;
		}
		return found;
	}
}
