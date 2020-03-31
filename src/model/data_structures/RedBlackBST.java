package model.data_structures;

import java.util.Iterator;

public class RedBlackBST<K extends Comparable<K>,V> implements IRedBlackBST<K,V> 
{
	private static final boolean RED   = true;
    private static final boolean BLACK = false;
    
    private NodoRedBlackBST raiz;

	public RedBlackBST()
	{
		
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(K key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(K key, V val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator <K> keys()
	{
		return null;
	}
	
	public Iterator<V> valuesInRange(K init, K end)
	{
		return null;
	}
	
	public Iterator<K> keysInRange(K init, K end)
	{
		return null;
	}


	public Iterator<K> iterator() 
	{
		return keys();
	}
}

