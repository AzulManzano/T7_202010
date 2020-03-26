package test.data_structures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.SeparateChaining;

public class TestSeparateChaining 
{
 public SeparateChaining<String,String> tabla;
 
 public void setUp1()
	{
		tabla = new SeparateChaining<String,String>();
	}
	
	@Before
	public void setUp2()
	{
		tabla = new SeparateChaining<String,String>();
		tabla.put("a", "a");
		tabla.put("b", "a");
		tabla.put("c", "a");
		tabla.put("d", "a");
	}
	
	@Test
	public void LinearProbingTest()
	{
		setUp1();
		assertEquals(tabla.isEmpty(), true);
		assertEquals(tabla.size(), 0);
		assertEquals(tabla.darCapacidad(), 5);
	}
	
	@Test
	public void testPut()
	{
		setUp1();
		tabla.put("a", "a");
		tabla.put("b", "a");
		
		assertEquals(tabla.isEmpty(), false);
		assertEquals(tabla.size(), 2);
		assertEquals(tabla.darCapacidad(), 5);
		
		tabla.put("c", "a");
		tabla.put("d", "a");
		tabla.put("a", "b");
		assertEquals(tabla.size(), 4);
		assertEquals(tabla.darCapacidad(), 5);
		
		tabla.put("azul", "b");
		assertEquals(tabla.size(), 5);
	}
	
	@Test
	public void testGet()
	{
		setUp2();
		assertEquals(tabla.size(), 4);
		assertEquals(tabla.get("a"), "a");
		assertEquals(tabla.get("b"), "a");
		assertEquals(tabla.get("c"), "a");
		assertEquals(tabla.get("d"), "a");
		assertEquals(tabla.get("z"), null);
	}
	
	@Test
	public void testDelete()
	{
		setUp2();
		assertEquals(tabla.isEmpty(), false);
		assertEquals(tabla.delete("a"), "a");
		assertEquals(tabla.delete("b"), "a");
		assertEquals(tabla.delete("c"), "a");
		assertEquals(tabla.delete("d"), "a");
		assertEquals(tabla.delete("a"), null);
		assertEquals(tabla.size(), 0);
		assertEquals(tabla.isEmpty(), true);
	}
	
	@Test
	public void testSize()
	{
		setUp2();
		assertEquals(tabla.size(), 4);
		assertEquals(tabla.delete("a"), "a");
		assertEquals(tabla.size(), 3);
		assertEquals(tabla.delete("b"), "a");
		assertEquals(tabla.size(), 2);
		assertEquals(tabla.delete("c"), "a");
		assertEquals(tabla.size(), 1);
		assertEquals(tabla.delete("d"), "a");
		assertEquals(tabla.size(), 0);
	}
	
	@Test
	public void testIsEmpty()
	{
		setUp2();
		assertEquals(tabla.isEmpty(), false);
		assertEquals(tabla.delete("a"), "a");
		assertEquals(tabla.delete("b"), "a");
		assertEquals(tabla.delete("c"), "a");
		assertEquals(tabla.delete("d"), "a");
		assertEquals(tabla.size(), 0);
		assertEquals(tabla.isEmpty(), true);
	}
	
	@Test
	public void testContains()
	{
		setUp2();
		assertEquals(tabla.size(), 4);
		assertEquals(tabla.contains("a"), true);
		assertEquals(tabla.contains("b"), true);
		assertEquals(tabla.contains("c"), true);
		assertEquals(tabla.contains("d"), true);
		assertEquals(tabla.contains("z"), false);
		assertEquals(tabla.contains("f"), false);
		assertEquals(tabla.contains("t"), false);
		assertEquals(tabla.contains("k"), false);
	}
	
	@Test
	public void testResize()
	{
		setUp2();
		assertEquals(tabla.size(), 4);
		assertEquals(tabla.darCapacidad(), 5);
		tabla.put("a1", "a");
		assertEquals(tabla.darCapacidad(), 5);
		tabla.put("a11", "a");
		tabla.put("a12", "a");
		tabla.put("a13", "a");
		tabla.put("a14", "a");
		tabla.put("a14", "a");
		assertEquals(tabla.darCapacidad(), 5);
	}
}
