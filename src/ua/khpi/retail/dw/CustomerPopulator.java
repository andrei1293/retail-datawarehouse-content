package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class CustomerPopulator implements IPopulator {

	public static final int CUSTOMER_NUMBER = 100;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < CUSTOMER_NUMBER; i++) {
			int customerID = i + 1;

			String customerName = String.format("Customer-%d", customerID);
			String customerAddress = String.format("%s-Address", customerName);
			String customerPhone = String.format("+380 XX %d %d", IdRandomizer.getRandomId(100, 999),
					IdRandomizer.getRandomId(1000, 9999));

			writer.printf(
					"INSERT INTO Customer (CustomerID, CustomerName, CustomerAddress, CustomerPhone) "
							+ "VALUES (%d, '%s', '%s', '%s');\n",
					customerID, customerName, customerAddress, customerPhone);
		}
	}
}
