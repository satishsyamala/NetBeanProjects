package com.hes.util;

public class NumberToWord {

	public String pw(int n, String ch)

	{
		String result="";
		String one[] = { " ", " one", " two", " three", " four", " five",
				" six", " seven", " eight", " Nine", " ten", " eleven",
				" twelve", " thirteen", " fourteen", "fifteen", " sixteen",
				" seventeen", " eighteen", " nineteen" };
		String ten[] = { " ", " ", " twenty", " thirty", " forty", " fifty",
				" sixty", "seventy", " eighty", " ninety" };
		if (n > 19) {
			result=ten[n / 10] + " " + one[n % 10];
		} else {
			result = one[n];
		}
		if (n > 0)
			result+=ch;
		
		return result;
	}

	public static void main(String[] args)

	{

		int n = 234567;
		System.out.print( getNuberInWord(n));
		
	}
	
	public static String getNuberInWord(int n)
	{
		String result="";
		if (n <= 0)
			result="Zero";
		else {
			NumberToWord a = new NumberToWord();
			//System.out.print("After conversion number in words is :");
			result+=a.pw((n / 1000000000), "  Hundred ");
			result+=a.pw((n / 10000000) % 100, "  crore ");
			result+=a.pw(((n / 100000) % 100), "  lakh ");
			result+=a.pw(((n / 1000) % 100), "  thousand ");
			result+=a.pw(((n / 100) % 10), "  hundred ");
			result+=a.pw((n % 100), " ");
		}
		return result;
	}
}