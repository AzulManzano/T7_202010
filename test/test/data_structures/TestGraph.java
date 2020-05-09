package test.data_structures;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.Graph;
import model.data_structures.Queue;

public class TestGraph 
{

	public Graph<Integer, String> estructura;

	@Before
	public void setUp1()
	{
		estructura = new Graph<Integer, String>();
	}

	@Before
	public void setUp2()
	{
		estructura = new Graph<Integer, String>();

		for(int i= 0; i<100;i++)
		{
			estructura.addVertex(i, "a"+i);
		}

		for(int j = 0; j<50; j++)
		{
			estructura.addEdge(j, j+1, j*100);
		}
	}

	@Test
	public void testV()
	{
		setUp1();
		assertEquals(estructura.V(),0);

		setUp2();
		assertEquals(estructura.V(),100);
	}

	@Test
	public void testE()
	{
		setUp1();
		assertEquals(estructura.E(),0);

		setUp2();
		assertEquals(estructura.E(),50);
	}

	@Test
	public void testaddEdge()
	{
		setUp1();
		estructura.addEdge(1, 2, 1);
		assertEquals(estructura.E(),0);
		assertEquals(estructura.V(),0);

		setUp2();
		estructura.addEdge(1, 50, 10);
		estructura.addEdge(1, 51, 101);
		estructura.addEdge(1, 52, 1001);

		assertEquals(estructura.E(),53);
		assertEquals(estructura.V(),100);

		estructura.addEdge(1, 50, 10);
		estructura.addEdge(1, 51, 101);
		estructura.addEdge(1, 52, 1001);

		assertEquals(estructura.E(),53);
		assertEquals(estructura.V(),100);
	}

	@Test
	public void testgetInfoVertex()
	{
		setUp1();
		assertEquals(estructura.getInfoVertex(1),null);

		setUp2();
		estructura.addVertex(100, "a");
		estructura.addVertex(1000, "b");
		estructura.addVertex(10000, "c");

		assertEquals(estructura.getInfoVertex(100),"a");
		assertEquals(estructura.getInfoVertex(1000),"b");
		assertEquals(estructura.getInfoVertex(10000),"c");
	}

	@Test
	public void testsetInfoVertex()
	{
		setUp1();
		estructura.setInfoVertex(1,"a");
		assertEquals(estructura.E(),0);
		assertEquals(estructura.V(),0);

		setUp2();
		estructura.addVertex(100, "a");
		estructura.addVertex(1000, "b");
		estructura.addVertex(10000, "c");

		estructura.setInfoVertex(100,"aa");
		estructura.setInfoVertex(1000,"bb");
		estructura.setInfoVertex(10000,"cc");

		assertEquals(estructura.getInfoVertex(100),"aa");
		assertEquals(estructura.getInfoVertex(1000),"bb");
		assertEquals(estructura.getInfoVertex(10000),"cc");
	}

	@Test
	public void testgetCostArc()
	{
		setUp1();
		int valor1 = 0;
		int valor2 = 0;

		valor1 = (int) estructura.getCostArc(1, 2);
		valor2= -1;
		assertEquals(valor1,valor2);

		setUp2();
		estructura.addEdge(1, 50, 10);
		estructura.addEdge(1, 51, 101);
		estructura.addEdge(1, 52, 1001);

		assertEquals(estructura.E(),53);
		assertEquals(estructura.V(),100);

		valor1 = (int) estructura.getCostArc(1, 50);
		valor2= 10;
		assertEquals(valor1,valor2);

		valor1 = (int) estructura.getCostArc(1, 51);
		valor2= 101;
		assertEquals(valor1,valor2);

		valor1 = (int) estructura.getCostArc(1, 52);
		valor2= 1001;
		assertEquals(valor1,valor2);
	}

	@Test
	public void testsetCostArc()
	{
		setUp1();
		int valor1 = 0;
		int valor2 = 0;
		estructura.setCostArc(1, 50, 5);
		assertEquals(estructura.E(),0);
		assertEquals(estructura.V(),0);

		setUp2();
		estructura.addEdge(1, 50, 10);
		estructura.addEdge(1, 51, 101);
		estructura.addEdge(1, 52, 1001);

		assertEquals(estructura.E(),53);
		assertEquals(estructura.V(),100);

		estructura.setCostArc(1, 50, 5);
		estructura.setCostArc(1, 51, 50);
		estructura.setCostArc(1, 52, 500);

		valor1 = (int) estructura.getCostArc(1, 50);
		valor2= 5;
		assertEquals(valor1,valor2);

		valor1 = (int) estructura.getCostArc(1, 51);
		valor2= 50;
		assertEquals(valor1,valor2);

		valor1 = (int) estructura.getCostArc(1, 52);
		valor2= 500;
		assertEquals(valor1,valor2);
	}

