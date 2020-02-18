package test.data_structures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ListaEncadenada;

public class TestListaEncadenada 
{
	private ListaEncadenada<String> lista;
	private Integer tamano;

	@Before
	public void setUp1()
	{
		lista = new ListaEncadenada<String>();
		tamano = 0; 
	}

	@Test
	public void TestGetSize()
	{
		assertEquals(lista.getSize(), tamano);
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
	}

	@Test
	public void TestAdd() throws Exception
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
		assertEquals(lista.getElement(0), "99");
		
		setUp1();
		for(int i = 0; i<50; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
		assertEquals(lista.getElement(0), "49");
	}

	@Test
	public void TestaddAtEnd() throws Exception
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.addAtEnd(i+"");;
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
		assertEquals(lista.getElement(0), "0");
		
		for(int i = 0; i<50; i++)
		{
			lista.addAtEnd(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
		assertEquals(lista.getElement(100), "0");
	}

	@Test
	public void TestaddAtK() throws Exception 
	{
		setUp1();
		lista.add("0");
		tamano++;
		for(int i = 0; i<100; i++)
		{
			lista.addAtK(i+"", 0);
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
		assertEquals(lista.getElement(0), "99");
		
		for(int i = 0; i<50; i++)
		{
			lista.addAtK(i+"", 0);
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
	} 

	@Test
	public void TestgetElement() throws Exception
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getElement(0), "99");
		assertEquals(lista.getElement(99), "0");
		assertEquals(lista.getElement(50), "49");
		
	}

	@Test
	public void TestinCero()
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getCurrentElement(), "99");
		
		for(int i = 0; i<50; i++)
		{
			lista.next();
		}
		lista.inCero();
		assertEquals(lista.getCurrentElement(), "99");
	}

	@Test
	public void TestgetCurrentElement()
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getCurrentElement(), "99");
		lista.next();
		assertEquals(lista.getCurrentElement(), "98");	
		lista.inCero();
		assertEquals(lista.getCurrentElement(), "99");
	}

	@Test
	public void Testnext()
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getCurrentElement(), "99");
		
		for(int i = 98; i>10; i--)
		{
			lista.next();
			assertEquals(lista.getCurrentElement(), i+"");
		}

	}

	@Test
	public void Testdelete()
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
	}

	@Test
	public void TestdeleteAtK()
	{
		setUp1();
		for(int i = 0; i<100; i++)
		{
			lista.add(i+"");
			tamano ++;
		}
		assertEquals(lista.getSize(), tamano);
	}
}
