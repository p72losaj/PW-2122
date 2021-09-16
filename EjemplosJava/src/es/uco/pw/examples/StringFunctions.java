package es.uco.pw.examples;

/**
 * A simple program to show the use of String in Java
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class StringFunctions {

	public static void main(String[] args) {
		
		String myFirstString = "I am learning Java";
		
		// Useful methods to manipulate strings
		
		int length = myFirstString.length();	// Number of characters
		System.out.println("The length is: " + length);
		
		String aSecondString = myFirstString.concat(". It's easy!"); // The result is returned as a new string
		System.out.println("After concat: firstString=<" + myFirstString + "> secondString=<" + aSecondString + ">");

		int index = myFirstString.indexOf("J");	// First appearance of a character
		String language = myFirstString.substring(index, index+4);
		System.out.println("After substring: " + language + " Is empty?: " + language.isEmpty());
		
		
	}
}
