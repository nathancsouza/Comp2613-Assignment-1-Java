package a01164474.io;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import a01164474.book.ApplicationException;
import a01164474.book.BookOptions;
import a01164474.data.Book;
import a01164474.data.CompareBookByAuthor;
import a01164474.data.CompareBookByAuthorDescending;
import a01164474.data.CompareBookByTitle;
import a01164474.data.CompareBookByTitleDescending;
import a01164474.data.Database;
import a01164474.util.Truncate;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */

public class BookReport {
	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%8s. %-12s %-40s %-40s %4s %6s %13s %-60s";
	public static final String BOOK_FORMAT = "%08d. %-12.12s %-40.40s %-40.40s %4d %6.3f %13d %-60s";	
	public static final String REPORT_FILENAME = "book_report.txt";
	public static final boolean isByAuthor = BookOptions.isByAuthorOptionSet();
	public static final boolean isDescending = BookOptions.isDescendingOptionSet();
	public static final boolean isByTitle = BookOptions.isByTitleOptionSet();	
	
	/**
	 * private constructor to prevent instantiation
	 */
	public BookReport() {
	}
	
	public static void write(PrintStream output) throws ApplicationException, IOException {
		String printConsole = null;
			
		output.println("\nBook Report");
		output.println(HORIZONTAL_LINE);
		printConsole = String.format(HEADER_FORMAT, "ID", "ISBN", "Authors", "Title", "Year", "Rating", "Ratings Count", "Image URL");
		output.println(printConsole);
		output.println(HORIZONTAL_LINE);
		
		Collection<Book> listOfBook = Database.getBooks().values();
	    if (isByAuthor) {
	      List<Book> sortedBooks = new ArrayList<>(listOfBook);
	      if (isDescending) {
	        Collections.sort(sortedBooks, new CompareBookByAuthorDescending());
	      } else {	    	  
	        Collections.sort(sortedBooks, new CompareBookByAuthor());
	      } 
	      listOfBook = sortedBooks;
	    }
	    if (isByTitle) {	    	
	      List<Book> sortedBooks = new ArrayList<>(listOfBook);
	      if (isDescending) {	    	  
	        Collections.sort(sortedBooks, new CompareBookByTitleDescending());
	      } else {
	        Collections.sort(sortedBooks, new CompareBookByTitle());
	      } 
	      listOfBook = sortedBooks;
	    }
		
		for (Book book : listOfBook) {
			String getAuthors = Truncate.truncateString(book.getAuthors(), 40);
			String getTitle = Truncate.truncateString(book.getTitle(), 40);
			String getImageURL = Truncate.truncateString(book.getImageURL(), 60);
			printConsole = String.format(BOOK_FORMAT, book.getId(), book.getIsbn(), getAuthors, getTitle, book.getPublicationYear(), book.getAverageRating(), book.getRatingsCount(), getImageURL);
			output.println(printConsole);
		}
		output.println();		
	}
}
