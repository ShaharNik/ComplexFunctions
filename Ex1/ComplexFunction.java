package Ex1;


public class ComplexFunction implements complex_function
{
	public static final double EPS = 0.00001;
	
	private Operation _Op;
	private function _Left;
	private function _Right;
	

	public ComplexFunction()
	{
		this._Left= null;
		this._Right = null;
		this._Op = Operation.None;
	}
	public ComplexFunction(String Op, function left, function right)
	{
		switch (Op.toLowerCase())
		{
		case "plus": this._Op = Operation.Plus;
		break;
		case "times": this._Op = Operation.Times;
		break;
		case "mul": this._Op = Operation.Times;
		break;
		case "div": this._Op = Operation.Divid;
		break;
		case "divid": this._Op = Operation.Divid;
		break;
		case "max": this._Op = Operation.Max;
		break;
		case "min": this._Op = Operation.Min; 
		break;
		case "comp": this._Op = Operation.Comp;
		break;
		case "none": this._Op = Operation.None;
		break;
		default: this._Op = Operation.Error;
		break;
		}
		if (left != null)
			this._Left = left;
		else
			throw new RuntimeException("The Left can't be null");
		this._Right = right;
	}

	public ComplexFunction(function left) 
	{
		// TODO Auto-generated constructor stub
		this._Left = left;
		this._Op = Operation.None;
	}
	@Override
	public double f(double x) 
	{
		// TODO Auto-generated method stub
		switch(_Op.toString())
		{
		case "Plus":  return _Left.f(x)+_Right.f(x);
		case "Times" :  return _Left.f(x)*_Right.f(x);
		case "Divid" : if(_Right.f(x)!=0)
			return _Left.f(x)/_Right.f(x);
		else
			throw new RuntimeException("Can't divide by 0");
		case "Max" : if(_Left.f(x)>_Right.f(x))
			return _Left.f(x);
		else
			return _Right.f(x);
		case "Min" : if(_Left.f(x)>_Right.f(x))
			return _Right.f(x);
		else
			return _Left.f(x);
		case "Comp" : 
			if(_Right != null) 
				return _Left.f(_Right.f(x));
			else
				return _Left.f(x);
		case "None" :	
			return _Left.f(x);
			//default: throw new RuntimeException("The Operation is not vaild");
		default: return _Left.f(x);
		}
	}

	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		if(s.indexOf("(") == -1 && s.indexOf(")") == -1)
		{
			Polynom p = new Polynom();
			return p.initFromString(s);
		}
		int IndexFirstBracket = s.indexOf("(");
		int IndexComma = splitPoint(s,IndexFirstBracket);
		String op = s.substring(0, IndexFirstBracket);
		// left function
		function left = initFromString(s.substring(IndexFirstBracket+1,IndexComma));
		// right function
		function right = initFromString(s.substring(IndexComma+1,s.length()-1));
		// op is low case
		function ans = new ComplexFunction(op, left, right);
		return ans;

	}
	/**
	 * 
	 * @param s - string that represents a complex function
	 * @param indexOfFirstBracket - location after "("
	 * @return location of the split (left and right functions)
	 */
	private int splitPoint(String s , int indexOfFirstBracket) 
	{
		int comma=0;
		int opener=1;
		int SplitAt= indexOfFirstBracket + 1;
		while(opener != comma && SplitAt < s.length()) 
		{
			if(s.charAt(SplitAt)=='(') 
				opener++;

			else if(s.charAt(SplitAt)==',') 
				comma++;

			SplitAt++;
		}		
		return SplitAt - 1;
	}

	@Override
	public function copy() 
	{
		// TODO Auto-generated method stub
		function ans = new ComplexFunction(OP_inUpper(),_Left.copy(),_Right.copy());
		return ans;
	}
	@Override
	public boolean equals(Object other)
	{
		/*
		if(other instanceof ComplexFunction)
		{
			//double deBug;
			ComplexFunction otherCF = (ComplexFunction)other;
			for (double x = -150; x <= 150; x+=0.1)
			{
				//deBug = Math.abs(this.f(x)-otherCF.f(x));
				if(Math.abs(this.f(x)-otherCF.f(x)) > EPS)
				{
					return false;
				}
			}
			return true;
			 
		}
		*/
		if(other instanceof function)
		{
			// compare the functions values running 0.1 steps
			for (double step = -150; step <= 150; step+=0.1)
			{
				if(Math.abs(this.f(step)-((function)other).f(step)) > EPS)
				{
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public void plus(function f1) 
	{
		//f1 = f1.copy();
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f;
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");

		this._Right = f1.copy();
		this._Op = Operation.Plus;
	}

	@Override
	public void mul(function f1) 
	{
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f.copy();
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");
		this._Right = f1.copy();
		this._Op = Operation.Times;

	}

	@Override
	public void div(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f.copy();
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");
		this._Right = f1.copy();
		this._Op = Operation.Divid;
	}

	@Override
	public void max(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f.copy();
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");
		this._Right = f1.copy();
		this._Op = Operation.Max;
	}

	@Override
	public void min(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f.copy();
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");
		this._Right = f1.copy();
		this._Op = Operation.Min;
	}

	@Override
	public void comp(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(OP_inUpper(),this._Left.copy(), this._Right.copy());
			this._Left = f.copy();
		}
		else
			throw new RuntimeException("Right Function is Null, can't execute operation.");
		this._Right = f1.copy();
		this._Op = Operation.Comp;
	}

	@Override
	public function left() 
	{
		// TODO Auto-generated method stub
		return this._Left; //should't be null
	}

	@Override
	public function right() 
	{
		// TODO Auto-generated method stub
		return this._Right; //can be null
	}

	@Override
	public Operation getOp() 
	{
		// TODO Auto-generated method stub
		return this._Op;
	}
	@Override
	/**
	 * @return string that represent this complex function.
	 */
	public String toString() 
	{
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(this._Op.toString() +"("+this._Left.toString()+","+this._Right.toString()+")");
		return sb.toString();
		 */

		String ans="";
		String op ="";
		if (this._Op != Operation.None) 
		{
			if (this._Op == Operation.Plus) 
				ans+="plus";
			else if (this._Op == Operation.Times) 
				ans+="mul";
			else if (this._Op == Operation.Divid) 
				ans+="div";
			else if (this._Op == Operation.Max) 
				ans+="max";
			else if (this._Op == Operation.Min) 
				ans+="min";
			else if (this._Op == Operation.Comp) 
				ans+="comp";

			ans+="(";
		}
		if(this._Left!=null) 
			ans+=this._Left;	
		if(this._Right!=null) 
		{
			ans+=",";
			ans+=this._Right;
			ans+=")";
		}
		return ans;

	}
	private String OP_inUpper()
	{
		switch(this._Op.toString())
		{
		case "Plus": return "plus";
		case "Times" : return "mul";
		case "Divid" : return "div";
		case "Max" : return "max";
		case "Min" : return "min";
		case "Comp" :return "comp";
		default: return "ERROR";
		}
	}

}
