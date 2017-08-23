package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class ProductPopulator implements IPopulator {

	public static int PRODUCT_NUMBER = 1000;

	public static int MEASURE_FROM = 1;

	public static int MEASURE_TO = 5;

	public static int CATEGORY_FROM = 1;

	public static int CATEGORY_TO = 15;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < PRODUCT_NUMBER; i++) {
			int productID = i + 1;

			String productName = String.format("Product-%s", productID);
			String productMeasure = String.format("Measure-%s", IdRandomizer.getRandomId(MEASURE_FROM, MEASURE_TO));
			String proudctCategory = String.format("Category-%s", IdRandomizer.getRandomId(CATEGORY_FROM, CATEGORY_TO));

			writer.printf(
					"INSERT INTO Product (ProductID, ProductName, ProductMeasure, ProductCategory) "
							+ "VALUES (%d, '%s', '%s', '%s');\n",
					productID, productName, productMeasure, proudctCategory);
		}
	}
}
