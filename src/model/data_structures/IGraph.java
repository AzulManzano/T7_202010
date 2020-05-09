package model.data_structures;

public interface IGraph<K,V> extends Iterable<K> 
{
	/**
	 * Obtener el n�mero de v�rtices.
	 */
	public int V();
	
	/**
	 * Obtener el n�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
	 */
	public int E();
	
	/**
	 * Adicionar el arco No dirigido entre el v�rtice IdVertexIni y el v�rtice IdVertexFin. El arco tiene el costo cost.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * Obtener la informaci�n de un v�rtice. Si el v�rtice no existe retorna null.
	 */
	public V getInfoVertex(K idVertex);
	
	/**
	 * Modificar la informaci�n del v�rtice idVertex.
	 */
	public void setInfoVertex(K idVertex,V infoVertex);

	

	public double getCostArc(K idVertexIni, K idVertexFin);
	
	/**
	 * Modificar el costo del arco No dirigido entre los v�rtices idVertexIni eidVertexFin
	 */
	public void setCostArc(K idVertexIni,K idVertexFin, double cost);

	
	/**
	 * Adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex.
	 */
	public void addVertex(K idVertex, V infoVertex);
	
	/**
	 * Desmarca todos los v�rtices del grafo
	 */
	public void uncheck();
	
	
	public void dfs(K s);
	
	public int cc();


}
