package es.uco.pw.classes;

/**
 * A class to represent a person
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class Person {

	/* Attributes */
	
	private int dni;
	
	static private String species;
	
	private String name;
	
	private String surname;
		
	/* Constructors */
	
	/**
	 * Empty (default) constructor
	 * */
	public Person() {
		Person.species = "Homo Sapiens";
	}
	
	/**
	 * Parameterized constructor
	 * @param dni The dni number
	 * */
	public Person(int dni) {
		this.dni = dni;
		Person.species = "Homo Sapiens";
	}
	
	/**
	 * Parameterized constructor
	 * @param name The name of the person
	 * @param surname The surname of the person
	 * */
	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
		Person.species = "Homo Sapiens";
	}

	/* Getters and setters */
	// Eclipse can generate these methods automatically...
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public static String getSpecies() {
		return species;
	}

	public static void setSpecies(String species) {
		Person.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/* Other methods */
	
	@Override
	public String toString() {
		String personInfo = "My name is " + this.name + " " + this.surname; // Another way to concat strings
		return personInfo;
	}
}
