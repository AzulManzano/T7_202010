package model.data_structures;

public class Bogota_Vertice 
{
	private int id;
	private double latitud;
	private double longitud;

	public Bogota_Vertice(int pId, double pLatitud, double pLongitud)
	{
		id=pId;
		latitud = pLatitud;
		longitud = pLongitud;
	}
	
	public int darId()
	{
		return id;
	}
	
	public double darLatitud()
	{
		return latitud;
	}
	
	public double darLongitud()
	{
		return longitud;
	}
}
