package com.java8.day1;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
