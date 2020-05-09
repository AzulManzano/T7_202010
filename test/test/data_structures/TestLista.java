package test.data_structures;

import static org.junit.Assert.assertEquals;

import model.data_structures.LinearProbing;
import model.data_structures.Lista;

import org.junit.Before;
import org.junit.Test;

public class TestLista 
{
	public Lista<String> estructura;

	@Before
	public void setUp1()
	{
		estructura = new Lista<String>();
	}
	
	@Test
	public void LinearProbingTest()
	{
		setUp1();
		assertEquals(estructura.isEmpty(), true);
		assertEquals(estructura.size(), 0);
	}
	
	@Test
	public void testSize()
	{
		setUp1();
		assertEquals(estructura.size(), 0);
		estructura.add("a");
		assertEquals(estructura.size(), 1);
		estructura.add("b");
		assertEquals(estructura.size(), 2);
	}
	
	@Test
	public void testIsEmpty()
	{
		setUp1();
		assertEquals(estructura.isEmpty(), true);
		estructura.add("a");
		assertEquals(estructura.isEmpty(), false);
	}
	
	@Test
	public void testContains()
	{
		setUp1();
		estructura.add("a");
		assertEquals(estructura.contains("a"), true);
		assertEquals(estructura.contains("b"), false);
		assertEquals(estructura.contains("c"), false);
		estructura.add("b");
		assertEquals(estructura.contains("b"), true);
	}
	
	@Test
	public void testAdd()
	{
		setUp1();
		for(int i = 0; i<100;i++)
		{
			estructura.add("a"+i);
		}
		assertEquals(estructura.size(), 100);
		estructura.add("a"+0);
		estructura.add("a"+1);
		estructura.add("a"+87);
		assertEquals(estructura.size(), 100);
	}
	
	@Test
	public void testGet()
	{
		setUp1();
		estructura.add("a");
		assertEquals(estructura.get("a"), "a");
		assertEquals(estructura.get("b"), null);
	}
	
}
