package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SoldProductPopulator implements IPopulator {

	public static final int INCOMPLETE_SCHEDULE = 150;

	public static final int INCOMPLETE_SIZE = 300;

	@Override
	public void populate(PrintWriter writer) {
		int dateCounter = 1;
		int productCounter = 1;

		for (int dateID = DatePopulator.DATE_SUPPLY_NUMBER + 1; dateID <= DatePopulator.DATE_SALE_NUMBER; dateID++) {
			int supplySize = IdRandomizer.getRandomId(1, 5);
			int customerID = IdRandomizer.getRandomId(1, SupplierPopulator.SUPPLIER_NUMBER);

			int suppliedProductID = 0;

			for (int i = 0; i < supplySize; i++) {
				int productID = IdRandomizer.getRandomId(1, ProductPopulator.PRODUCT_NUMBER);

				while (productID == suppliedProductID) {
					productID = IdRandomizer.getRandomId(1, ProductPopulator.PRODUCT_NUMBER);
				}

				suppliedProductID = productID;

				int orderTime = IdRandomizer.getRandomId(2, 48);

				int expectedSupplyTime = IdRandomizer.getRandomId(2, 5);
				int realSupplyTime = expectedSupplyTime;

				if (dateCounter == INCOMPLETE_SCHEDULE) {
					realSupplyTime = IdRandomizer.getRandomId(expectedSupplyTime, expectedSupplyTime + 2);
				}

				int orderedAmount = 5 + IdRandomizer.getRandomId(5, 50);
				int suppliedAmount = orderedAmount;

				if (productCounter == INCOMPLETE_SIZE) {
					suppliedAmount = IdRandomizer.getRandomId(orderedAmount - 5, orderedAmount + 5);
				}

				int averagePrice = IdRandomizer.getRandomId(75, 500);

				int totalCost = orderedAmount * averagePrice;

				writer.printf(
						"INSERT INTO SoldProduct (DateID, CustomerID, ProductID, OrderTime, ExpectedSupplyTime,"
								+ "RealSupplyTime, OrderedAmount, SuppliedAmount, TotalCost) "
								+ "VALUES (%d, %d, %d, %d, %d, %d, %d, %d, %d);\n",
						dateID, customerID, productID, orderTime, expectedSupplyTime, realSupplyTime, orderedAmount,
						suppliedAmount, totalCost);
			}

			if (dateCounter == INCOMPLETE_SCHEDULE) {
				dateCounter = 1;
			} else {
				dateCounter++;
			}

			if (productCounter == INCOMPLETE_SIZE) {
				productCounter = 1;
			} else {
				productCounter++;
			}
		}
	}
}
