package model.data_structures;

import java.util.Iterator;

public class Graph<K extends Comparable<K>,V> implements IGraph<K,V>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------
	private int numVertices;
	private int numArcos;

	private int numConectados; 

	private LinearProbing<K,Vertice<K,V>> grafo;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public Graph()
	{
		numVertices = 0;
		numArcos = 0;
		numConectados = 0;
		grafo = new LinearProbing<K,Vertice<K,V>>();
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Obtener el número de vértices.
	 */
	public int V() 
	{
		return numVertices;
	}

	/**
	 * Obtener el número de arcos. Cada arco No dirigido debe contarse una única vez.
	 */
	public int E() 
	{
		return numArcos;
	}

	/**
	 * Adicionar el arco No dirigido entre el vértice IdVertexIni y el vértice IdVertexFin. El arco tiene el costo cost.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost) 
	{
		Vertice<K,V> vertiOrigen = grafo.get(idVertexIni);
		Vertice<K,V> vertiDestino =  grafo.get(idVertexFin);
		if(vertiOrigen != null && vertiDestino != null)
		{
			Arco<K,V> nuevo = new Arco<K,V>(vertiOrigen,vertiDestino,cost);
			Arco<K,V> copia = new Arco<K,V>(vertiDestino,vertiOrigen,cost);
			if(grafo.get(idVertexIni).darAdyacentes().contains(nuevo) == false)
			{
				grafo.get(idVertexIni).agregarArco(nuevo);
				grafo.get(idVertexFin).agregarArco(copia);
				numArcos++;
			}
		}

	}

	/**
	 * Obtener la información de un vértice. Si el vértice no existe retorna null.
	 */
	public V getInfoVertex(K idVertex) 
	{

		if(grafo.get(idVertex) != null)
		{
			return grafo.get(idVertex).darInformacion();
		}
		else 
			return null;
	}

	public Vertice<K,V> getVertex(K idVertex)
	{
		if(grafo.get(idVertex) != null)
		{
			return grafo.get(idVertex);
		}
		else 
			return null;
	}

	/**
	 * Modificar la información del vértice idVertex.
	 */
	public void setInfoVertex(K idVertex, V infoVertex) 
	{
		if(grafo.get(idVertex) != null)
		{
			grafo.get(idVertex).cambiarInformacion(infoVertex);;
		}
	}

	/**
	 * Obtener el costo de un arco, si el arco no existe, retorna –1.0.
	 */
	public double getCostArc(K idVertexIni, K idVertexFin) 
	{
		Vertice<K,V> vertiOrigen = grafo.get(idVertexIni);
		Vertice<K,V> vertiDestino =  grafo.get(idVertexFin);
		if(vertiOrigen != null && vertiDestino != null)
		{
			Queue<Arco<K,V>> lista = vertiOrigen.darAdyacentes().darArcos();
			int tam = lista.getSize();
			boolean encontrado = false;
			for(int i = 0; i < tam && encontrado == false;i++)
			{
				Arco<K,V> elemento = lista.dequeue();
				if(elemento.darDestino().equals(vertiDestino) && elemento.darOrigen().equals(vertiOrigen))
				{
					return elemento.darCosto();
				}
			}

			return -1;
		}
		else
		{
			return -1;
		}

	}

	/**
	 * Modificar el costo del arco No dirigido entre los vértices idVertexIni eidVertexFin
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, double cost) 
	{
		Vertice<K,V> vertiOrigen = grafo.get(idVertexIni);
		Vertice<K,V> vertiDestino =  grafo.get(idVertexFin);
		if(vertiOrigen != null && vertiDestino != null)
		{
			Queue<Arco<K,V>> lista = vertiOrigen.darAdyacentes().darArcos();
			int tam = lista.getSize();
			boolean encontrado = false;
			for(int i = 0; i < tam && encontrado == false;i++)
			{
				Arco<K,V> elemento = lista.dequeue();
				if(elemento.darDestino().equals(vertiDestino) && elemento.darOrigen().equals(vertiOrigen))
				{
					elemento.modificarCosto(cost);;
				}
			}

		}
	}

	/**
	 * Adiciona un vértice con un Id único. El vértice tiene la información InfoVertex.
	 */
	public void addVertex(K idVertex, V infoVertex) 
	{
		if(idVertex != null && infoVertex != null)
		{
			if(grafo.contains(idVertex)==false)
			{
				Vertice<K,V> nuevo = new Vertice<K,V>(idVertex, infoVertex);
				grafo.put(idVertex, nuevo);
				numVertices++;
			}
		}
	}

	/**
	 * Retorna los identificadores de los vértices adyacentes a idVertex
	 */
	public Queue<K> adj(K idVertex)
	{
		Queue<K> retorno= new Queue<K>();
		if(grafo.get(idVertex) != null)
		{
			Queue<Arco<K,V>> lista = grafo.get(idVertex).darAdyacentes().darArcos();
			int tam = lista.getSize();

			for(int i = 0; i<tam; i++)
			{
				retorno.enqueue(lista.dequeue().darDestino().darLlave());
			}

		}
		return retorno;
	}

	/**
	 * Desmarca todos los vértices del grafo
	 */
	public void uncheck() 
	{
		Queue<K> llaves = grafo.llaves();

		for(int i=0; i<numVertices; i++)
		{
			grafo.get(llaves.dequeue()).desmarcar();
		}
	}

	public void dfs(K s) 
	{
		int color = numConectados;
		if(grafo.get(s).darMarca() == false)
		{ 
			grafo.get(s).marcar(color, null);
			numConectados++;
			Vertice<K,V> vertice = getVertex(s);

			Queue<K> ady = adj(s);
			while(ady.isEmpty()== false)
			{
				Queue<Arco<K,V>> lista = vertice.darAdyacentes().darArcos();
				Arco<K,V> arco = null;
				boolean iguales = false;
				K elemento = ady.dequeue();
				while(iguales == false && lista.isEmpty()== false)
				{
					arco = lista.dequeue();
					if(arco.darDestino().darLlave().equals(elemento) && arco.darOrigen().darLlave().equals(s))
					{
						iguales = true;
					}
				}


				if(grafo.get(elemento).darMarca() == false)
				{
					dfs(elemento, color, arco);
				}
			}
		}
	}

	public void dfs(K s, int color, Arco<K,V> pArcoLlegada)
	{
		grafo.get(s).marcar(color, pArcoLlegada);
		Vertice<K,V> vertice = getVertex(s);

		Queue<K> ady = adj(s);
		while(ady.isEmpty()== false)
		{
			Queue<Arco<K,V>> lista = vertice.darAdyacentes().darArcos();
			Arco<K,V> arco = null;
			boolean iguales = false;
			K elemento = ady.dequeue();
			while(iguales == false && lista.isEmpty()== false)
			{
				arco = lista.dequeue();
				if(arco.darDestino().darLlave().equals(elemento) && arco.darOrigen().darLlave().equals(s))
				{
					iguales = true;
				}
			}


			if(grafo.get(elemento).darMarca() == false)
			{
				dfs(elemento, color, arco);
			}
		}

	}

	public int cc() 
	{	
		Queue<K> llaves = vectores();
		while(llaves.isEmpty()== false)
		{
			K elemento = llaves.dequeue();
			
			if(grafo.get(elemento).darMarca() == false)
			{
				dfs(elemento);
			}
		}

		return numConectados;
	}

	public Queue<K> vectores()
	{
		return grafo.llaves();
	}
	
	public int darNumeroMarcados()
	{
		Queue<K> llaves = vectores();
		int contador = 0;
		for(int i =0; i<V();i++)
		{
			if(getVertex(llaves.dequeue()).darMarca()==true)
				contador++;
		}
		return contador;
	}

	public Iterator<K> iterator() 
	{
		return null;
	}

}
