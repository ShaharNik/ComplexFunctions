package Ex1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Functions_GUI implements functions
{
	ArrayList<function> G_Functions;
	public Functions_GUI()
	{
		
	}
	@Override
	public int size() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<function> iterator() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(function e) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends function> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFromFile(String file) throws IOException 
	{
		// TODO Auto-generated method stub
		G_Functions = new ArrayList<function>();
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null)
		{
			line = line.substring(line.indexOf("f(x)=")+"f(x)=".length());
			line = line.strip(); // Removes white spaces
			ComplexFunction cf = new ComplexFunction();
			G_Functions.add(cf.initFromString(line));
			line = reader.readLine();
		}
		reader.close();

	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		FileWriter fw = new FileWriter(file);
		Iterator<function> itrFunction = G_Functions.iterator();
		int counter = 0;
		while(itrFunction.hasNext())
		{
			function f = itrFunction.next();
			fw.write(counter+") "+"   f(x)= "+f.toString()+'\n');
			counter++;
		}
		fw.close();

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		JsonReader reader = null;
		try 
		{
			reader = new JsonReader(new FileReader(json_file));
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		G_Functions = gson.fromJson(reader, function.class); // contains the whole reviews list
		G_Functions.toString(); 
		
	}

}
