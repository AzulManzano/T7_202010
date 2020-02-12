package test.data_structures;


import model.data_structures.Stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStack 
{
	private Stack<String> pila;
	private Integer tamano;

	@Before
	public void setUp1()
	{
		tamano = 0; 
		pila = new Stack<String>();
	}

	@Test
	public void testStack()
	{
		assertEquals(pila.getSize(), tamano);
		assertEquals(pila.isEmpty(), true);
		assertEquals(pila.getElement(), null);
		assertEquals(pila.pop(), null);
	}

	@Test
	public void testPush()
	{
		setUp1();
		String primero = "Numero 1";

		pila.push(primero);
		tamano = 1;
		assertEquals(pila.getSize(), tamano);
		assertEquals(pila.getElement(), primero);

		for(int i = 0; i <1000; i++)
		{
			pila.push(i+"");
		}
		tamano = 1001;
		assertEquals(pila.getSize(), tamano);
		assertEquals(pila.getElement(),"999");
		assertEquals(pila.pop(), "999");
	}

	@Test
	public void testPop()
	{
		setUp1();
		assertEquals(pila.getSize(), tamano);
		assertEquals(pila.pop(), null);
		
		for(int i = 0; i < 1000; i++)
		{
			pila.push(i+"");
		}
		
		for(int i = 999; i>-1; i--)
		{
			assertEquals(pila.pop(), i+"");
		}
	}

	@Test
	public void testGetSize()
	{
		setUp1();
		assertEquals(pila.getSize(), tamano);
		
		for(int i = 0; i < 1000; i++)
		{
			pila.push(i+"");
			tamano ++;
		}

		assertEquals(pila.getSize(), tamano);
		
		for(int i = 0; i < 100001; i++)
		{
			pila.push(i+"");
			tamano ++;
		}
		assertEquals(pila.getSize(), tamano);
		
		for(int i = 0; i < 5000; i++)
		{
			pila.push(i+"");
			tamano ++;
		}
		assertEquals(pila.getSize(), tamano);	
	}

	@Test
	public void testIsEmpty()
	{
		setUp1();
		assertEquals(pila.isEmpty(), true);
		assertEquals(pila.getSize(), tamano);
		assertEquals(pila.getElement(), null);
	}

	@Test
	public void testGetElement()
	{
		setUp1();
		assertEquals(pila.getElement(), null);
		
		for(int i = 0; i < 1001; i++)
		{
			pila.push(i+"");
		}
		assertEquals(pila.getElement(), "1000");
		
		for(int i = 0; i < 100001; i++)
		{
			pila.push(i+"");
		}
		assertEquals(pila.getElement(), "100000");
		
		for(int i = 0; i < 5000; i++)
		{
			pila.pop();
		}
		assertEquals(pila.getElement(), "95000");
	}

}
