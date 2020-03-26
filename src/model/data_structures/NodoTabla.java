package model.data_structures;

public class NodoTabla<K,V>
{
	private K key;
    private V val;
    private NodoTabla<K,V> next;
    
    public NodoTabla(K llave, V valor, NodoTabla<K,V> siguiente)
    {
    	key = llave;
    	val = valor;
    	next = siguiente;
    }
    
    
    public K darLLave()
    {
    	return key;
    }
    
    public V darValor()
    {
    	return val;
    }
    
    public NodoTabla<K,V> darSiguiente()
    {
    	return next;
    }
    
    public void asignarSiguiente(NodoTabla<K,V> valor)
    {
    	next = valor;
    }
    
    public void cambiarValor(V nuevo)
    {
    	val = nuevo;
    }
}
