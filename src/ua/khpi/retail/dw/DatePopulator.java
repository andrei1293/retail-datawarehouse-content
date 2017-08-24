package ua.khpi.retail.dw;

import java.io.PrintWriter;

import ua.khpi.retail.dw.api.IPopulator;
import ua.khpi.retail.dw.util.CalendarUtil;
import ua.khpi.retail.dw.util.IdRandomizer;

public class DatePopulator implements IPopulator {

	public static final int DATE_SUPPLY_NUMBER = 200;

	public static final int DATE_SALE_NUMBER = 2000;

	@Override
	public void populate(PrintWriter writer) {
		for (int i = 0; i < DATE_SUPPLY_NUMBER; i++) {
			int dateID = i + 1;

			int dayNumber = IdRandomizer.getRandomId(1, 28);
			int monthNumber = IdRandomizer.getRandomId(1, 12);
			int yearNumber = IdRandomizer.getRandomId(2011, 2016);

			int quarterNumber = CalendarUtil.getQuarterNumber(monthNumber);

			String monthName = String.format("Month-%d", monthNumber);

			writer.printf(
					"INSERT INTO SupplyDate (DateID, DayNumber, MonthNumber, MonthNameUKR, MonthNameENG, MothNameRUS,"
							+ "QuarterNumber, YearNumber) VALUES (%d, '%s', '%s', '%s');\n",
					dateID, dayNumber, monthNumber, monthName, monthName, monthName, quarterNumber, yearNumber);
		}

		for (int i = 0; i < DATE_SALE_NUMBER; i++) {
			int dateID = DATE_SUPPLY_NUMBER + i;

			int dayNumber = IdRandomizer.getRandomId(1, 28);
			int monthNumber = IdRandomizer.getRandomId(1, 12);
			int yearNumber = IdRandomizer.getRandomId(2011, 2016);

			int quarterNumber = CalendarUtil.getQuarterNumber(monthNumber);

			String monthName = String.format("Month-%d", monthNumber);

			writer.printf(
					"INSERT INTO SupplyDate (DateID, DayNumber, MonthNumber, MonthNameUKR, MonthNameENG, MothNameRUS,"
							+ "QuarterNumber, YearNumber) VALUES (%d, '%s', '%s', '%s');\n",
					dateID, dayNumber, monthNumber, monthName, monthName, monthName, quarterNumber, yearNumber);
		}
	}
}
