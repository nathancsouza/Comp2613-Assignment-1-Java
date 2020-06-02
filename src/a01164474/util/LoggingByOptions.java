package a01164474.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.io.BookReport;
import a01164474.io.CustomerReport;
import a01164474.io.PurchaseReport;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */

public class LoggingByOptions {
	
	private static final Logger lOG = LogManager.getLogger();

	public static void logByPurchaseOptions() {
		if(PurchaseReport.isByLastName) {
			lOG.debug("isByLastName = " + PurchaseReport.isByLastName);
		    lOG.debug("isDescending = " + PurchaseReport.isDescending);
		}
		if(PurchaseReport.isByTitle) {
			lOG.debug("isByTitle = " + PurchaseReport.isByTitle);
		    lOG.debug("isDescending = " + PurchaseReport.isDescending);
		}
		if(PurchaseReport.hasTotal) {
			lOG.debug("hasTotal = " + PurchaseReport.hasTotal);
		}
		if(!PurchaseReport.isByTitle && !PurchaseReport.isByTitle && !PurchaseReport.hasTotal) {
			lOG.debug("isByLastName = " + PurchaseReport.isByLastName);
		    lOG.debug("isByTitle = " + PurchaseReport.isByTitle);
		    lOG.debug("isDescending = " + PurchaseReport.isDescending);
		    lOG.debug("hasTotal = " + PurchaseReport.hasTotal);
		}
	}
	
	public static void logByBookOptions() {
		if(BookReport.isByAuthor) {
			lOG.debug("isByAuthor = " + BookReport.isByAuthor);
			lOG.debug("isDescending = " + BookReport.isDescending);
		}
		if(BookReport.isByTitle) {
			lOG.debug("isByTitle = " + BookReport.isByTitle);
			lOG.debug("isDescending = " + BookReport.isDescending);
		}	
		if(!BookReport.isByAuthor && !BookReport.isByTitle ) {
			lOG.debug("isByAuthor = " + BookReport.isByAuthor);			
			lOG.debug("isByTitle = " + BookReport.isByTitle);
			lOG.debug("isDescending = " + BookReport.isDescending);
		}
	}
	
	public static void logByCustomerOptions() {
		if(CustomerReport.isByJoinDate) {
			lOG.debug("isByJoinDate = " + CustomerReport.isByJoinDate);
			lOG.debug("isDescending = " + CustomerReport.isDescending);
		}
		if(!CustomerReport.isByJoinDate) {
			lOG.debug("isByJoinDate = " + CustomerReport.isByJoinDate);
			lOG.debug("isDescending = " + CustomerReport.isDescending);
		}
	}
}
