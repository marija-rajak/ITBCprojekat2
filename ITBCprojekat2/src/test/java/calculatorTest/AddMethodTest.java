package calculatorTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import calculator.Calculator;

public class AddMethodTest {

	SoftAssert sa;

	@BeforeMethod
	public void settingSa() {
		sa = new SoftAssert();
	}

	@Test
	public void sabiranjePozitivnih() {

		Calculator calc = new Calculator();

		double drugiSabirak = 0;

		for (int i = 0; i < 5; i++) {

			double prviSabirak = calc.getValue();

			calc.add(drugiSabirak);

			double result = calc.getValue();
			double expected = prviSabirak + drugiSabirak;

			AssertJUnit.assertEquals(result, expected);

			drugiSabirak += 2.3;
		}
		sa.assertAll();
	}

	@Test
	public void sabiranjePozitivanNegativan() {

		Calculator calc = new Calculator();

		calc.setValue(100);

		double drugiSabirak = 0;

		for (int i = 0; i < 5; i++) {

			double prviSabirak = calc.getValue();

			calc.add(drugiSabirak);

			double result = calc.getValue();
			double expected = prviSabirak + drugiSabirak;

			AssertJUnit.assertEquals(result, expected);

			drugiSabirak -= 2.3;
		}
		sa.assertAll();
	}

	@Test
	public void sabiranjeNegativnih() {

		Calculator calc = new Calculator();

		calc.setValue(-10);

		double drugiSabirak = 0;

		for (int i = 0; i < 5; i++) {

			double prviSabirak = calc.getValue();

			calc.add(drugiSabirak);

			double result = calc.getValue();
			double expected = prviSabirak + drugiSabirak;

			AssertJUnit.assertEquals(result, expected);

			drugiSabirak -= 2.3;
		}
		sa.assertAll();
	}
}
