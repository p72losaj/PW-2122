package es.uco.pw.examples;

import java.util.ArrayList;

/**
 * Examples with array and array list
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class Arrays {

	public static void main(String[] args) {

		// Java initializes with zeroes
		double [] data = new double[4]; 
		System.out.println(data[0]);
		
		// Assign values
		data[0] = 1.2;
		data[1] = 3.8;
		data[2] = 6.5;
		data[3] = data[2];
		
		// We can also assign the values when defining the variable
		String week [] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		System.out.println(week.length);
		
		// Array list
		ArrayList<String> names = new ArrayList<String>(4); // We can set a maximum size
		names.add("John");
		names.add("Anna");
		names.add("Mary");
		names.add("Peter");
		names.add("Harry"); // Java automatically adds space
		System.out.println("Size: " + names.size());
		System.out.println("Name at position 2: " + names.get(2));
		names.remove(2);
		System.out.println("Name at position 2 after removal: " + names.get(2));
		names.set(3, "Jane");
	}
}
