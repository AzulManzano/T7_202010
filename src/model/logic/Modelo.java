package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.Comparendo;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10
	private Stack<Comparendo> pila;
	private Queue<Comparendo> cola;
	private ListaEncadenada<Comparendo> lista;
	private int iniciado;

	private Comparendo mayorObjectId;
	private double mayLatitud;
	private double mayLongitud;
	private double minLatitud;
	private double minLongitud;


	public static String PATH = "./data/comparendos_dei_2018.geojson";

	public Modelo()
	{
		pila = new Stack<Comparendo>();
		cola = new Queue<Comparendo>();
		lista = new ListaEncadenada<Comparendo>();
		iniciado = 0;

		mayorObjectId = null;
		mayLatitud = -90;
		mayLongitud = -90;
		minLatitud = 90;
		minLongitud = 90;

	}


	// Retorno de estructuras -----------------------
	public Queue<Comparendo> darCola()
	{
		return cola;
	}

	public Stack<Comparendo> darPila()
	{
		return pila;
	}

	public ListaEncadenada<Comparendo> darLista()
	{
		return lista;
	}
	//---------------------------------------------

	public int darIniciacion()
	{
		return iniciado;
	}

	public Comparendo darMayorObjectId()
	{
		return mayorObjectId;
	}

	public double darMayLatitud()
	{
		return mayLatitud;
	}

	public double darMayLongitud()
	{
		return mayLongitud;
	}

	public double darMinLatitud()
	{
		return minLatitud;
	}

	public double darMinLongitud()
	{
		return minLongitud;
	}


	//Tamaño ----------------------------------------------
	public int darTamPila()
	{
		return pila.getSize();
	}

	public int darTamCola()
	{
		return cola.getSize();
	}

	public int darTamLista()
	{
		return lista.getSize();
	}

	//---------------------------------------------------

	public Comparendo darComparendoStack()
	{
		return pila.getElement();
	}

	public Comparendo darComparendoQueue()
	{
		return cola.getElement();
	}

	public void cargarDatos() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();


				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				if(pila.isEmpty() == true)
				{
					mayorObjectId = c;
				}
				else 
				{
					if(mayorObjectId.darObjectId() < c.darObjectId() )
					{
						mayorObjectId = c;
					}
					if(mayLatitud < c.darLatitud())
					{
						mayLatitud = c.darLatitud();
					}
					if(mayLongitud < c.darLongitud())
					{
						mayLongitud = c.darLongitud();
					}
					if(minLatitud > c.darLatitud())
					{
						minLatitud = c.darLatitud();
					}
					if(minLongitud > c.darLongitud())
					{
						minLongitud = c.darLongitud();
					}
				}


				pila.push(c);
				cola.enqueue(c);
				lista.add(c);
				iniciado = 1;
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public ArregloDinamico<Comparendo> copiarComparendos()
	{int tam = darTamCola();
		ArregloDinamico<Comparendo> copi = new ArregloDinamico(tam);
		for(int i = 0; i<tam; i++)
		{
			copi.agregar(darCola().dequeue());
		}
		return copi;
	}
	

}
