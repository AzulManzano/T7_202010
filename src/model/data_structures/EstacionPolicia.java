package model.data_structures;

public class EstacionPolicia 
{

	private int objectId;
	private String direccion;
	private String nobre;
	
	private double latitud;
	private double longitud;
	
	public EstacionPolicia(int pObjet, String pDireccion, String pNombre, double pLatitud, double pLongitud)
	{
		objectId = pObjet;
		direccion = pDireccion;
		nobre = pNombre;
		latitud = pLatitud;
		longitud = pLongitud;
	}
	
	public int darObjet()
	{
		return objectId;
	}
	
	public String darDireccion()
	{
		return direccion;
	}
	
	public String darNombre()
	{
		return nobre;
	}
	
	public double darLatitud()
	{
		return latitud;
	}
	
	public double darLongitud()
	{
		return longitud;
	}
	
	public String darInformacion()
	{
		return "[OBJECTID: " + objectId+ ", NOMBRE: " + nobre+", DIRECCION: "+ direccion+ ", LATITUD: " + latitud + ", LONGITUD: "+longitud+"]";
	}
}
