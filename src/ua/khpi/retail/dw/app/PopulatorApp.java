package ua.khpi.retail.dw.app;

import java.io.PrintWriter;

import ua.khpi.retail.dw.ProductPopulator;
import ua.khpi.retail.dw.SuppliedProductPopulator;
import ua.khpi.retail.dw.SupplierPopulator;
import ua.khpi.retail.dw.SupplyOrderPopulator;

public class PopulatorApp {

	public void runApp(String fileName) {
		try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
			new ProductPopulator().populate(writer);
			new SupplierPopulator().populate(writer);
			new SupplyOrderPopulator().populate(writer);
			new SuppliedProductPopulator().populate(writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new PopulatorApp().runApp("retail-dw-content.sql");
	}
}