	@Test
	public void testaddVertex()
	{
		setUp1();
		estructura.addVertex(100, "a");
		estructura.addVertex(1000, "b");
		estructura.addVertex(10000, "c");

		assertEquals(estructura.E(),0);
		assertEquals(estructura.V(),3);

		setUp2();
		estructura.addVertex(100, "a");
		estructura.addVertex(1000, "b");
		estructura.addVertex(10000, "c");

		assertEquals(estructura.getInfoVertex(100),"a");
		assertEquals(estructura.getInfoVertex(1000),"b");
		assertEquals(estructura.getInfoVertex(10000),"c");

		assertEquals(estructura.E(),50);
		assertEquals(estructura.V(),103);
	}

	@Test
	public void testuncheck()
	{
		setUp2();	
		estructura.cc();
		Queue<Integer> llaves1 = estructura.vectores();

		while(llaves1.isEmpty() == false)
		{
			assertEquals(estructura.getVertex(llaves1.dequeue()).darMarca(),true);
		}
		estructura.uncheck();

		Queue<Integer> llaves = estructura.vectores();

		while(llaves.isEmpty() == false)
		{
			assertEquals(estructura.getVertex(llaves.dequeue()).darMarca(),false);
		}
	}

	@Test
	public void testadj()
	{
		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		estructura.addEdge(0, 1, 10);
		estructura.addEdge(0, 2, 10);
		estructura.addEdge(0, 3, 10);

		estructura.addEdge(1, 0, 10);
		estructura.addEdge(1, 2, 10);
		estructura.addEdge(1, 3, 10);

		assertEquals(estructura.E(),5);

		Queue<Integer> lista = estructura.adj(0);
		int tam =lista.getSize();
		assertEquals(tam,3);

		Queue<Integer> lista1 = estructura.adj(0);
		int tam1 =lista1.getSize();
		assertEquals(tam1,3);
	}

	@Test
	public void testdfs()
	{
		setUp2();	
		Queue<Integer> llaves1 = estructura.vectores();
		Queue<Integer> llaves2 = estructura.vectores();
		while(llaves2.isEmpty() == false)
		{
			estructura.dfs(llaves2.dequeue());
		}
		
		while(llaves1.isEmpty() == false)
		{
			assertEquals(estructura.getVertex(llaves1.dequeue()).darMarca(),true);
		}
		
		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		estructura.addEdge(0, 1, 10);
		estructura.addEdge(0, 2, 10);
		estructura.addEdge(0, 3, 10);

		estructura.addEdge(1, 0, 10);
		estructura.addEdge(1, 2, 10);
		estructura.addEdge(1, 3, 10);
		Queue<Integer> llaves3 = estructura.vectores();
		estructura.dfs(0);
		while(llaves3.isEmpty() == false)
		{
			assertEquals(estructura.getVertex(llaves3.dequeue()).darMarca(),true);
		}
		
		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");
		estructura.dfs(0);
		
		Queue<Integer> llaves4 = estructura.vectores();
		
		while(llaves4.isEmpty() == false)
		{
			Integer ele = llaves4.dequeue();
			if(ele != 0)
				assertEquals(estructura.getVertex(ele).darMarca(),false);
		}
	}

	@Test
	public void testcc()
	{
		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		estructura.addEdge(0, 1, 10);
		estructura.addEdge(0, 2, 10);
		estructura.addEdge(0, 3, 10);

		estructura.addEdge(1, 0, 10);
		estructura.addEdge(1, 2, 10);
		estructura.addEdge(1, 3, 10);

		assertEquals(estructura.E(),5);

		assertEquals(estructura.cc(),1);

		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		assertEquals(estructura.cc(),4);

		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		estructura.addEdge(0, 1, 10);
		estructura.addEdge(0, 2, 10);

		assertEquals(estructura.cc(),2);

		setUp1();
		estructura.addVertex(0, "a");
		estructura.addVertex(1, "b");
		estructura.addVertex(2, "a");
		estructura.addVertex(3, "a");

		estructura.addEdge(0, 1, 10);

		assertEquals(estructura.cc(),3);

		setUp2();
		assertEquals(estructura.cc(),50);
		assertEquals(estructura.E(),50);

		estructura.uncheck();
		for(int j = 0; j<99; j++)
		{
			estructura.addEdge(j, j+1, j*100);
		}
		assertEquals(estructura.cc(),51);
	}
}
