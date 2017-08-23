package ua.khpi.retail.dw.util;

import java.util.concurrent.ThreadLocalRandom;

public class IdRandomizer {

	public static int getRandomId(int from, int to) {
		return ThreadLocalRandom.current().nextInt(from, to + 1);
	}
}
