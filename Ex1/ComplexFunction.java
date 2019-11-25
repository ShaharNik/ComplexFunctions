package Ex1;

public class ComplexFunction implements complex_function
{
	private Operation _Op;
	private function _Left;
	private function _Right;
	
	public ComplexFunction()
	{
		//Empty ComplexFunction
	}
	public ComplexFunction(String Op, function left, function right)
	{
	    switch (Op)
	    {
	      case "Plus": this._Op = Operation.Plus;
	      case "Times": this._Op = Operation.Times;
	      case "Divid": this._Op = Operation.Divid;
	      case "Max": this._Op = Operation.Max;
	      case "Min": this._Op = Operation.Min; 
	      case "Comp": this._Op = Operation.Comp;
	      case "None": this._Op = Operation.None;
	      case "Error": this._Op = Operation.Error;
	      //default:    
	    }
		this._Left = left;
		this._Right = right;
	}

	
	
	@Override
	public double f(double x) 
	{
		// TODO Auto-generated method stub
		this._Left.f(x);
		this._Right.f(x);
		return 0; //which to return?
	}

	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) 
	{
		// TODO Auto-generated method stub
		if (f1 != null)
		{
			this._Right = f1;
			this._Op = Operation.Plus;
		}
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return this._Left; //should't be null
	}

	@Override
	public function right() {
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
