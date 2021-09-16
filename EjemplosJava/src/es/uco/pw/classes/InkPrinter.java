package es.uco.pw.classes;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * A class that implements the IPrinter interface
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class InkPrinter implements IPrinter {

	/* Class attributes */
	
	protected Queue<String> documents;
	
	protected String model = "CP1025";
	
	/* Constructor */
	
	/**
	 * Parameterized constructor
	 * @param capacity Maximum queue capacity
	 * */
	public InkPrinter(int capacity) {
		// Queue is an interface, so we should instantiate a particular implementation
		this.documents = new ArrayBlockingQueue <String>(capacity);
	}
	
	/* Methods from the interface */
	
	@Override
	public void sendDocument(String text) {
		this.documents.add(text);
	}
	
	@Override
	public void printDocuments() {
		
		// An example of lambda expression to iterate through the queue elements
		System.out.println("========= MODE 1: Using lambda expressions");
		this.documents.forEach(d -> System.out.println("\t" + d));
		
		// Another option: use an iterator
		System.out.println("========= MODE 2: Using an iterator");
		Iterator<String> it = this.documents.iterator();
		while(it.hasNext()) {
			System.out.println("\t" + it.next());
		}
		
		// Another option: retrieve the head of the queue and remove in one step
		System.out.println("========= MODE 3: Using queue retrieving method");
		while(!this.documents.isEmpty()) {
			System.out.println("\t" + this.documents.poll());
		}
	}

	@Override
	public int getNumberOfDocumentsQueue() {
		return this.documents.size();
	}

	@Override
	public void cancelPendingDocuments() {
		this.documents.clear();
	}
	
	/* Other methods can be specific for this class */
	
	/**
	 * Get the type of printer
	 * @return A string representing the printer model
	 * */
	public String getPrinterModel() {
		return model;
	}

}
