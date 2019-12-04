package Ex1;

public class ComplexFunction implements complex_function
{
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
		switch (Op)
		{
		case "Plus": this._Op = Operation.Plus;
		break;
		case "Times": this._Op = Operation.Times;
		break;
		case "Divid": this._Op = Operation.Divid;
		break;
		case "Max": this._Op = Operation.Max;
		break;
		case "Min": this._Op = Operation.Min; 
		break;
		case "Comp": this._Op = Operation.Comp;
		break;
		case "None": this._Op = Operation.None;
		break;
		default: this._Op = Operation.Error;
		break;
		}
		if (this._Left != null)
			this._Left = left;
		if (this._Right != null)
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
		default: throw new RuntimeException("The Operation is not vaild");
		}
	}

	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		s = s.strip(); // remove spaces
		//s=clearSpaces(s);
		int i=0;
		if (s.indexOf('(') == -1 && s.indexOf(')') == -1) { 
			Polynom po = new Polynom (s);
			function fun= new ComplexFunction(po);
			return fun;
		}
		else 
		{
			while (s.charAt(i) != '(') 
			{
				i++;
			}
			int split=splitPoint(s, i+1);
			String s1=s.substring(i+1, split);
			function left = initFromString(s1);
			String s2=s.substring(split+1, s.length()-1);
			function right = initFromString(s2);
			String s3 = s.substring(0, i);
			s3.toLowerCase();
			String s4="";
			switch(s3) 
			{

			case "plus"	: s4="Plus"; break;

			case "mul"	: s4="Times"; break;

			case "div"	: s4="Divid"; break;

			case "max" 	: s4="Max"; break;

			case "min"	: s4="Min"; break;

			case "comp"	: s4="Comp"; break;

			default: s4="None"; break;

			}
			function fun= new ComplexFunction(s4, left, right);
			return fun;
		}
	}
	/**
	 * 
	 * @param s - string that represents a complex function
	 * @param i - location after "("
	 * @return location of the split (left and right)
	 */
	private int splitPoint (String s , int i) 
	{
		int comma=0;
		int opener=1;
		int closer=0;
		int SplitAt=0;
		while(i != s.length()) 
		{
			if(s.charAt(i)=='(') 
				opener++;

			else if(s.charAt(i)==',') 
				comma++;

			else if(s.charAt(i)==')') 
				closer++;

			if(comma==opener && s.charAt(i) == ',') 
			{
				SplitAt=i;
				return SplitAt;
			}
			i++;
		}		
		return SplitAt;
	}

	@Override
	public function copy() 
	{
		// TODO Auto-generated method stub
		function ans = new ComplexFunction(_Op.toString(),_Left.copy(),_Right.copy());
		return ans;
	}
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof ComplexFunction)
		{
			ComplexFunction f = (ComplexFunction)other;
			boolean check=false;
			if(this._Op.compareTo(f._Op) == 0) // check if the enums are equal
				check = true;
			return this._Left.equals(f._Left) && this._Right.equals(f._Right) && check ;
		}
		if(other instanceof function)
			return this._Left.equals(other);
		return false;
	}
	@Override
	public void plus(function f1) 
	{
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left.copy(),this._Right.copy());
			this._Left = f;
		}

		this._Right = f1.copy();
		this._Op = Operation.Plus;
	}

	@Override
	public void mul(function f1) 
	{
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left,this._Right);
			this._Left = f.copy();
		}
		this._Right = f1.copy();
		this._Op = Operation.Times;

	}

	@Override
	public void div(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left,this._Right);
			this._Left = f.copy();
		}
		this._Right = f1.copy();
		this._Op = Operation.Divid;
	}

	@Override
	public void max(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left,this._Right);
			this._Left = f.copy();
		}
		this._Right = f1.copy();
		this._Op = Operation.Max;
	}

	@Override
	public void min(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left,this._Right);
			this._Left = f.copy();
		}
		this._Right = f1.copy();
		this._Op = Operation.Min;
	}

	@Override
	public void comp(function f1) 
	{
		// TODO Auto-generated method stub
		if(this._Right != null)
		{
			function f = new ComplexFunction(_Op.toString(),this._Left,this._Right);
			this._Left = f.copy();
		}
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
		StringBuilder sb = new StringBuilder();
		sb.append(this._Op.toString() +"("+this._Left.toString()+","+this._Right.toString()+")");
		return sb.toString();
		/*
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
		 */
	}

}
