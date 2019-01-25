package com.nanourl.helpers;

/*
 * Module which generates unique number for each long URL and encodes that 
 * number to alphnumeric string which is used in creating short URL
 * 
 * Author: Harsh Vasoya
 */

public class UniqueKeyGenerator {

	/* Generates a unique number based on the current time */
	public long generateKey() {
		long timeInMilliSec = System.currentTimeMillis();
		timeInMilliSec %= Constants.MOD;

		return timeInMilliSec;
	}

	/* Converts number to base62 string */
	public String convertToBase62(long input) {
		String output = "";
		while (input > 0) {
			int num = (int) (input % Constants.BASE_NUM);
			output += Constants.KEYS.charAt(num);
			input /= Constants.BASE_NUM;
		}

		while (output.length() < 6) {
			output += Constants.KEYS.charAt(0);
		}

		return output;
	}
}
