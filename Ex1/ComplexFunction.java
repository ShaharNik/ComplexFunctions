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
			case "Error": this._Op = Operation.Error;
			//default:    
		}
		this._Left = left;
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
			case "Divid" : return _Left.f(x)/_Right.f(x);
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
		return null;
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
			{
				check = true;
			}
			return this._Left.equals(f._Left) && this._Right.equals(f._Right) && check ;
		}
		if(other instanceof function)
		{
			return this._Left.equals(other);
		}
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

}
