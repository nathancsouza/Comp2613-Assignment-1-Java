package a01164474.data;

import java.io.IOException;
import java.util.Map;
import a01164474.book.ApplicationException;
import a01164474.io.BookReader;
import a01164474.io.CustomerReader;
import a01164474.io.PurchaseReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */
public class Database {
	private static final Logger lOG = LogManager.getLogger();
	private static Map<Long, Book> listOfBook;
	private static Map<Long, Customer> listOfCustomer;
	private static Map<Long, Purchase> listOfPurchase;
	
	public static void retrieveData() throws IOException, ApplicationException{
		lOG.debug("Starting data loading...");			
		//customer			
		listOfCustomer = CustomerReader.read();
		lOG.debug("Customer has been loaded");
		//book
		BookReader readBookFile = new BookReader();			
		listOfBook = readBookFile.read();
		lOG.debug("Book has been loaded");
		//purchase
		PurchaseReader readPurchaseFile = new PurchaseReader();			
		listOfPurchase = readPurchaseFile.read();
		lOG.debug("Purchase has been loaded");
		lOG.debug("All data has been loaded successfully!");
	}		  
	  
	  public static Map<Long, Customer> getCustomers() {
	    return listOfCustomer;
	  }	  
	  public static Map<Long, Book> getBooks() {
	    return listOfBook;
	  }	  
	  public static Map<Long, Purchase> getPurchases() {
	    return listOfPurchase;
	  }	  	  
}
