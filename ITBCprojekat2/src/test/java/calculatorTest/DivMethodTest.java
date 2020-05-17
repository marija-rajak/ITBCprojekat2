package calculatorTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import calculator.Calculator;

public class DivMethodTest {

	SoftAssert sa;

	@BeforeMethod
	public void settingSa() {
		sa = new SoftAssert();
	}

	@Test
	public void deljenjeNule() {

		Calculator calc = new Calculator();

		double delilac = -5;

		for (int i = 0; i < 5; i++) {

			double deljenik = calc.getValue();

			calc.div(delilac);

			double result = Math.abs(calc.getValue());
			double expected = Math.abs(deljenik / delilac);

			AssertJUnit.assertEquals(result, expected);

			delilac += 2.3;
			calc.setValue(0);
		}
		sa.assertAll();
	}

	@Test
	public void deljenjeSaNulom() {

		Calculator calc = new Calculator();

		for (int i = 0; i < 10; i += 2) {

			calc.setValue(i - 4);

			double pocVrednost = calc.getValue();

			try {
				calc.div(0);

			} catch (Exception e) {

			}

			double zavrsnaVrednost = calc.getValue();

			AssertJUnit.assertEquals(zavrsnaVrednost, pocVrednost);

		}
		sa.assertAll();
	}

	@Test
	public void deljenjePozNeg() {

		Calculator calc = new Calculator();

		calc.setValue(10);
		double delilac = 0.25;

		for (int i = 0; i < 9; i++) {

			double deljenik = calc.getValue();

			calc.div(delilac);

			double result = calc.getValue();
			double expected = deljenik / delilac;

			AssertJUnit.assertEquals(result, expected);

			delilac *= -1.1;
		}
		sa.assertAll();
	}

}
