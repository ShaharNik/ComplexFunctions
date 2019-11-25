package myMath;

import java.util.ArrayList;
import java.util.Iterator;

public class PollyTests 
{
	public static void addMonomToPolly(ArrayList<Monom> monoms, Monom monom_to_add)
	{
		//Monom monom_to_add = new Monom(tmp);
		Iterator<Monom> runner = monoms.iterator();
		boolean monomAddedToPolly = false;
		while(runner.hasNext() && !monomAddedToPolly) // try add the monom if the power is already exist
		{
			Monom m_runner = runner.next();
			if (m_runner.get_power() == monom_to_add.get_power())
			{
				m_runner.add(monom_to_add);
				monomAddedToPolly = true;
			}
		}
		if (!monomAddedToPolly) // the power is not exist in polly, add the monom
		{
			monoms.add(monom_to_add); 
		}
	}
	public static void addSamePowersMonoms(ArrayList<Monom> monoms) // NOT GOOD
	{
		Iterator<Monom> runner1 = monoms.iterator();
		while(runner1.hasNext())
		{
			Iterator<Monom> runner2 = monoms.iterator();
			while (runner2.hasNext())
			{
				if (runner1.next().get_power() == runner2.next().get_power())
				{
					runner1.next().add(runner2.next());
				}
			}

		}
	}

	/* didn't used it
	public static void printPolly(ArrayList<Monom> monoms)
	{
		// Print a Polynom String from monoms Array list
		String str = monoms.toString();
		str = str.replaceAll("\\, ", "+");
		str = str.replaceAll("\\*", "");
		str = str.replaceAll("\\+-", "-");
		str=str.substring(1,str.length()-1); // remove [ and ]
		if(str.charAt(0)=='+') 
			str=str.substring(1,str.length()-2);
		System.out.println(str);
	}
	*/


	public static void testf()
	{
		Polynom_able p= new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5");
		double actual= p.f(1);
		double expected= -4.3;


		if (expected == actual)
			System.out.println("Good f");
		else
			System.out.println("Bad f");

	}

	public static void testSubstract()
	{

		Polynom_able p1= new Polynom("2x^5+3x^3-2");
		Polynom_able p2= new Polynom("2x^4-1x^3+6x^2+5");
		p1.substract(p2);

		if (p1.toString().equals("2.0*x^5-2.0*x^4+4.0*x^3-6.0*x^2-7.0"))
			System.out.println("Good Substract");
		else
			System.out.println("Bad Substract");	

	}

	public static void testAdd()
	{
		Polynom_able p1= new Polynom("5x^5+3x^3-2");
		Polynom_able p2= new Polynom("3x^5+2x^3+6+2x^2");

		p1.add(p2);
		System.out.println(p1);
		if(p1.toString().equals("8.0*x^5+5.0*x^3+2.0*x^2+4.0"))
			System.out.println("Good polynoms add");
		else
			System.out.println("Bad polynoms add");
	}

	public static void testMultiply()
	{
		Polynom_able p11= new Polynom("2x^5+4.0x^3-21");
		Polynom_able p22= new Polynom("0");
		p11.multiply(p22);
		System.out.println(p11);
		System.out.println("is zero? "+p11.isZero());

		Polynom_able p1= new Polynom("x^2+5x+1");
		Polynom_able p2= new Polynom("3x^2-10x+15");
		p1.multiply(p2);
		System.out.println(p1);

		if(p1.toString().equals("3.0*x^4+5.0*x^3-32.0*x^2+65.0*x^1+15.0") && p11.isZero())		
			System.out.println("Good Multiply");
		else
			System.out.println("Bad Multiply");

	}

	public static void testArea()
	{

		Polynom_able p1= new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5");
		double actual= p1.area(-0.941, 4.831, 0.01);
		double expected= 25.183451113677425;
		if(expected == actual)
			System.out.println("Good Area");
		else
			System.out.println("Bad Area");
	}

	public static void testIsZero()  
	{

		Polynom_able p1= new Polynom("3x^4+6x^3-2");
		Polynom_able p2= new Polynom();

		boolean Test_1= p1.isZero();
		boolean Test_2= p2.isZero();
		if(!Test_1 && Test_2)
			System.out.println("Good isZero");
		else
			System.out.println("Bad isZero");

	}

	public static void testRoot()
	{
		Polynom_able p1= new Polynom("4x^3+2x^2+11x+23");
		double temp = p1.root(-2, 0, 0.01);
		double ans_root= -1.4189453125;
		if (temp == ans_root)
			System.out.println("Good Root");
		else
			System.out.println("Bad root");

	}

	public static void testDerivative()
	{
		Polynom_able p = new Polynom("3x^4-2x^3+2x^2");
		p=p.derivative();
		if(p.toString().equals("12.0*x^3-6.0*x^2+4.0*x^1"))
			System.out.println("Good Derivative");
		else
			System.out.println("Bad Derivative");
	}



	public static boolean testPolyStringConstractor() 
	{
		String[] PolynomsToCheck = {"2*x^5-3X+3X+7x^7", "4X^+", "5x^3-6+3x^2", "5x-5X", "x^2+x^2", "1+1", "5X^3+x+X", "5x+5", "5x5x", "x^", "^x","x^-1", "^", "x7+1"};
		final int goodPolynomNumber = 7; // How many polynoms are good in the array?
		final int badPolynomNumber = 7; // How many polynoms are bad in the array?

		int counterGoodPolynoms = 0;
		int counterBadPolynoms = 0;

		for(int i = 0; i<PolynomsToCheck.length; i++) 
		{
			try 
			{
				Polynom p = new Polynom(PolynomsToCheck[i]);
				counterGoodPolynoms++;
				//System.out.println("the good polynoms are: \n " + p.toString());
			}
			catch (Exception e)
			{
				counterBadPolynoms++;
			}
		}
		if(counterGoodPolynoms == goodPolynomNumber && counterBadPolynoms == badPolynomNumber)
		{
			System.out.println("The Test ran good! =) "+goodPolynomNumber+ " polynoms are good, and "+counterBadPolynoms+ " polynoms are bad.");
			//System.out.println("goodCounter is: "+counterGoodPolynoms + "bad counter is: "+counterBadPolynoms);
			return true;
		}
		else
		{
			System.out.println("The Test went wrong .. :( ");
			return false;
		}
	}


	public static void testRoot2() 
	{
		String[] polynoms = {"x^3","-x^2+3" , "x+15", "1.5x^2-6x+3", "-0.6x^2+12x", "-x^6+2x^3-x^1+12" , "0.0043x^6" , "3x" , "17" , "-x^4-2x-1" ,"-0.5x^8"};
		int goodCounter = 0;
		int badCounter = 0;
		for (int i = 0; i < polynoms.length; i++) 
		{
			try
			{
				Polynom p = new Polynom(polynoms[i]);
				System.out.println(i+ ") " + p.toString());
				System.out.println(p.root(-2, 1, 0.05));
				goodCounter++;
			}
			catch (Exception e)
			{
				badCounter++;
				//System.out.print(i + ", ");
			}

		}
		System.out.println("Number of Polynoms root founded: "+goodCounter + " Number of bad polynoms: "+badCounter);
	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//polyString("3.2x^1 -5x^7 +3X^2 +3X -3x^1+6*X^7+6.6x^6-5.4x");

		// Good
		/*
		testRoot();
		testIsZero() ;
		testf();
		testAdd();
		testSubstract();
		testMultiply();
		testPolyStringConstractor();
		testDerivative();
		testArea(); // Good results on debug, double is not large enough
		 */

	}

}
