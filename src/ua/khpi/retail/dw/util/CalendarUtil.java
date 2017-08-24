package ua.khpi.retail.dw.util;

public class CalendarUtil {

	public static int getQuarterNumber(int monthNumber) {
		int quarterNumber = 1;
		
		if (monthNumber >= 4) {
			quarterNumber = 2;
		}
		
		if (monthNumber >= 7) {
			quarterNumber = 3;
		}
		
		if (monthNumber >= 10) {
			quarterNumber = 4;
		}
		
		return quarterNumber;
	}
}
