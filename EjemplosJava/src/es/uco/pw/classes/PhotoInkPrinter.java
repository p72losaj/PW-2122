package es.uco.pw.classes;

/**
 * A subclass that inherits from InkPrinter
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class PhotoInkPrinter extends InkPrinter {

	/* Specific class attributes (it also inherits the InkPrinter attributes) */
	
	protected int width;
	
	protected int height;
	
	/**
	 * Parameterized constructor
	 * @param capacity Maximum queue capacity
	 * */
	public PhotoInkPrinter(int capacity) {
		super(capacity);
		super.model = "CP1050"; // We can access to this attribute because it is protected
	}
	
	/* Override a method from the super class */
	
	@Override
	public void cancelPendingDocuments() {
		super.cancelPendingDocuments(); // Do the same than the parent class
		this.width = 0;					// Do anything else
		this.height = 0;
	}
	
	/* Specific methods for the subclass */
	
	/**
	 * Set the photo dimension
	 * @param width Width in pixels
	 * @param height Height in pixels
	 * */
	public void setPhotoDimension(int widht, int height) {
		this.width = widht;
		this.height = height;
	}
}
