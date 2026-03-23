package com.primitivedatatypes;
import java.util.*;

public class palindrome {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no to check");
		int num = sc.nextInt();
		
		int temp= num;
		int rev = 0;
		
		while(temp!=0) {
			
			int n= temp%10;
			rev = rev*10 + n;
			temp= temp/10;
			
			
		}
		
		if(rev == num) {
			
			System.out.println("Palindrom");
		}
		
		else {
			System.out.println("Not Palindrom");
		}
		
	}

}
