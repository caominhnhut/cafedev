package com.warmup.reflection;

@MyAnnotation(name = "Table", value = "Employee")
public class Cat extends Animal implements Say {

	public static final String SAY = "Meo meo";
	public static final int NUMBER_OF_LEGS = 4;

	// Private field.
	private String name;

	// Private field
	public int age;

	public Cat() {

	}

	public Cat(String name) {
		this.name = name;
		this.age = 1;
	}

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	// Private Method.
	private void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Implements from interface Say.
	 */
	@Override
	public String say() {
		return SAY;
	}

	/**
	 * Implements from Animal.
	 */
	@Override
	public int getNumberOfLegs() {
		return NUMBER_OF_LEGS;
	}

}
