package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class DatePopulator implements IPopulator {

	public static final int DATE_SUPPLY_NUMBER = 200;
	
	public static final int DATE_SALE_NUMBER = 2000;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < DATE_SUPPLY_NUMBER; i++) {
			int dateID = i + 1;

			int dayNumber = IdRandomizer.getRandomId(1, 28);
			int monthNumber = IdRandomizer.getRandomId(1, 12);
			int yearNumber = IdRandomizer.getRandomId(2011, 2016);
			
			int quarterNumber = 1;
			
			if (monthNumber >= 4) {
				quarterNumber = 2;
			}

			writer.printf(
					"INSERT INTO Customer (CustomerID, CustomerName, CustomerAddress, CustomerPhone) "
							+ "VALUES (%d, '%s', '%s', '%s');\n",
					customerID, customerName, customerAddress, customerPhone);
		}
	}
}
