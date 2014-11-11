package com.mehshad.puzzlers.parentChild;

public class ParentChildTest {

	
	public static void main(String[] args) {
		Child child = new Child();
		Parent parent = child;
		parent.print();//Dynamic polymorphism
		parent.print2();//No polymorphism as method is static
		System.out.println(parent.name);//No dynamic polymorphoism for shadowed fields
	}
}
