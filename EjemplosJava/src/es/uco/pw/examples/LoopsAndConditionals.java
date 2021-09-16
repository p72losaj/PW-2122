package es.uco.pw.examples;

import java.util.ArrayList;

/**
 * Examples with loops and conditional structures
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class LoopsAndConditionals {

	public static void main(String[] args) {
		
		/* Conditionals in Java */
		double result = Math.random();
		
		if(result > 0.5) {
			System.out.println("Cara");
		}
		else if (result < 0.5){
			System.out.println("Cruz");
		}
		else {
			System.out.println("De canto! :O");
		}
		
		/* Loops in Java */
		int rows = 2, cols = 3;
		double [][] matrix = new double[rows][cols];
		
		// For
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				matrix[i][j] = Math.random(); // Example of static method (we do not need to create a Math object)
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		// For with collection of objects
		ArrayList<String> names = new ArrayList<String>(4); // We can set a maximum size
		names.add("John");
		names.add("Anna");
		names.add("Mary");
		System.out.println("Printing all names: ");
		for(String s: names) {
			System.out.println(s);
		}
		
		// While
		int n = 0;
		while(n < names.size()) {
			System.out.println(names.get(n).toUpperCase());
			n++;
		}
		
		// Do-while
		do {
			names.remove(0);
		}while(!names.isEmpty());
		System.out.println(names.size());
	}
}
