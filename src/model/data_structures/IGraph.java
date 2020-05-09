package model.data_structures;

public interface IGraph<K,V> extends Iterable<K> 
{
	/**
	 * Obtener el número de vértices.
	 */
	public int V();
	
	/**
	 * Obtener el número de arcos. Cada arco No dirigido debe contarse una única vez.
	 */
	public int E();
	
	/**
	 * Adicionar el arco No dirigido entre el vértice IdVertexIni y el vértice IdVertexFin. El arco tiene el costo cost.
	 */
	public void addEdge(K idVertexIni, K idVertexFin, double cost);
	
	/**
	 * Obtener la información de un vértice. Si el vértice no existe retorna null.
	 */
	public V getInfoVertex(K idVertex);
	
	/**
	 * Modificar la información del vértice idVertex.
	 */
	public void setInfoVertex(K idVertex,V infoVertex);

	

	public double getCostArc(K idVertexIni, K idVertexFin);
	
	/**
	 * Modificar el costo del arco No dirigido entre los vértices idVertexIni eidVertexFin
	 */
	public void setCostArc(K idVertexIni,K idVertexFin, double cost);

	
	/**
	 * Adiciona un vértice con un Id único. El vértice tiene la información InfoVertex.
	 */
	public void addVertex(K idVertex, V infoVertex);
	
	/**
	 * Desmarca todos los vértices del grafo
	 */
	public void uncheck();
	
	
	public void dfs(K s);
	
	public int cc();


}
