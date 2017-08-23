package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SuppliedProductPopulator implements IPopulator {

	@Override
	public void populate(PrintWriter writer) {
		int countSuppliedProducs = 1;

		for (int supplyOrderID = 1; supplyOrderID <= SupplyOrderPopulator.SUPPLY_ORDER_NUMBER; supplyOrderID++) {
			int orderSize = IdRandomizer.getRandomId(10, 50);

			for (int i = 0; i < orderSize; i++) {
				int productID = IdRandomizer.getRandomId(1, ProductPopulator.PRODUCT_NUMBER);

				int orderedAmount = 5 + IdRandomizer.getRandomId(0, 45);
				int suppliedAmount = orderedAmount;

				if (countSuppliedProducs == 4) {
					suppliedAmount = IdRandomizer.getRandomId(orderedAmount - 5, orderedAmount + 5);

					countSuppliedProducs = 1;
				} else {
					countSuppliedProducs++;
				}

				int averageCost = IdRandomizer.getRandomId(50, 300);

				int totalCost = suppliedAmount * averageCost;

				writer.printf(
						"INSERT INTO SuppliedProduct (SupplyOrderID, ProductID, OrderedAmount, SuppliedAmount, TotalCost) "
								+ "VALUES (%d, %d, %d, %d, %d);\n",
						supplyOrderID, productID, orderedAmount, suppliedAmount, totalCost);
			}
		}
	}
}
