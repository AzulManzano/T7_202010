package model.data_structures;

public class NodoRedBlackBST<K,V> 
{
	private K llave;           // key
    private V valor;         // associated data
    private NodoRedBlackBST derecha; 
    private NodoRedBlackBST izquierda;  // links to left and right subtrees
    private boolean color;     // color of parent link
    private int tamano;          // subtree count
	
	
	public NodoRedBlackBST(K key, V val, boolean pColor, int pTamano)
	{
		llave = key;
		valor = val;
		color = pColor;
		tamano = pTamano;
	}
	
	public K darLLave()
	{
		return llave;
	}
	
	public V darValor()
	{
		return valor;
	}
	
	public NodoRedBlackBST darDerecho()
	{
		return derecha;
	}
	
	public NodoRedBlackBST darIzquierdo()
	{
		return izquierda;
	}
	
	public boolean darColor()
	{
		return color;
	}
	
	public int size()
	{
		return tamano;
	}
	
	public void cambiarValor(V pValor)
	{
		valor = pValor;
	}
	
	public void cambiarDerecha(NodoRedBlackBST pDerecha)
	{
		derecha = pDerecha;
	}
	
	public void cambiarIzquierda(NodoRedBlackBST pIzquierda)
	{
		izquierda = pIzquierda;
	}
	
	public void cambiarColor(boolean pColor)
	{
		color = pColor;
	}

}
