package toCheckoutSandbox;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditProduct {

	public static String EDIT_LINK;

	public static String EL_ID;

	public static String EL_PRODUCTNAME;

	public static String EL_PRODUCTPRICE;

	public static String EL_SAVEBUTTON;

	public static String EL_CONFIRMATION_MESSAGE;

	public static int findNOProducts(WebDriver w, HashMap<String, String> xpt) {

		List<WebElement> rows = w.findElements(By.xpath(Locators.findLocatorValue(xpt, "EL_ID")));
		int n = rows.size();
		return n;
	}

	public static void setNewPrice(WebDriver w, HashMap<String, String> xpt) {

		int n = findNOProducts(w, xpt);

		for (int j = 1; j <= 7; j++) {
			String prodName = TestData.getData(4, j, 0);

			for (int i = 2; i <= n + 1; i++) {
				String temp1 = (w
						.findElement(By.xpath(Locators.fixXpath(i, Locators.findLocatorValue(xpt, "EL_PRODUCTNAME"))))
						.getText());
				if (prodName.equals(temp1)) {

					WebElement priceField = w.findElement(
							By.xpath(Locators.fixXpath(i, Locators.findLocatorValue(xpt, "EL_PRODUCTPRICE"))));

					float price = Float.valueOf(priceField.getAttribute("value"));

					priceField.clear();

					price += 100;

					priceField.sendKeys(String.format(Locale.US, "%.2f", price));
				}
			}
		}
		w.findElement(By.xpath(Locators.findLocatorValue(xpt, "EL_SAVEBUTTON"))).click();
	}

	public static boolean findMessage(WebDriver w, HashMap<String, String> xpt) {

		try {
			WebElement e = w.findElement(By.xpath(Locators.findLocatorValue(xpt, "EL_CONFIRMATION_MESSAGE")));
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
