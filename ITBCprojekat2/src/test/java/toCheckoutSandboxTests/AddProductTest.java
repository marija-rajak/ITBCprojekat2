package toCheckoutSandboxTests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toCheckoutSandbox.AddProducts;
import toCheckoutSandbox.Locators;
import toCheckoutSandbox.Login;
import toCheckoutSandbox.TestData;

public class AddProductTest {

	public static WebDriver wd;
	public static SoftAssert sa;
	public static HashMap<String, String> Xpaths = Locators.findXpaths();

	@BeforeClass
	public void login() {

		wd = new ChromeDriver();

		wd.get(Login.HOME_PAGE_URL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Login.enterUsername(wd, 3, 0, 0, Xpaths);
		Login.enterPassword(wd, 3, 0, 1, Xpaths);
		Login.logIn(wd, Xpaths);

	}

	@BeforeMethod
	public void settingNew() {

		sa = new SoftAssert();
		
	}

	@AfterClass
	public static void closeDriver() {
		wd.close();
	}

	@Test
	public void Test_1_AddingProducts() {

		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		for (int i = 1; i <= 5; i++) {

			AddProducts.openForm(wd, Xpaths);
			AddProducts.inputProductName(wd, Xpaths, i);
			AddProducts.inputProductPrice(wd, Xpaths, i);
			AddProducts.submitProduct(wd, Xpaths);

			sa.assertTrue(AddProducts.findMessage(wd, Xpaths));
		}

		wd.findElement(By.xpath(Locators.findLocatorValue(Xpaths, "VIEW_LINK"))).click();

		HashMap<String, Float> products = AddProducts.listProducts(wd, Xpaths, AddProducts.findNOProducts(wd, Xpaths));

		for (int i = 1; i <= 5; i++) {

			float currentPrice = products.get(TestData.getData(4, i, 0));
			
			float expectedPrice = Float.valueOf(TestData.getData(4, i, 1));
			

			sa.assertEquals(currentPrice, expectedPrice);
		}

		sa.assertAll();
	}

	@Test
	public void Test_2_ZeroPrice() {
		
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		AddProducts.openForm(wd, Xpaths);
		AddProducts.inputProductName(wd, Xpaths, 6);
		AddProducts.inputProductPrice(wd, Xpaths, 6);
		AddProducts.submitProduct(wd, Xpaths);

		sa.assertFalse(AddProducts.findMessage(wd, Xpaths));

		wd.findElement(By.xpath(Locators.findLocatorValue(Xpaths, "VIEW_LINK"))).click();

		sa.assertFalse(
				AddProducts.findProduct(wd, Xpaths, AddProducts.findNOProducts(wd, Xpaths), TestData.getData(4, 6, 0)));

		sa.assertAll();
	}

	@Test
	public void Test_3_NegativePrice() {
		
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		AddProducts.openForm(wd, Xpaths);
		AddProducts.inputProductName(wd, Xpaths, 7);
		AddProducts.inputProductPrice(wd, Xpaths, 7);
		AddProducts.submitProduct(wd, Xpaths);

		sa.assertFalse(AddProducts.findMessage(wd, Xpaths));

		wd.findElement(By.xpath(Locators.findLocatorValue(Xpaths, "VIEW_LINK"))).click();

		sa.assertFalse(
				AddProducts.findProduct(wd, Xpaths, AddProducts.findNOProducts(wd, Xpaths), TestData.getData(4, 7, 0)));

		sa.assertAll();
	}
}
