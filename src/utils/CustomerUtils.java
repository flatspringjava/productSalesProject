package utils;

import java.util.Scanner;

/**
 * 
 * @author 이시화
 * 
 *
 */
public class CustomerUtils {
		
	private static Scanner scanner = new Scanner(System.in);

		public static String nextLine(String msg) {
			System.out.println(msg);
			return scanner.nextLine();

		}
			public static int nextInt(String msg) {
				return Integer.parseInt(nextLine(msg));
			}
			

	
}
