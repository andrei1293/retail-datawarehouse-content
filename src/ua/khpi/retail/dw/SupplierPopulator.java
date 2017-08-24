package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.IdRandomizer;

public class SupplierPopulator implements IPopulator {

	public static final int SUPPLIER_NUMBER = 20;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < SUPPLIER_NUMBER; i++) {
			int supplierID = i + 1;

			String supplierName = String.format("Supplier-%d", supplierID);
			String supplierAddress = String.format("%s-Address", supplierName);
			String supplierPhone = String.format("+380 XX %d %d", IdRandomizer.getRandomId(100, 999),
					IdRandomizer.getRandomId(1000, 9999));

			writer.printf(
					"INSERT INTO Supplier (SupplierID, SupplierName, SupplierAddress, SupplierPhone) "
							+ "VALUES (%d, '%s', '%s', '%s');\n",
					supplierID, supplierName, supplierAddress, supplierPhone);
		}
	}
}
