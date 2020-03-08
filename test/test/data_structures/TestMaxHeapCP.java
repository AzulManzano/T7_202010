package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxHeapCP;

public class TestMaxHeapCP 
{
	private MaxHeapCP<Integer> estructura;
	private int tamano;

	@Before
	public void setUp1()
	{
		tamano = 1000;
		Integer[] lista = new Integer[1000];
		for(int i = 0; i < 1000; i++)
		{
			lista[i] = i;
		}
		estructura = new MaxHeapCP<Integer>(lista);
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	@Test
	public void testDarNumElementos()
	{
		setUp1();

		assertEquals(estructura.esVacia(), false);
		assertEquals(estructura.darNumElementos(), tamano );

		for(int i =0; i<1000;i++)
		{
			estructura.agregar(i);
			tamano++;
		}
		assertEquals(estructura.darNumElementos(), tamano);
		
		for(int i = 0; i<1000; i++)
		{
			estructura.sacarMax();
			tamano--;
		}

		assertEquals(estructura.darNumElementos(),tamano);
	}

	@Test
	public void testAgregar()
	{
		setUp1();


		assertEquals(estructura.darNumElementos(), tamano );

		//	Agregar elementos ordenados
		for(int i = 0; i<1000;i++)
		{
			estructura.agregar(i);
			tamano++;
		}
		assertEquals(estructura.darNumElementos(), tamano);
		assertNotNull(estructura.sacarMax());
		assertNotNull(estructura.sacarMax());

		setUp1();

		//	Agregar elementos ordenados
		for(int i = 1; i<1000;i =i+2)
		{
			estructura.agregar(i);
			tamano++;
		}
		assertEquals(estructura.darNumElementos(), tamano);
		assertNotNull(estructura.sacarMax());
		assertNotNull(estructura.sacarMax());	
	}

	@Test
	public void testSacarMax()
	{
		setUp1();

		assertEquals(estructura.darNumElementos(), tamano );
		for(int i = 0; i<100; i++)
		{
			Integer un = estructura.sacarMax();
			assertEquals(un>=estructura.sacarMax(), true);
		}
	}

	@Test
	public void testDarMax()
	{
		setUp1();

		assertEquals(estructura.darNumElementos(), tamano );

		Integer valor = 1000;
		for(int i = 1000; i>0; i--)
		{
			estructura.agregar(i);
			assertEquals(estructura.darMax(), valor);
		}

		setUp1();

		assertEquals(estructura.darNumElementos(), tamano );

		Integer valor1 = 999;
		for(int i = 0; i<1000; i++)
		{
			estructura.agregar(i);
			assertEquals(estructura.darMax(), valor1);
		}


	}

	@Test
	public void testEsVasia()
	{
		setUp1();
		assertEquals(estructura.darNumElementos(), tamano );
		assertEquals(estructura.esVacia(), false);
		
		for(int i = 0; i<1000; i++)
		{
			estructura.sacarMax();
		}

		assertEquals(estructura.darNumElementos(),0);
		assertEquals(estructura.esVacia(), true);
	}



}
