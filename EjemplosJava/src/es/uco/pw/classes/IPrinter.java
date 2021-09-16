package es.uco.pw.classes;

/**
 * An interface to declare printer operations
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public interface IPrinter {
	
	/**
	 * Send a new document to the printer
	 * */
	public void sendDocument(String text);
	
	/**
	 * Print the documents in the queue
	 * */
	public void printDocuments();
	
	/**
	 * Get the number of documents waiting
	 * */
	public int getNumberOfDocumentsQueue();
	
	/**
	 * Cancel current documents in the queue
	 * */
	public void cancelPendingDocuments();
}
