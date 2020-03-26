package test.data_structures;

import static org.junit.Assert.assertEquals;

import model.data_structures.Queue;

import org.junit.Before;
import org.junit.Test;


public class TestQueue 
{
	private Queue<String> cola;
	private String elemento1;
	private String elemento2;
	private String elemento3;
	private Integer tamaño;

	@Before
	public void setUp1()
	{
		tamaño = 0;
		elemento1 = "Numero 1";
		elemento2 = "Nuemro 2";
		elemento3 = "Numero 3";

		cola = new Queue<String>();
	}

	@Test
	public void QueueTest()
	{
		assertEquals( "El tamaño de la cola no es correcto.", cola.getSize() , tamaño);
		assertEquals("No deberia estar vacia", cola.isEmpty(), true );
		assertEquals("Ese no es el primer elemeto", cola.getElement(), null);
	}

	@Test
	public void testeEnqueue()
	{
		setUp1();

		cola.enqueue(elemento2);
		tamaño = 1;
		assertEquals(cola.getSize(), tamaño);

		cola.enqueue(elemento3);
		tamaño = 2;
		assertEquals(cola.getSize(), tamaño);
		
		setUp1();
		
		cola.enqueue(elemento1);
		tamaño = 1;
		assertEquals(cola.getSize(), tamaño);
		
	}

	@Test
	public void testDequeue()
	{
		setUp1();
		tamaño = 0;

		assertEquals( "El tamaño de la cola no es correcto.", cola.getSize() , tamaño);

		cola.enqueue(elemento1);
		cola.enqueue(elemento2);
		cola.enqueue(elemento3);
		tamaño = 3;

		assertEquals( "El tamaño de la cola no es correcto.", cola.getSize() , tamaño);

		String momento = cola.dequeue();
		assertEquals(momento, elemento1);

		momento = cola.dequeue();
		assertEquals(momento, elemento2);

		momento = cola.dequeue();
		assertEquals(momento, elemento3);

		tamaño = 0;
		assertEquals(cola.isEmpty(), true);
		assertEquals(cola.getSize(), tamaño);	
	}

	@Test
	public void testGetSize()
	{
		setUp1();

		tamaño = 0;
		assertEquals(cola.getSize(), tamaño);

		cola.enqueue(elemento2);
		tamaño = 1;
		assertEquals(cola.getSize(), tamaño);

		cola.enqueue(elemento3);
		tamaño = 2;
		assertEquals(cola.getSize(), tamaño);

		for(int i = 0; i<1000; i++)
		{
			cola.enqueue(i+"");
		}
		tamaño = 2 + 1000;
		assertEquals(cola.getSize(), tamaño);		
	}

	@Test
	public void testIsEmpty()
	{
		setUp1();

		assertEquals(cola.getSize(), tamaño);	
		assertEquals(cola.isEmpty(), true);	
	}

	@Test
	public void testGetElement()
	{
		setUp1();
		
		assertEquals(cola.getElement(), null);
		assertEquals(cola.isEmpty(), true);	
		
		for(int i = 0; i<1000; i++)
		{
			cola.enqueue(i+"");
		}
		assertEquals(cola.getElement(), "0");
		
		for(int i = 0; i<500; i++)
		{
			cola.dequeue();
		}
		assertEquals(cola.getElement(), "500");
	}

}
