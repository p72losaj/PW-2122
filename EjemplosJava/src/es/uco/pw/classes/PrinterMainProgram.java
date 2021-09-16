package es.uco.pw.classes;

/**
 * A program to use the printer class
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class PrinterMainProgram {

	public static void main(String[] args) {
		IPrinter myPrinter = new InkPrinter(10);

		myPrinter.sendDocument("First text to be printed.");
		myPrinter.sendDocument("I also want to print this.");
		myPrinter.sendDocument("Last document to print.");
		
		System.out.println("Documents to be printed: " + myPrinter.getNumberOfDocumentsQueue());
		myPrinter.printDocuments();
		System.out.println("Documents to be printed: " + myPrinter.getNumberOfDocumentsQueue());
		
		myPrinter.sendDocument("I forgot this one!");
		System.out.println("Documents to be printed: " + myPrinter.getNumberOfDocumentsQueue());
		myPrinter.cancelPendingDocuments();
		System.out.println("Documents to be printed: " + myPrinter.getNumberOfDocumentsQueue());
	}
}
