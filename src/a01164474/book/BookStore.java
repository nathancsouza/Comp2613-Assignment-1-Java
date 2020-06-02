package a01164474.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import a01164474.data.Database;
import a01164474.io.BookReport;
import a01164474.io.CustomerReport;
import a01164474.io.PurchaseReport;
import a01164474.util.LoggingByOptions;

/**
 * Project: Book
 * File: BookStore.java
 * Date: October, 2017
 * Time: 1:22:25 PM
 */

/**
 * @author Sam Cirka, A00123456
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */
public class BookStore {
	
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger lOG = LogManager.getLogger();

	/**
	 * Bcmc Constructor. Processes the commandline arguments
	 * ex. -inventory -make=honda -by_count -desc -total -service
	 * 
	 * @throws ApplicationException
	 * @throws ParseException
	 */
	public BookStore(String[] args) throws ApplicationException, ParseException {
		lOG.info("Created Bcmc");
		BookOptions.process(args);
	}

	/**
	 * Entry point to GIS
	 * 
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		lOG.info(startTime);
		lOG.info("Starting Logging");
		// start the Book System
		try {
			BookStore book = new BookStore(args);
			if (BookOptions.isHelpOptionSet()) {
				BookOptions.Value[] values = BookOptions.Value.values();
				System.out.format("%-5s %-15s %-10s %s%n", "Option", "Long Option", "Has Value", "Description");
				for (BookOptions.Value value : values) {
					System.out.format("-%-5s %-15s %-10s %s%n", value.getOption(), ("-" + value.getLongOption()), value.isHasArg(),
							value.getDescription());
				}

				return;
			}
			book.run();
		} catch (Exception e) {
			e.printStackTrace();
			lOG.debug(e.getMessage());
		}

		Instant endTime = Instant.now();
		lOG.info(endTime);
		lOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME.
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.",
					LOG4J_CONFIG_FILENAME));
			Configurator.initialize(new DefaultConfiguration());
		}
	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void run() throws Exception {
		lOG.debug("run()");
		Database.retrieveData();
		generateReports();
	}

	/**
	 * Generate the reports from the input data
	 * 
	 * @throws FileNotFoundException
	 * @throws Exception 
	 */
	private void generateReports() throws FileNotFoundException, Exception {
		PrintStream output = null;
		lOG.debug("generating the reports");
		
		//Customer Report
		if (BookOptions.isCustomersOptionSet()) {
			lOG.debug("generating the customer report");
			CustomerReport.write(System.out);
			output = new PrintStream(new File(CustomerReport.REPORT_FILENAME));
			CustomerReport.write(output);
			lOG.debug("Logging by customerOptions:");
			LoggingByOptions.logByCustomerOptions();
			output.close();
			lOG.debug("\nCustomer report closed\n");
		}
		
		//Book Report
		if (BookOptions.isBooksOptionSet()) {
			lOG.debug("generating the Book report");
			BookReport.write(System.out);
			output = new PrintStream(new File(BookReport.REPORT_FILENAME));
			BookReport.write(output);
			lOG.debug("Logging by bookOptions:");
			LoggingByOptions.logByBookOptions();
			output.close();
			lOG.debug("\nBook report closed\n");
		}
		
		//Purchase Report
		if (BookOptions.isPurchasesOptionSet()) {			
			lOG.debug("generating the purchase report");				
			PurchaseReport.write(System.out);
			output = new PrintStream(new File(PurchaseReport.REPORT_FILENAME));
			PurchaseReport.write(output);
			lOG.debug("Logging by purchaseOptions:");
			LoggingByOptions.logByPurchaseOptions();
			output.close();
			lOG.debug("\nPurchase report closed\n");
		}
	}	
}
