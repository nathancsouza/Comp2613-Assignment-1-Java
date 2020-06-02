package a01164474.io;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import a01164474.book.ApplicationException;
import a01164474.book.BookOptions;
import a01164474.data.CompareCustomerByJoinedDate;
import a01164474.data.CompareCustomerByJoinedDateDescending;
import a01164474.data.Customer;
import a01164474.data.Database;
import a01164474.util.Common;


/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */
public class CustomerReport {
	
	public static final String HORIZONTAL_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-13s %-15s %-40s %-25s %-12s %-15s %-45s%s";
	public static final String CUSTOMER_FORMAT = "%3d. %06d %-13s %-15s %-40s %-25s %-12s %-15s %-45s%s";
	public static final String REPORT_FILENAME = "customers_report.txt";
	public static final boolean isDescending = BookOptions.isDescendingOptionSet();
	public static final boolean isByJoinDate = BookOptions.isByJoinDateOptionSet();
	
	/**
	 * private constructor to prevent instantiation
	 */
	public CustomerReport() {
	}
	
	/**
	 * Print the report.
	 * @param map 
	 * @param readCustomersFile 
	 * @param customers 
	 * @throws IOException 
	 */
	public static void write(PrintStream output) throws ApplicationException, IOException {
		
		String printConsole = null;
		
		output.println("\nCustomers Report");
		output.println(HORIZONTAL_LINE);
		printConsole = String.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
		output.println(printConsole);
		output.println(HORIZONTAL_LINE);
		
		Collection<Customer> listOfCustomers = Database.getCustomers().values();
		List<Customer> sortedCustomers = new ArrayList<>(listOfCustomers);
		if (isByJoinDate) {					
				Collections.sort(sortedCustomers, new CompareCustomerByJoinedDate());
			if (isDescending) {
				Collections.sort(sortedCustomers, new CompareCustomerByJoinedDateDescending());
			}else {			
				Collections.sort(sortedCustomers, new CompareCustomerByJoinedDate());
			}
			listOfCustomers = sortedCustomers;
		} 
		
		int i = 0;
		for (Customer customer : listOfCustomers) {			
			LocalDate date = customer.getJoinedDate();
			printConsole = String.format(CUSTOMER_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(),
			customer.getCity(), customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), Common.DATE_FORMAT.format(date));
			output.println(printConsole);				
		}		
		output.println();
	}
	
}
