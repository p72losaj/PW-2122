package es.uco.pw.classes;

/**
 * A program to show how to use the Person class
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class PersonMainProgram {

	public static void main(String[] args) {
		
		Person p1 = new Person(1234);
		System.out.println(p1.getDni());
		System.out.println(Person.getSpecies());
		
		p1.setName("John");
		p1.setSurname("Smith");
		System.out.println(p1.toString());
		
		Person p2 = new Person("Anna", "Taylor");
		System.out.println(p2.getName() + " " + p2.getSurname());
		
		// The equals method is inherited from Object and can be redefined
		System.out.println(p1.equals(p2));
	}
}
