package model.data_structures;

public class Arco<K,V> implements Comparable<Arco<K,V>>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	private double costo;

	private Vertice<K,V> origen;

	private Vertice<K,V> destino;


	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public Arco(Vertice<K,V> pOrigen, Vertice<K,V> pDestino, double pCosto)
	{
		costo = pCosto;
		origen = pOrigen;
		destino = pDestino;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public Vertice<K,V> darOrigen()
	{
		return origen;
	}

	public Vertice<K,V> darDestino()
	{
		return destino;
	}

	public double darCosto()
	{
		return costo;
	}

	public void modificarCosto(double pCosto)
	{
		costo = pCosto;
	}


	public int compareTo(Arco<K, V> arg0) 
	{
		if(this.darOrigen().darLlave().equals(arg0.darOrigen().darLlave())  && this.darDestino().darLlave().equals(arg0.darDestino().darLlave()))
		{
			return 0;	
		}
		else
		{
			return 1;
		}
	}
}
