package a01164474.data;

/**
 * Project: Assignment 1
 * @author Nathan Souza, A01164474
 */

public class Buyer {
	
	private String firstName;    
    private String lastName;    
    private String title;    
    private double price;
    
    public Buyer(String firstName, String lastName, String title, double price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.price = price;        
      }

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Buyer [firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", price=" + price
				+ "]";
	}    
    
}
