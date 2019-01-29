package com.warmup.comparision;
import java.util.Comparator;

public class Employee implements Comparable<Employee>{

	private int id;
	private String name;
	private int age;
	private long salary;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public long getSalary() {
		return salary;
	}

	public Employee(int id, String name, int age, int salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", salary=" + this.salary + "]\n";
	}

	@Override
	public int compareTo(Employee o) {
		//let's sort the employee based on an id in ascending order
        //returns a negative integer, zero, or a positive integer as this employee id
        //is less than, equal to, or greater than the specified object.
        //return (this.id - o.id); sort by id as ASC
		//return (o.id - this.id); sort by id as DESC
		return (o.name.compareTo(this.name));
	}
	
	public static Comparator<Employee> compareSalary = new Comparator<Employee>(){

		@Override
		public int compare(Employee o1, Employee o2) {
			return (int) (o1.getSalary() - o2.getSalary());
		}
	};

}
