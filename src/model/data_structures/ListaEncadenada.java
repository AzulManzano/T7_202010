package model.data_structures;

public class ListaEncadenada<T> implements IListaEncadenada<T>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	private Nodo<T> primero;
	private Nodo<T> actual;
	private Nodo<T> ultimo;
	private Integer tamano;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public ListaEncadenada() 
	{
		primero = null;
		ultimo = null;
		actual = null;
		tamano = 0;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public Nodo<T> darPrimero()
	{
		return primero;
	}

	public Integer getSize() 
	{
		return tamano;
	}

	public void add(T dato) 
	{
		Nodo<T> nuevo = new Nodo<T>(dato);
		if(primero != null)
		{
			nuevo.asignarSiguiente(primero);
			primero = nuevo;
			actual = nuevo;
		}
		else 
		{
			primero = nuevo;
			ultimo = nuevo;
			actual = nuevo;
		}
		tamano++;
	}

	public void addAtEnd(T dato) 
	{
		Nodo<T> nuevo = new Nodo<T>(dato);	
		if(ultimo != null)
		{
			ultimo.asignarSiguiente(nuevo);
			ultimo = nuevo;
		}
		else
		{
			primero = nuevo;
			ultimo = nuevo;
			actual = nuevo;
		}
		tamano ++;
	}

	public void addAtK(T dato, int k) throws Exception 
	{
		if(k>getSize()-1 || k<0)
		{
			throw new Exception("La posicion suministrada no existe");
		}
		else if(k==0)
		{
			add(dato);
		}
		else if(k==getSize()-1)
		{
			addAtEnd(dato);
		}
		else
		{
			int cont = 0;
			Nodo<T> nuevo = new Nodo<T>(dato);
			Nodo<T> n = primero;
			while(n.darSiguiente()!=null && cont!=k-1)
			{
				cont++;
				n = n.darSiguiente();
			}
			Nodo<T> siguiente = n.darSiguiente();
			nuevo.asignarSiguiente(siguiente);
			n.asignarSiguiente(nuevo);
			tamano ++;
		}
	}

	public T getElement(int k) throws Exception 
	{

		if(k>getSize()-1 || k<0)
		{
			throw new Exception("La posicion suministrada no existe");
		}
		else
		{
			int cont = 0;
			Nodo<T> n = primero;
			while(cont!=k)
			{
				cont++;
				n = n.darSiguiente();
			}
			return n.darElemento();
		}
	} 

	public void inCero() 
	{
		actual = primero;	
	}

	public T getCurrentElement() 
	{
		return actual.darElemento();
	}

	public void next()
	{
		actual = actual.darSiguiente();
	}

	public boolean delete(T dato) 
	{

		boolean rta = false;
		if(primero.darElemento()==dato)
		{
			primero = primero.darSiguiente();
			rta = true;
			tamano --;
		}
		else
		{
			Nodo<T> n = primero;
			while(n.darSiguiente()!=null && rta == false)
			{
				if(n.darSiguiente().darElemento()==dato)
				{
					n.asignarSiguiente(n.darSiguiente().darSiguiente());
					rta = true;
					tamano --;
				}				
				n = n.darSiguiente();
			}
		}
		return rta;
	}

	public boolean deleteAtK(int k) throws Exception 
	{
		try{
			T elemento = getElement(k);
			boolean rta = delete(elemento);
			if(rta==true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public Iterador<T> iterator() 
	{
		return new Iterador<T>(primero);
	}
}
