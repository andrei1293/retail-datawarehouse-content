package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SupplyOrderPopulator implements IPopulator {

	public static int SUPPLY_ORDER_NUMBER = 200;

	@Override
	public void populate(PrintWriter writer) {
		int year = 2001;
		int month = 1;

		for (int i = 0; i < SUPPLY_ORDER_NUMBER; i++) {
			int supplyOrderID = i + 1;

			int supplierID = IdRandomizer.getRandomId(1, SupplierPopulator.SUPPLIER_NUMBER);

			int orderDay = IdRandomizer.getRandomId(1, 10);

			String orderDate = String.format("%d-%d-%d", year, month, orderDay);

			int contractDay = IdRandomizer.getRandomId(orderDay, orderDay + 3);

			String contractDate = String.format("%d-%d-%d", year, month, contractDay);

			int expectedSupplyDay = IdRandomizer.getRandomId(contractDay + 2, orderDay + 5);

			String expectedSupplyDate = String.format("%d-%d-%d", year, month, expectedSupplyDay);

			int realSupplyDay = IdRandomizer.getRandomId(contractDay + 2, orderDay + 8);

			String realSupplyDate = String.format("%d-%d-%d", year, month, realSupplyDay);

			writer.printf(
					"INSERT INTO SupplyOrder (SupplyOrderID, SupplierID, OrderDate, ContractDate, ExpectedSupplyDate,"
							+ "RealSupplyDate) VALUES (%d, %d, '%s', '%s', '%s', '%s');\n",
					supplyOrderID, supplierID, orderDate, contractDate, expectedSupplyDate, realSupplyDate);
			
			if (month == 12) {
				month = 1;
				year++;
			} else {
				month++;
			}
		}
	}
}
