package a01164474.io;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import a01164474.book.BookOptions;
import a01164474.data.Book;
import a01164474.data.Buyer;
import a01164474.data.ComparePurchaseByLastName;
import a01164474.data.ComparePurchaseByLastNameDescending;
import a01164474.data.ComparePurchaseByTitle;
import a01164474.data.ComparePurchaseByTitleDescending;
import a01164474.data.Customer;
import a01164474.data.Database;
import a01164474.data.Purchase;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */

public class PurchaseReport {
	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%-24s %-80s %4s";
	public static final String BOOK_FORMAT = "%-24s %-80s $%.2f";
	public static final String REPORT_FILENAME = "purchase_report.txt";
	public static final boolean isDescending = BookOptions.isDescendingOptionSet();
	public static final boolean isByLastName = BookOptions.isByLastnameOptionSet();
	public static final boolean isByTitle = BookOptions.isByTitleOptionSet();
	public static final boolean hasTotal = BookOptions.isTotalOptionSet();
	
	/**
	 * private constructor to prevent instantiation
	 */
	public PurchaseReport() {
	}
	private static ArrayList<Buyer> buyers;
	
	public static void write(PrintStream output) throws IOException {
		
		String printConsole = null;
		
		output.println("\nPurchase Report");
		output.println(HORIZONTAL_LINE);
		printConsole = String.format(HEADER_FORMAT, "Name", "Title", "Price");
	    output.println(printConsole);
		output.println(HORIZONTAL_LINE);		
		
		Collection<Purchase> listOfPurchase = Database.getPurchases().values();
		Map<Long, Customer> customers = Database.getCustomers();
		Map<Long, Book> books = Database.getBooks();
		buyers = new ArrayList<>();
		
		for (Purchase purchase : listOfPurchase) {
			long customerID = purchase.getCustomerID();
			long bookID = purchase.getBookID();
			Book book = books.get(Long.valueOf(bookID));
		    Customer customer = customers.get(Long.valueOf(customerID));
		    double price = purchase.getPrice();		    
		    if(BookOptions.getCustomerId() != null) {
		    	if(BookOptions.getCustomerId().equals(String.valueOf(purchase.getCustomerID()))) {
			    	Buyer buyer = new Buyer(customer.getFirstName() +" "+ customer.getLastName() , customer.getLastName(), book.getTitle(), price);
					buyers.add(buyer);
		    	}
		    }else {
				Buyer buyer = new Buyer(customer.getFirstName() +" "+ customer.getLastName() , customer.getLastName(), book.getTitle(), price);
				buyers.add(buyer);
		    }		    		    
		}
		if (isByLastName) {
		      if (isDescending) {
		        Collections.sort(buyers, new ComparePurchaseByLastNameDescending());
		      } else {
		          Collections.sort(buyers, new ComparePurchaseByLastName());
		      } 
		    } 
		    if (isByTitle) {
		      if (isDescending) {
		        Collections.sort(buyers, new ComparePurchaseByTitleDescending());
		      } else {
		        Collections.sort(buyers, new ComparePurchaseByTitle());
		      } 
		    }
		    output.println();
		double total = 0.0;
	    for (Buyer buyer : buyers) {	    	 	    	
	    	if (hasTotal)
	    		total += buyer.getPrice();	    	
	    	printConsole = String.format(BOOK_FORMAT, buyer.getFirstName(), buyer.getTitle(), buyer.getPrice());
 	    	output.println(printConsole);
	    }	    
	    Object[] purchases = new Object[] { Double.valueOf(total) };
	    if (hasTotal) {	    	
	    	printConsole = String.format("%nValue of purchases: $%,.2f\n ", purchases);
	    	output.println(printConsole);
 	     }		    
	    output.println();
	}
}
