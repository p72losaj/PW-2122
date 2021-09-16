package es.uco.pw.examples;

/**
 * A little program to show how to catch exceptions in Java
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class Exceptions {

	public static void main(String[] args) {
		String [] letters = {"a", "b", "c", "d"};
		try {
			for(int i=0; i<5; i++) {
				System.out.println(letters[i]);
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("You are trying to access to an invalid index.");
		}
		catch(Exception e) {
			System.err.println("This is a generic exception... ");
			e.printStackTrace(); // It prints the method calls that generates the exception
		}
		finally {
			System.err.println("This message always appears...");
		}
	}

}
