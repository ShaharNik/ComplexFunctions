package Ex1;
/**
 * 

 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ComplexFunctionTest 
{
	public static final double EPS = 0.00001;
	@Test
	void test() 
	{
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		System.out.println(cf.toString());
		cf.mul(m2);
		System.out.println(cf.toString());
		Polynom p = new Polynom();
		p.add(m1);
		p.add(m2);
		p.multiply(m2);
		double v = 4.0;
		double dp = p.f(v);
		double dcf = cf.f(v);
		double dd = Math.abs(dp-dcf);
		if (dd > EPS) 
		{
			System.out.println(p+" at "+v+" = "+dp);
			System.out.println(cf+" at "+v+" = "+dcf);
			fail("ERR: should got the same value from: "+p+"  and "+cf);

		}
	}

	@Test
	void testOfString() 
	{
		Polynom p1 = new Polynom();
		p1.add(new Monom(2,2));
		Polynom p2 = new Polynom();
		p2.add(new Monom(3,3));
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);
		System.out.println(cf.toString());
		cf.mul(m2);
		cf3.mul(m2);
		String s = cf.toString();
		function cf2 = cf.initFromString(s); 
		if(!cf.equals(cf2)) // equals is not perfect.. 
		{
			fail("ERR: "+cf+" should be equals to "+cf2);
		}
		if(!cf.equals(cf3)) 
		{
			fail("ERR: "+cf+" should be equals to "+cf3);
		}
	}
	@Test
	void testComplexFunction() 
	{
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x -1","x -2", "x -3", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		for(int i=1;i<s3.length;i++) 
		{
			p3.multiply(new Polynom(s3[i]));
		}
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("divid", new Monom("x"),p3);
		cf.div(p1);
		String s = cf.toString();
		function cf5 = cf4.initFromString(s);
		if(!cf.equals(cf5)) // equals is not perfect..
		{
			fail("ERR: "+cf+" should be equals to "+cf5);
		}
		int size=10;
		for(int i=0;i<size;i++) 
		{
			double x = Math.random();
			double d = cf.f(x);
			double d5 = cf5.f(x);
			assertEquals(d,d5,Monom.EPSILON);
		}
		/*
		System.out.println("cf: ");
		System.out.println(cf.toString());
		System.out.println("cf4: ");
		System.out.println(cf4.toString());
		System.out.println("cf5: ");
		System.out.println(cf5.toString());
		 */
	}
	@Test
	void equalsTest()
	{
		// Part 1
		ComplexFunction a = new ComplexFunction();
		function f1 = a.initFromString("Plus(x,x)");
		function f2 = a.initFromString("2x");
		assertEquals(f1, f2);

		// Part 2
		function f = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Plus", f, b);
		ComplexFunction t2 = new ComplexFunction("Plus", f, b);
		if (!(t1.equals(t2))) 
			fail("ERR: Object are not equals");

		t2 = new ComplexFunction("Plus", b, f);
		//if (!(t1.equals(t2))) 
		//fail("ERR: Object are not equals");

		function c = new Polynom("x^2+2x");
		function d = new Polynom("0");
		t2 = new ComplexFunction("Plus", c, d);
		if (!(t1.equals(t2))) 
			fail("ERR: Object are not equals");
	}
	@Test
	void testPlus() 
	{
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Plus", a, b);
		if ((t1.f(3))!=15) 
			fail("ERR: testPlus");
	}

	@Test
	void testMul() 
	{
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Times", a, b);
		if ((t1.f(3))!=54) 
			fail("ERR: testMul");
	}

	@Test
	void testDiv() 
	{
		function a = new Polynom("2x^3");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Divid", a, b);
		if ((t1.f(7))!=14) 
			fail("ERR: testDiv");
		b = new Polynom("0");
		t1 = new ComplexFunction("Divid", a, b);
		try 
		{
			t1.f(4);
			fail("ERR: testDiv");;
		}
		catch (Exception e) {;}
	}

	@Test
	void testMax() {
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("max", a, b);
		if ((t1.f(-1))!=1) 
			fail("ERR: testMax");
	}

	@Test
	void testMin() {
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("min", a, b);
		if ((t1.f(-1))!=-2) 
			fail("ERR: testMin");
	}

	@Test
	void testComp() {
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Comp", a, b);
		if (t1.f(3)!=18) 
			fail("ERR: testComp");
	}
	@Test
	void testInitFromString() 
	{
		String s1 = "0";
		Polynom p1 = new Polynom(s1);
		function x = new ComplexFunction(p1);
		String expected = "plus(max(plus(-6.0x,2.0x),plus(2.0x,-6.0x)),plus(max(plus(-6.0x,2.0x),plus(2.0x,-6.0x)),2.0x))";
		try 
		{
			x = x.initFromString(expected);
		}
		catch (Exception e) 
		{
			fail("ERR: testInitFromString");
		}
		//part 2
		Polynom p3 = new Polynom();
		p1.add( new Monom(2,2));
		Polynom p2 = new Polynom();
		p2.add(new Monom(3,3));
		Monom m1 = new Monom(2,2);
		Monom m2 = new Monom(3,3);
		ComplexFunction cf = new ComplexFunction("plus", m1,m2);
		ComplexFunction cf3 = new ComplexFunction("Plus", p3,p2);
		cf.mul(m2);
		cf3.mul(m2);
		String s = cf.toString();
		function cf2 = cf.initFromString(s);
		if(!cf.equals(cf2)) 
		{
			fail("ERR: "+cf+" should be equals to "+cf2);
		}
	}

	@Test
	void testCopy() 
	{
		function a = new Polynom("2x");
		function b = new Polynom("x^2");
		ComplexFunction t1 = new ComplexFunction("Comp", a, b);
		ComplexFunction t2 = (ComplexFunction)t1.copy();
		if (!(t1.f(2)==t2.f(2))) 
			fail("ERR: testCopy");
		t1.plus(a);
		if (t1.f(2)==t2.f(2)) 
			fail("ERR: testInitFromString");
		
	}

}
