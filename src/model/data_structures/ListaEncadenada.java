package model.data_structures;

public class ListaEncadenada<T> implements IListaEncadenada<T>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	private Nodo<T> primero;
	private Nodo<T> actual;
	private Nodo<T> ultimo;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public ListaEncadenada(T pPrimero, T pUltimo) 
	{
		primero = new Nodo<T>(pPrimero);
		ultimo = new Nodo<T>(pUltimo);
		actual = null;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public Nodo<T> darPrimero(){
		return primero;
	}

	public Integer getSize() {
		int cont = 0;
		Nodo<T> n = primero;
		while(n!=null){
			cont++;
			n = n.darSiguiente();
		}
		return cont;
	}

	public void add(T dato) {
		Nodo<T> nuevo = new Nodo<T>(dato);
		nuevo.asignarSiguiente(primero);
		primero = nuevo;    
	}

	public void addAtEnd(T dato) {
		Nodo<T> nuevo = new Nodo<T>(dato);
		ultimo.asignarSiguiente(nuevo);
		ultimo = nuevo;

	}

	public void addAtK(T dato, int k) throws Exception {
		if(k>getSize()-1 || k<0){
			throw new Exception("La posicion suministrada no existe");
		}
		else if(k==0){
			add(dato);
		}
		else if(k==getSize()-1){
			addAtEnd(dato);
		}
		else{
			int cont = 0;
			Nodo<T> nuevo = new Nodo<T>(dato);
			Nodo<T> n = primero;
			while(n.darSiguiente()!=null && cont!=k-1){
				cont++;
				n = n.darSiguiente();
			}
			Nodo<T> siguiente = n.darSiguiente();
			nuevo.asignarSiguiente(siguiente);
			n.asignarSiguiente(nuevo);
		}

	}

	public T getElement(int k) throws Exception {

		if(k>getSize()-1 || k<0){
			throw new Exception("La posicion suministrada no existe");
		}
		else{
			int cont = 0;
			Nodo<T> n = primero;
			while(cont!=k){
				cont++;
				n = n.darSiguiente();
			}
			return n.darElemento();
		}
	}

	public T getCurrentElement() {
		return actual.darElemento();
	}

	public boolean delete(T dato) {

		boolean rta = false;
		if(primero.darElemento()==dato){
			primero = primero.darSiguiente();
			rta = true;
		}
		else{

			Nodo<T> n = primero;
			while(n.darSiguiente()!=null && rta == false){
				if(n.darSiguiente().darElemento()==dato){
					n.asignarSiguiente(n.darSiguiente().darSiguiente());
					rta = true;
				}
			}
		}
		return rta;
	}

	public boolean deleteAtK(int k) throws Exception {
		try{
			T elemento = getElement(k);
			boolean rta = delete(elemento);
			if(rta==true)
				return true;
			else
				return false;
		}
		catch(Exception e){
			return false;
		}
	}

	public Iterador<T> iterator() {
		return new Iterador<T>(primero);

	}
}
