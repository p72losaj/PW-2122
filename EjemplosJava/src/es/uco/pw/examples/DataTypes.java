package es.uco.pw.examples;

/**
 * A simple program to show Java types
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class DataTypes {

	public static void main(String[] args) {
		
		// Numeric
		byte byteVar = 1;
		short shortVar = 2;
		int intVar = 5;
		long longVar = 1000000000;
		float floatVar = 1/2;
		double doubleVar = 2.3;
		
		System.out.println("Values: byte=" + byteVar + " short=" + shortVar + " int=" + intVar +
							" long=" + longVar + " float=" + floatVar + " double=" + doubleVar);
		
		// The result of dividing two integers is another integer!
		floatVar = (float)1/2;
		System.out.println("Casting to float: " + floatVar);
		
		// No numeric
		boolean boolVar = true;
		char charVar = 'c';
		System.out.println("Values: boolean=" + boolVar + " char=" + charVar);
				
		
		// Numeric classes
		Integer intObject = new Integer(2); 		// Deprecated for Java > 9
		Integer intObject2 = Integer.valueOf(3);	// Better space and performance with the Factory method
		
		// You can check the value and type, convert to other data formats, compare to others values
		System.out.println("Type: " + Integer.TYPE + " value: " + intObject.intValue());
		System.out.println("To byte: " + intObject2.byteValue() + " To float: " + intObject2.floatValue() + " To string: " + intObject2.toString());
		System.out.println("Compare to 5: " + intObject2.compareTo(5)); // 0 are equals, -1 is smaller, > 1 is greater
		
		// No numeric classes
		Boolean boolObject = new Boolean(false);		// Deprecated for Java > 9
		Boolean boolObject2 = Boolean.valueOf("true"); 	// Create value by parsing the string
		System.out.println("Type1: " + Boolean.TYPE + " value1: " + boolObject.booleanValue() + " value2: " + boolObject2.booleanValue());
		
		// We can also do some logical operations
		boolean resultAnd = Boolean.logicalAnd(true, false);
		boolean resultOr = Boolean.logicalOr(true, true);
		System.out.println("resultAnd=" + resultAnd + " resultOr=" + resultOr);
	}

}
