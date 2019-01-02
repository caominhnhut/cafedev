package com.warmup.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Execution {

	public static void main(String[] args) {
		System.out.println("**********LIST OF METHODS**********");
		Method[] methods = ListMethod.class.getMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
		}

		System.out.println("**********ATTRIBUTE**********");
		Class<ListMethod> lMClass = ListMethod.class;
		System.out.println("Full name: " + lMClass.getName());
		System.out.println("Simple name:" + lMClass.getSimpleName());

		Package pkage = lMClass.getPackage();
		System.out.println("Package name:" + pkage.getName());

		int modifiers = lMClass.getModifiers();
		boolean isPublic = Modifier.isPublic(modifiers);
		boolean isInterface = Modifier.isInterface(modifiers);
		boolean isAbstract = Modifier.isAbstract(modifiers);
		boolean isFinal = Modifier.isFinal(modifiers);

		// true
		System.out.println("Is Public? " + isPublic);
		// true
		System.out.println("Is Final? " + isFinal);
		// false
		System.out.println("Is Interface? " + isInterface);
		// false
		System.out.println("Is Abstract? " + isAbstract);

		System.out.println("*****************CAT**************");
		Class<Cat> catClass = Cat.class;
		System.out.println("Class name: " + catClass.getSimpleName());

		Class<?> catBaseClass = catClass.getSuperclass();
		System.out.println("Base class: " + catBaseClass.getSimpleName());

		Class<?>[] catInterfaces = catClass.getInterfaces();
		for (Class<?> interf : catInterfaces) {
			System.out.println("Interfaces: " + interf.getSimpleName());
		}

		Constructor<?>[] constructors = catClass.getConstructors();
		for (Constructor<?> con : constructors) {
			System.out.println("Contructor name: " + con.getName());
		}

		Field[] fields = catClass.getFields();
		for (Field f : fields) {
			System.out.println("Field name: " + f.getName());
		}

		try {
			Constructor<?> constructorWithParam = catClass.getConstructor(String.class, int.class);
			Class<?>[] paramClass = constructorWithParam.getParameterTypes();
			for (Class<?> type : paramClass) {
				System.out.println("Param type: " + type.getName());
			}

			Cat tom = (Cat) constructorWithParam.newInstance("Tom", 5);
			System.out.println("Cat name: " + tom.getName());
			System.out.println("Cat's age: " + tom.getAge());

			Field nof = catClass.getField("NUMBER_OF_LEGS");
			Class<?> fieldType = nof.getType();
			System.out.println("Field type: " + fieldType.getSimpleName());

			Field ageField = catClass.getField("age");
			Cat tom1 = new Cat("Tom", 10);
			Integer age = (Integer) ageField.get(tom1);
			System.out.println("Age = " + age);

			ageField.set(tom, 7);

			System.out.println("New Age = " + tom.getAge());

			Method getAgeMethod = catClass.getMethod("getAge");

			Class<?> returnType = getAgeMethod.getReturnType();
			System.out.println("Return type of getAge: " + returnType.getSimpleName());

			Cat tom3 = new Cat("Tom", 9);

			int age1 = (int) getAgeMethod.invoke(tom);

			System.out.println("Age = " + age1);

			Method setAgeMethod = catClass.getMethod("setAge", int.class);

			setAgeMethod.invoke(tom, 15);

			System.out.println("New Age = " + tom.getAge());

			Field privateField = catClass.getDeclaredField("name");
			privateField.setAccessible(true);
			String fieldValue = (String) privateField.get(tom3);
			System.out.println("Private value field: " + fieldValue);
			privateField.set(tom3, "Jerry");
			System.out.println(tom3.getName());

			Method private_setNameMethod = catClass.getDeclaredMethod("setName", String.class);
			private_setNameMethod.setAccessible(true);
			private_setNameMethod.invoke(tom, "Tom Cat");

			System.out.println("New name = " + tom.getName());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		for (Method method : methods) {
			boolean isSetter = isSetter(method);
			boolean isGetter = isGetter(method);
			System.out.println("Method: " + method.getName());
			System.out.println(" - Is Setter? " + isSetter);
			System.out.println(" - Is Getter? " + isGetter);
		}

		Annotation[] annotations = catClass.getAnnotations();
		for (Annotation ano : annotations) {
			System.out.println(ano.annotationType().getSimpleName());
		}
		Annotation ann = catClass.getAnnotation(MyAnnotation.class);
		MyAnnotation myAnn = (MyAnnotation) ann;
		System.out.println("Name = " + myAnn.name());
		System.out.println("Value = " + myAnn.value());
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		if (void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}
}
