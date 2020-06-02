package a01164474.util;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */

public class Truncate {
	
	public static String truncateString(String input, int maxLength) {
	    if (input.length() <= maxLength) 
	        return input;
	    else 
	        return input.substring(0, maxLength-3) + "...";
	}
}
