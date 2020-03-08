package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import model.data_structures.MaxColaCP;

public class TestMaxColaCP 
{
	private MaxColaCP<Integer> estructura;
	private int tamano;

	@Before
	public void setUp1()
	{
		tamano = 0;
		estructura = new MaxColaCP<Integer>();
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	@Test
	public void testDarNumElementos()
	{
		setUp1();

		assertEquals(estructura.esVacia(), true);
		assertEquals(estructura.darNumElementos(), tamano );

		for(int i =0; i<1000;i++)
		{
			estructura.agregar(i);
			tamano++;
		}
		assertEquals(estructura.darNumElementos(), tamano);
		assertEquals(estructura.darNumElementos(), 1000);
	}

	@Test
	public void testAgregar()
	{
		setUp1();

		assertEquals(estructura.esVacia(), true);
		assertEquals(estructura.darNumElementos(), tamano );

		//	Agregar elementos ordenados
		for(int i = 0; i<1000;i++)
		{
			estructura.agregar(i);
			tamano++;
		}
		assertEquals(estructura.darNumElementos(), tamano);
		assertEquals(estructura.darNumElementos(), 1000);
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
		assertEquals(estructura.darNumElementos(), 500);
		assertNotNull(estructura.sacarMax());
		assertNotNull(estructura.sacarMax());
		
		setUp1();

		//	Agregar elementos ordenados inversamente
		for(int i = 1000; i>0;i--)
		{
			estructura.agregar(i);
			tamano++;
		}
		Integer num = 999;
		assertEquals(estructura.darNumElementos(), tamano);
		assertEquals(estructura.darNumElementos(), 1000);
		assertEquals(estructura.darMax(), estructura.sacarMax());
		assertEquals(estructura.darMax(), num);
		assertNotNull(estructura.sacarMax());
		assertNotNull(estructura.sacarMax());
		setUp1();

		//	Agregar elementos repetidos
		for(int i = 1000; i>0;i--)
		{
			estructura.agregar(4);
			tamano++;
		}
	
		assertEquals(estructura.darNumElementos(), tamano);		
		for(int i = 500; i>0;i--)
		{
			assertNotNull(estructura.sacarMax());
		}
	
		setUp1();
		//	Agregar elementos intermedios repetidos
		estructura.agregar(-1);
		tamano++;
		estructura.agregar(1);
		tamano++;
		for(int i =0; i<100;i++)
		{
			estructura.agregar(0);
			tamano++;
		}
		Integer valor = 1;
		Integer valor1 = 0;
		
		assertEquals(estructura.darNumElementos(), tamano);
		assertEquals(estructura.sacarMax(), valor);
		for(int i =0; i<100;i++)
		{
			assertEquals(estructura.sacarMax(), valor1);
		}
		
		setUp1();
		//	Agregar elementos intermedios repetidos
		for(int i = 0; i<50; i++)
		{
			estructura.agregar(i);
			tamano++;
			for(int j = 0; j<100; j++)
			{
				estructura.agregar(j);
				tamano++;
			}
		}
		assertEquals(estructura.darNumElementos(), tamano);

		for(int i = 0; i<100; i++)
		{
			Integer un = estructura.sacarMax();
			assertEquals(un>=estructura.sacarMax(), true);
		}
	}

	@Test
	public void testSacarMax()
	{
		setUp1();

		assertEquals(estructura.esVacia(), true);
		assertEquals(estructura.darNumElementos(), tamano );
		
		for(int i = 0; i<50; i++)
		{
			estructura.agregar(i);
			tamano++;
			for(int j = 0; j<100; j++)
			{
				estructura.agregar(j);
				tamano++;
			}
		}
		assertEquals(estructura.darNumElementos(), tamano);

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

		assertEquals(estructura.esVacia(), true);
		assertEquals(estructura.darNumElementos(), tamano );
		
		Integer valor = 1000;
		for(int i = 1000; i>0; i--)
		{
			estructura.agregar(i);
			assertEquals(estructura.darMax(), valor);
		}
		
		setUp1();
		
		assertEquals(estructura.esVacia(), true);
		assertEquals(estructura.darNumElementos(), tamano );
		
		Integer valor1 = 0;
		for(int i = 0; i<1000; i++)
		{
			estructura.agregar(i);
			assertEquals(estructura.darMax(), valor1);
			valor1++;
		}


	}

	@Test
	public void testEsVasia()
	{
		setUp1();

		assertEquals(estructura.darNumElementos(), tamano );
		assertEquals(estructura.darNumElementos(), 0 );
		assertEquals(estructura.esVacia(), true);

		estructura.agregar(1);
		tamano ++;
		assertEquals(estructura.darNumElementos(),1);
		assertEquals(estructura.esVacia(), false);
	}

}
