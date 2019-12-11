package Ex1;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;


import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;


class Functions_GUITest {

	public static void main(String[] a) {
		functions data = FunctionsFactory();
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);
	}
	private functions _data=null;


	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	@Test
	void testInitFromFileAndSave() throws Exception {
		_data.saveToFile("test.txt");
		Functions_GUI fromFile = new Functions_GUI();
		fromFile.initFromFile("test.txt");
		Iterator<function> itr = _data.iterator();
		while(itr.hasNext())
		{
			function f = itr.next();
			if(!fromFile.contains(f))
			{
				fail("Problem with reading file one or more function were not found");
			}
		}


	}

	@Test
	void testDrawFunctionsJSON() {
		_data.drawFunctions("GUI_params.txt");

	}
	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "4.6 +7.7x^4 -3x^4";
		String s2 = "25 +22.3x -3.3x +0.1x^5";
		String[] s3 = {"2x^5 +3x","x -5", "4x^2 -12"};
		Polynom pol1 = new Polynom(s1);
		Polynom pol2 = new Polynom(s2);
		Polynom pol3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(pol3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction("Plus", pol1,pol2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(pol1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}

	@Test
	void testDrawFun()
	{
		Functions_GUI d = new Functions_GUI();
		try {
			d.initFromFile("test_Gui.txt");
			d.drawFunctions("GUI_params.txt");
		}
		catch (Exception e) {

		}
	}

	@Test
	void testDrawfunction_2() 
	{
		int w=3000, h=60, res=300;
		Range rx = new Range(-7,8);
		Range ry = new Range(-2,10);
		_data.drawFunctions(w,h,rx,ry,res);

	}

	@Test
	void testDrawFunctions() 
	{
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		_data.drawFunctions(w,h,rx,ry,res);
	}


	@Test
	void testInitFromFile() {                          // Strings array test

		function t = new ComplexFunction();
		String [] anticipated = {"mul(5.0x^3+2.4x^2+3.1,0.4x^2-1.2x+23.0)" , 
				"plus(min(x+4,div(mul(3x+8.0,x-2.0),4x-9.0)),3.0)" ,
				"mul(min(-8.0x^4+2.0x^12+34,0.1x^5-2x+5.0),-1.0x^4+2.4x^2+42.12)" , 
				"-1.0x^4+2.4x^2+3.1" ,"0.1x^5-7x^3+5.0" , 
				"plus(plus(max(max(plus(-1.0x^4+2.4x^2+3.1,0.1x^5+87x+5.0),"
						+ "plus(div(x+1,mul(mul(x+3.0,x-2.0),x-4.0)),2.0)),"
						+ "div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5+5.0),-1.0x^4+2.4x^2+3.1))"
						+ ",-1.0x^4+2.4x^2+3.1),0.1x^5-65x+5.0)" ,
						"min(div(min(mul(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-9.0+52.0),"
								+ "plus(div(x+1,mul(mul(x+3.0,x-2.0),x-4.0)),3.5)),"
								+ "div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-90.0+5.0),-1.0x^4+2.4x^2+7.7)),"
								+ "-1.0x^4+2.4x^2+3.1),3.1x^5+54x^6+5.0)" };


		Functions_GUI anticipated_G = new Functions_GUI();
		for (int i=0; i<anticipated.length; i++)
		{
			anticipated_G.add(t.initFromString(anticipated[i]));
		}

		try 
		{
			_data.initFromFile("s.txt");      // need improvement
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}

		Iterator<function> i = _data.iterator();
		Iterator<function> k = anticipated_G.iterator();

		while (i.hasNext() && k.hasNext())
		{ 
			i.next(); k.next();
		}
	}



	@Test
	void testSaveToFile()
	{
		try
		{
			_data.saveToFile("gui_test.txt");
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}

	}


	@Test   //check
	void testNoFXReadUsingDefaultJSONparams()
	{
		try
		{
			Functions_GUI fromFile = new Functions_GUI();
			fromFile.initFromFile("test.txt");
			fromFile.drawFunctions("GUI_params");
		}
		catch (Exception e) {
			e.printStackTrace();
		}



	}

}

