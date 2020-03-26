package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


import model.data_structures.Comparendo;
import model.data_structures.LavesCompuestas;
import model.data_structures.LinearProbing;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.SeparateChaining;
import model.data_structures.SequentialSearch;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//Ya que mi computador no tiene la capacidad de leer los 500000 datos del archivo grande no pude hacer las tablas de datos con esa informacion, 
	//por ese motivo tuve que trabajar con este archivo que solo tiene 50000. Gracias por tu comprension

	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson";
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private SeparateChaining<LavesCompuestas,Queue<Comparendo>> sequential;
	private LinearProbing<LavesCompuestas,Queue<Comparendo>> linear;
	private Queue<LavesCompuestas> colaSeparateChaining;
	private Queue<LavesCompuestas> colaLinearProbing;
	private Comparendo primero;
	private Comparendo ultimo;
	
	private Queue<LavesCompuestas> cola1;
	private Queue<LavesCompuestas> cola2;

	private int elemnetoscargado;

	public Modelo()
	{
		cola1 = new Queue<LavesCompuestas>();
		cola2 = new Queue<LavesCompuestas>();
		
		elemnetoscargado = 0;
		primero = null;
		ultimo = null;
		sequential = new SeparateChaining<LavesCompuestas,Queue<Comparendo>>();
		linear = new LinearProbing<LavesCompuestas,Queue<Comparendo>>();
		colaSeparateChaining = new Queue<LavesCompuestas>();
		colaLinearProbing= new Queue<LavesCompuestas>();
	}


	// Retorno de estructuras -----------------------
	public SeparateChaining<LavesCompuestas,Queue<Comparendo>> darSeparateChaining()
	{
		return sequential;
	}

	public LinearProbing<LavesCompuestas,Queue<Comparendo>> darLinearProbing()
	{
		return linear;
	}

	public Comparendo darPrimero()
	{
		return primero;
	}

	public Comparendo darUltimo()
	{
		return ultimo;
	}
	
	public Queue<LavesCompuestas> darCola1()
	{
		return cola1;
	}
	
	public Queue<LavesCompuestas> darCola2()
	{
		return cola2;
	}

	
	//---------------------------------------------


	//Tamaño ----------------------------------------------

	public int darelemnetoscargado()
	{
		return elemnetoscargado;
	}
	public int darTamanoSequentialSearch()
	{
		return sequential.size();
	}

	public int darTamanoLinearProbing()
	{
		return linear.size();
	}

	//---------------------------------------------------

	public void cargarDatos() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				elemnetoscargado++;
				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				LavesCompuestas ll= new LavesCompuestas(FECHA_HORA,CLASE_VEHI,INFRACCION);

				if(sequential.isEmpty() == true)
				{
					primero = c;
				}

				Queue<Comparendo> agregar1 = new Queue<Comparendo>();
				if(sequential.get(ll) == null)
				{
					agregar1.enqueue(c);
				}
				else
				{
					Queue<Comparendo> otra = sequential.delete(ll);
					otra.enqueue(c);
					agregar1 = otra;
				}
				sequential.put(ll, agregar1);

				Queue<Comparendo> agregar = new Queue<Comparendo>();
				if(linear.get(ll) == null)
				{
					agregar.enqueue(c);
				}
				else
				{
					Queue<Comparendo> otra = linear.delete(ll);
					otra.enqueue(c);
					agregar = otra;
				}

				linear.put(ll, agregar);
				
				if(cola1.getSize()<8000)
				{
					cola1.enqueue(ll);
				}
				if(cola2.getSize()<8000)
				{
					cola2.enqueue(ll);
				}

				ultimo = c;
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public String tranformar(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "AUTOMÃ“VIL";
		} 
		else if(lista.equals("2"))
		{
			listica = "BICICLETA";
		}
		else if(lista.equals("3"))
		{
			listica = "BUS";
		}
		else if(lista.equals("4"))
		{
			listica = "BUSETA";
		}
		else if(lista.equals("5"))
		{
			listica = "CAMIONETA";
		}
		else if(lista.equals("6"))
		{
			listica = "CAMPERO";
		}
		else if(lista.equals("7"))
		{
			listica =  "MOTOCICLETA";
		}

		return listica;
	}

	public MaxHeapCP<Comparendo> requerimiento1(Date pFacha, String pClase, String pInfraccion)
	{
		MaxHeapCP<Comparendo> lista = new MaxHeapCP<Comparendo>();
		LavesCompuestas llave = new LavesCompuestas(pFacha,pClase,pInfraccion);

		if(linear.get(llave) != null)
		{
			Queue<Comparendo> otra = linear.delete(llave);


			while(otra.getSize() !=0)
			{
				lista.agregar(otra.dequeue());
			}
		}
		return lista;
	}

	public MaxHeapCP<Comparendo> requerimiento2(Date pFacha, String pClase, String pInfraccion)
	{
		MaxHeapCP<Comparendo> lista = new MaxHeapCP<Comparendo>();
		LavesCompuestas llave = new LavesCompuestas(pFacha,pClase,pInfraccion);

		if(sequential.get(llave) != null)
		{
			Queue<Comparendo> otra = sequential.delete(llave);


			while(otra.getSize() !=0)
			{
				lista.agregar(otra.dequeue());
			}
		}
		return lista;
	}
	
	public void rellenarColas() throws ParseException
	{
		SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
		String s11 = "2019-08-08";
		Date FECHA_HORA1 = parser.parse(s11);		
		String tipo1 = "MOTOCICLETA";
		String tipo2 = "C40";
		LavesCompuestas llave = new LavesCompuestas(FECHA_HORA1,tipo1,tipo2);
		
		for(int i = 0; i<2000;i++)
		{
			cola1.enqueue(llave);
			cola2.enqueue(llave);
		}
		
	}
	
	public void tiempoDeGetEnLinearProbing()
	{
		int tamano = cola1.getSize();
		for(int i = 0;i<tamano;i++)
		{
			linear.get(cola1.dequeue());
		}
	}
	
	public void tiempoDeGetEnSeparateChaining()
	{
		int tamano = cola2.getSize();
		for(int i = 0;i<tamano;i++)
		{
			sequential.get(cola2.dequeue());
		}
	}
}


