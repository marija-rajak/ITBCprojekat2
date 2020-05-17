package toCheckoutSandbox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	public static String getData(int h, int i, int j) {

		FileInputStream fis;
		XSSFWorkbook wb;

		try {
			fis = new FileInputStream("test data.xlsx");
			wb = new XSSFWorkbook(fis);

			return wb.getSheetAt(h).getRow(i).getCell(j).toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";

		}

	}

}
