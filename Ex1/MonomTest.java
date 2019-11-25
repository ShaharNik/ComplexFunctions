package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest 
{
	public static void main(String[] args) 
	{
		//test1();
		//test2();
		//test_add();
		//regular();
		//test3();
	}
	private static void test1() 
	{
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"-2", "-x","-3.2x^2","0", "X", "5X^0", "4*x^2"};
		for(int i=0;i<monoms.length;i++) 
		{
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() 
	{
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));

		for(int i=0;i<monoms.size();i++) 
		{
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}

	private static void test_add()
	{
		System.out.println("*****  Test add:  *****");
		Monom m1 = new Monom(2,5); // Not the same powers
		Monom m2 =new Monom(-1,0);
		System.out.println(m1.toString());
		System.out.println("+        +");
		System.out.println(m2.toString());
		m1.add(m2);
		System.out.println("=========");
		System.out.println(m1.toString());
		System.out.println("-----------------");


		Monom m3 =new Monom(-1.3,5);
		Monom m4 =new Monom(-2.2,5);
		System.out.println(m3.toString());
		System.out.println("+        +");
		System.out.println(m4.toString());
		m3.add(m4);
		System.out.println("=========");
		System.out.println(m3.toString());
		System.out.println("-----------------");


		Monom m5 =new Monom(5,2);
		Monom m6 =new Monom(4,2);
		System.out.println(m5.toString());
		System.out.println("+        +");
		System.out.println(m6.toString());
		m5.add(m6);
		System.out.println("=========");
		System.out.println(m5.toString());


	}
	private static void test3()
	{
		String[] testForMonomial = new String[] {"5", "x", "x^2", "(2+2)x", "X", "999x^25", "2y", "1+1", "^", "x^", "5^3", "^x", "5x+5", "5*x^3", "x3", "x^0",  "60X^1", "5^0", "x^-1", "5x5x"  };
		int counterFalse = 0; // needs to be 9
		int counterTrue = 0; // needs to be 11
		Monom m;
		for (int i = 0; i < testForMonomial.length; i++)
		{
			try
			{
				m = new Monom(testForMonomial[i]);
				counterTrue++;
				//System.out.println(m.toString());
			}
			catch(Exception e)
			{
				counterFalse++;
			}
		}
		if(counterFalse == 12 && counterTrue == 8)
			System.out.println("Test for monomial ran well");
		else
			System.out.println("Test for monomial has faild.. ");
	}

	private static void regularTest()
	{
		String[] arr = new String[10];
		arr[0] = "asd34";
		arr[1] = "*x";
		arr[2] = "*^6";
		arr[3] = "*";
		arr[4] = "X";
		arr[5] = ".";
		arr[6] = "^";
		arr[7] = "0.x^3";
		arr[8] = "-1.0*x^1";
		arr[9] = "1-x";

		for(int i = 0; i < arr.length;i++)
		{
			//arr[i].replaceAll("X", "x");
			if(arr[i].matches("[+-]?[0-9]*[.]?[0-9]*\\*?[xX]?\\^?[0-9]*"))
			{
				if (arr[i].contains(".x") || arr[i].equals("^") || arr[i].equals("+") || arr[i].equals("-") || arr[i].charAt(arr[i].length()-1) == '^')
				{
					System.out.println(arr[i] +" NOT matches!!");
				}
				else
				{
					System.out.println(arr[i] +" matches!!");
				}
			}
			else
			{
				System.out.println(arr[i] +" NOT matches!!");
			}
		}
	}

}
