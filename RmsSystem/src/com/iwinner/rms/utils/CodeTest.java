package com.iwinner.rms.utils;

public class CodeTest {

	public void primeTest() {
		int no = 11;
		int count = 0;
		for (int i = 1; i <= no; i++) {
			if (no % i == 0) {
				count++;
			}

		}
		if (count == 2) {
			System.out.println("Prime ");
		} else {
			System.out.println("Not Prime");
		}

	}

	public static void main(String[] args) {

		// test();
		// testFor();
		testJava();
	}

	public static void test() {

		int x = 4;
		for (int i = 1; i <= 4; i++) {

			for (int j = 1; j < i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

	public static void testFor() {

		int x = 4;
		for (int i = 4; i > 1; i--) {
			for (int j = 1; j <= i - 1; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

	public static void testJava() {

		java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();
		for (int i = 1; i <= 4; i++)
			list.add(i);
		System.out.println("List Of Values  " + list);
		int fromIndex = 0;
		int toIndex = 1;
		for (int i = 0; i < 4; i++) {
			System.out.println(list.subList(fromIndex, toIndex));
			toIndex++;
		}
	}
}
