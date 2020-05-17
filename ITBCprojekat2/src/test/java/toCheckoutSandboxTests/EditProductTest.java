package toCheckoutSandboxTests;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toCheckoutSandbox.AddProducts;
import toCheckoutSandbox.EditProduct;
import toCheckoutSandbox.Locators;
import toCheckoutSandbox.Login;
import toCheckoutSandbox.TestData;

public class EditProductTest {

	public static WebDriver wd;
	public static SoftAssert sa;
	public static HashMap<String, String> Xpaths = Locators.findXpaths();

	@BeforeClass
	public void Login() {
		wd = new ChromeDriver();

		wd.get(Login.HOME_PAGE_URL);
		
		wd.manage().window().maximize();

		Login.enterUsername(wd, 3, 0, 0, Xpaths);
		Login.enterPassword(wd, 3, 0, 1, Xpaths);
		Login.logIn(wd, Xpaths);
	}

	@BeforeMethod
	public void settingNew() {
		sa = new SoftAssert();
	}

	@Test
	public void Test_1_ChangingPrice() {

		wd.findElement(By.xpath(Locators.findLocatorValue(Xpaths, "PRODUCTS_LINK"))).click();
		wd.findElement(By.xpath(Locators.findLocatorValue(Xpaths, "EDIT_LINK"))).click();

		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		EditProduct.setNewPrice(wd, Xpaths);

		sa.assertTrue(EditProduct.findMessage(wd, Xpaths));

		HashMap<String, Float> correctedPrices = AddProducts.listProducts(wd, Xpaths,
				EditProduct.findNOProducts(wd, Xpaths));

		for (int i = 1; i <= 7; i++) {

			double currentPrice = correctedPrices.get(TestData.getData(4, i, 0));
			
			double expectedPrice =Double.valueOf(TestData.getData(4, i, 1)) + 100;

			DecimalFormat df = new DecimalFormat("#.##");
			
			 String cp = df.format(currentPrice);
			 String ep = df.format(expectedPrice);
			
			sa.assertEquals(cp, ep);
		}
		sa.assertAll();
	}
}
