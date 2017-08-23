package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class ProductPopulator implements IPopulator {

	public static int PRODUCT_NUMBER = 1000;

	public static int MEASURE_NUMBER = 5;

	public static int CATEGORY_NUMBER = 15;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < PRODUCT_NUMBER; i++) {
			int productID = i + 1;

			String productName = String.format("Product-%d", productID);
			String productMeasure = String.format("Measure-%d", IdRandomizer.getRandomId(1, MEASURE_NUMBER));
			String proudctCategory = String.format("Category-%d", IdRandomizer.getRandomId(1, CATEGORY_NUMBER));

			writer.printf(
					"INSERT INTO Product (ProductID, ProductName, ProductMeasure, ProductCategory) "
							+ "VALUES (%d, '%s', '%s', '%s');\n",
					productID, productName, productMeasure, proudctCategory);
		}
	}
}
