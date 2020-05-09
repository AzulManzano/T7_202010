package model.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.Bogota_Vertice;
import model.data_structures.Comparendo;
import model.data_structures.Escritor;
import model.data_structures.EstacionPolicia;
import model.data_structures.Graph;
import model.data_structures.Haversine;
import model.data_structures.LinearProbing;

import model.data_structures.Nodo;
import model.data_structures.Queue;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	//	Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted
	//		
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//Ya que mi computador no tiene la capacidad de leer los 500000 datos del archivo grande no pude hacer las tablas de datos con esa informacion, 
	//por ese motivo tuve que trabajar con este archivo que solo tiene 50000. Gracias por tu comprension

	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted.geojson";
	public static String ESTACION = "./data/estacionpolicia.geojson.json";
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	private Graph<Integer, Bogota_Vertice> grafo;
	private Graph<Integer, Bogota_Vertice> limitado;
	
	private Queue<EstacionPolicia> estaciones;
	private Queue<EstacionPolicia> estacionesDelimitadas;
	
	private Haversine distanciador;

	private Escritor escritor;

	public Modelo()
	{
		grafo = new Graph<Integer, Bogota_Vertice>();
		distanciador = new Haversine();
		limitado = new Graph<Integer, Bogota_Vertice>();
		escritor = new Escritor();
		
		estaciones = new Queue<EstacionPolicia>();
		estacionesDelimitadas = new Queue<EstacionPolicia>();
	}


	// Retorno de estructuras -----------------------

	public Graph<Integer, Bogota_Vertice> darGrafo()
	{
		return grafo;	
	}

	public Graph<Integer, Bogota_Vertice> darLimitado()
	{
		return limitado;	
	}

	public Escritor escribir()
	{
		return escritor;
	}
	
	public Queue<EstacionPolicia> darEstaciones()
	{
		return estaciones;
	}
	
	public Queue<EstacionPolicia> darEstacionesDelimitadas()
	{
		return estacionesDelimitadas;
	}

	//---------------------------------------------

	public int darNumeroVertices()
	{
		return grafo.V();
	}

	public int darNumeroArcos()
	{
		return grafo.E();
	}

	public void reiniciar()
	{
		grafo = new Graph<Integer, Bogota_Vertice>();
		limitado = new Graph<Integer, Bogota_Vertice>();
	}

	//Tamaño ----------------------------------------------


	//---------------------------------------------------

	public void cargarDatos() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


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


				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cargarVertices( String pArchivo, Graph<Integer, Bogota_Vertice> grafo, Graph<Integer, Bogota_Vertice> grafo1) throws Exception
	{
		String texto, valores[];

		int id;
		double  latitud, longitud;


		try
		{
			File datos = new File( pArchivo );
			FileReader fr = new FileReader( datos );
			BufferedReader lector = new BufferedReader( fr );

			texto = lector.readLine( );
			while(texto != null)
			{
				valores = texto.split( "," );

				id = Integer.parseInt(valores[ 0 ]);
				longitud = Double.parseDouble(valores[ 1 ]);
				latitud = Double.parseDouble(valores[ 2 ]);

				Bogota_Vertice nuevo = new Bogota_Vertice(id,latitud,longitud );
				grafo.addVertex(id, nuevo);

				if((longitud< -74.062707 && longitud > -74.094723) && (latitud <  4.621360 && latitud > 4.597714 ))
					grafo1.addVertex(id, nuevo);

				// Siguiente línea
				texto = lector.readLine( );
			}
			lector.close( );
		}
		catch( IOException e )
		{
			throw new Exception( "Error al cargar los datos almacenados de vehículos." );
		}
		catch( NumberFormatException e )
		{
			throw new Exception( "El archivo no tiene el formato esperado." );
		}
	}

	public void cargarArcos( String pArchivo, Graph<Integer, Bogota_Vertice> grafo, Graph<Integer, Bogota_Vertice> grafo1) throws Exception
	{
		String texto, valores[];

		Integer idOrigen;
		Integer idDestino;


		try
		{
			File datos = new File( pArchivo );
			FileReader fr = new FileReader( datos );
			BufferedReader lector = new BufferedReader( fr );

			texto = lector.readLine( );
			texto = lector.readLine( );
			texto = lector.readLine( );
			texto = lector.readLine( );
			while(texto != null)
			{
				valores = texto.split( " " );
				int tam = valores.length;

				idOrigen = Integer.parseInt(valores[ 0 ]);
				Bogota_Vertice origen = grafo.getInfoVertex(idOrigen);

				for(int i = 1;i<tam;i++)
				{
					idDestino = Integer.parseInt(valores[ i ]);
					Bogota_Vertice destino = grafo.getInfoVertex(idDestino);

					if(origen != null && destino != null)
					{
						double distancia = distanciador.distance(origen.darLatitud(), origen.darLongitud(), destino.darLatitud(), destino.darLongitud());
						grafo.addEdge(idOrigen, idDestino, distancia);grafo1.addEdge(idOrigen, idDestino, distancia);
					}
				}




				// Siguiente línea
				texto = lector.readLine( );
			}
			lector.close( );
		}
		catch( IOException e )
		{
			throw new Exception( "Error al cargar los datos almacenados de vehículos." );
		}
		catch( NumberFormatException e )
		{
			throw new Exception( "El archivo no tiene el formato esperado." );
		}
	}

	public void crearJson()
	{

		JSONObject obj = new JSONObject();
		obj.put("Num_vertices", darNumeroVertices());
		obj.put("Num_arcos", darNumeroArcos());


		JSONArray list = new JSONArray();

		Queue<Integer> llaves = darGrafo().vectores();
		for(int i=0; i<darNumeroVertices(); i++)
		{
			Bogota_Vertice elemento = darGrafo().getInfoVertex(llaves.dequeue());
			JSONObject propiedades = new JSONObject();

			propiedades.put("Longitud", elemento.darLongitud());
			propiedades.put("Latitud", elemento.darLatitud());
			propiedades.put("Id",elemento.darId());
			list.add(propiedades);

		}
		obj.put("Vertices", list);


		JSONArray list2 = new JSONArray();

		Queue<Integer> llaves1 = darGrafo().vectores();
		for(int i=0; i<darNumeroVertices(); i++)
		{
			Integer origen = llaves1.dequeue();
			Queue<Integer> adya = grafo.adj(origen);

			if(adya.isEmpty() == false)
			{
				while(adya.isEmpty() == false)
				{
					Integer destino = adya.dequeue();
					if(origen<destino)
					{
						JSONObject propiedades = new JSONObject();

						propiedades.put("IdOrigen", origen);
						propiedades.put("IdDestino", destino);
						Double costo = grafo.getCostArc(origen, destino);
						propiedades.put("Costo",costo);
						list2.add(propiedades);
					}
				}
			}
		}
		obj.put("Arcos", list2);



		try {

			FileWriter file = new FileWriter("data/mallaVial.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			//manejar error
		}
	}

	public void cargarInfo() 
	{
		reiniciar();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader("data/mallaVial.json"));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("Vertices").getAsJsonArray();


			for(JsonElement e: e2) {

				double longitud = e.getAsJsonObject().get("Longitud").getAsDouble();
				double latitud = e.getAsJsonObject().get("Latitud").getAsDouble();
				int OBJECTID = e.getAsJsonObject().get("Id").getAsInt();

				Bogota_Vertice nuevo = new Bogota_Vertice(OBJECTID,latitud,longitud );
				grafo.addVertex(OBJECTID, nuevo);
				
				if((longitud< -74.062707 && longitud > -74.094723) && (latitud <  4.621360 && latitud > 4.597714 ))
					limitado.addVertex(OBJECTID, nuevo);
					

			}

			JsonArray e3 = elem.getAsJsonObject().get("Arcos").getAsJsonArray();

			for(JsonElement e: e3) {

				double costo = e.getAsJsonObject().get("Costo").getAsDouble();
				int destino = e.getAsJsonObject().get("IdDestino").getAsInt();
				int origen = e.getAsJsonObject().get("IdOrigen").getAsInt();

				grafo.addEdge(origen, destino, costo);
				limitado.addEdge(origen, destino, costo);

			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cargarEstaciones() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(ESTACION));
			JsonElement elem = JsonParser.parseReader(reader); 
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			for(JsonElement e: e2) {
				
				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();
				
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				 
				String DIRECCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPODIR_SITIO").getAsString();
				String NOMBRE = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPONOMBRE").getAsString();
				
				
				EstacionPolicia n = new EstacionPolicia( OBJECTID, DIRECCION,  NOMBRE,  latitud,  longitud);
				
				estaciones.enqueue(n); 
				
				if((longitud< -74.062707 && longitud > -74.094723) && (latitud <  4.621360 && latitud > 4.597714 ))
					estacionesDelimitadas.enqueue(n);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


}