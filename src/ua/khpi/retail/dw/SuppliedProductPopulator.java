package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SuppliedProductPopulator implements IPopulator {

	public static final int INCOMPLETE_ORDERS = 20;

	public static final int INCOMPLETE_UNITS = 10;

	@Override
	public void populate(PrintWriter writer) {
		int incompleteOrder = SupplyOrderPopulator.SUPPLY_ORDER_NUMBER / INCOMPLETE_ORDERS;

		int orderCounter = 1;

		for (int supplyOrderID = 1; supplyOrderID <= SupplyOrderPopulator.SUPPLY_ORDER_NUMBER; supplyOrderID++) {
			int orderSize = IdRandomizer.getRandomId(10, 50);

			int incompleteUnit = orderSize / INCOMPLETE_UNITS;

			int unitCounter = 1;

			int previousProductID = 0;

			for (int i = 0; i < orderSize; i++) {
				int productID = IdRandomizer.getRandomId(1, ProductPopulator.PRODUCT_NUMBER);

				while (productID == previousProductID) {
					productID = IdRandomizer.getRandomId(1, ProductPopulator.PRODUCT_NUMBER);
				}
				
				previousProductID = productID;

				int orderedAmount = 5 + IdRandomizer.getRandomId(0, 45);
				int suppliedAmount = orderedAmount;

				if (orderCounter == incompleteOrder) {
					if (unitCounter == incompleteUnit) {
						suppliedAmount = IdRandomizer.getRandomId(orderedAmount - 5, orderedAmount + 5);

						unitCounter = 1;
					} else {
						unitCounter++;
					}
				}

				int averageCost = IdRandomizer.getRandomId(50, 300);

				int totalCost = suppliedAmount * averageCost;

				writer.printf(
						"INSERT INTO SuppliedProduct (SupplyOrderID, ProductID, OrderedAmount, SuppliedAmount, TotalCost) "
								+ "VALUES (%d, %d, %d, %d, %d);\n",
						supplyOrderID, productID, orderedAmount, suppliedAmount, totalCost);
			}

			if (orderCounter == incompleteOrder) {
				orderCounter = 1;
			} else {
				orderCounter++;
			}
		}
	}
}
