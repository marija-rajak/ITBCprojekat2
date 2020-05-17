package toCheckoutSandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Locators {

	
	public static final HashMap<String, String> XPATHS = new HashMap<String, String>();

	static String temp = "";
	
	
	public static HashMap<String, String> findXpaths() {

		try {
			FileReader fr = new FileReader("nw.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((temp = br.readLine()) != null) {

				String[] elemLoc = temp.split("\t");
				XPATHS.put(elemLoc[0], elemLoc[1]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return XPATHS;
	}

	
	public static String findLocatorValue(HashMap xpt, String elementName) {

		return xpt.get(elementName).toString();
	}	
	
	
	public static String fixXpath(int a, String xpth) {
		
		if (xpth.contains("[i]")) {
			xpth = xpth.replace("[i]", "["+(char)(a+'0')+"]");
		}
		
		return xpth;
	}
	
}
