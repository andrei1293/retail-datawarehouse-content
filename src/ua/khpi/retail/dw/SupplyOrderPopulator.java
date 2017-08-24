package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SupplyOrderPopulator implements IPopulator {

	public static final int SUPPLY_ORDER_NUMBER = 200;
	
	public static final int OVERDUE_ORDERS = 10;

	@Override
	public void populate(PrintWriter writer) {
		int year = 2001;
		int month = 1;
		
		int overdueOrder = SUPPLY_ORDER_NUMBER / OVERDUE_ORDERS;
		
		int orderCounter = 1;

		for (int i = 0; i < SUPPLY_ORDER_NUMBER; i++) {
			int supplyOrderID = i + 1;

			int supplierID = IdRandomizer.getRandomId(1, SupplierPopulator.SUPPLIER_NUMBER);

			int orderDay = IdRandomizer.getRandomId(1, 10);
			int orderHour = IdRandomizer.getRandomId(9, 12);

			String orderDate = String.format("%d-%d-%d %d:00:00", year, month, orderDay, orderHour);

			int contractDay = IdRandomizer.getRandomId(orderDay, orderDay + 2);
			int contractHour = IdRandomizer.getRandomId(orderHour, orderHour + 2);

			String contractDate = String.format("%d-%d-%d %d:00:00", year, month, contractDay, contractHour);

			int expectedSupplyDay = IdRandomizer.getRandomId(contractDay + 2, orderDay + 5);

			String expectedSupplyDate = String.format("%d-%d-%d 17:00:00", year, month, expectedSupplyDay);

			int realSupplyDay = expectedSupplyDay;
			
			if (orderCounter == overdueOrder) {
				realSupplyDay = IdRandomizer.getRandomId(expectedSupplyDay + 1, expectedSupplyDay + 3);
				
				orderCounter = 1;
			} else {
				orderCounter++;
			}

			String realSupplyDate = String.format("%d-%d-%d 17:00:00", year, month, realSupplyDay);

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
