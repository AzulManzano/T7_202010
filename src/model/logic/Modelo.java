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
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10


	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";

	private Comparendo[] listaLarga;
	private Comparendo[] listaCorta;
	private MaxColaCP<Comparendo> maxCola;
	private ArrayList<Comparendo> verificador;
	private MaxHeapCP<Comparendo> maxHeap;

	private int tamanoLargo;
	private int tamanoCorto;

	public Modelo()
	{
		listaLarga = new Comparendo[600000];
		verificador = new ArrayList<Comparendo>();
		tamanoLargo = 0;
		tamanoCorto = 0;
	}


	// Retorno de estructuras -----------------------

	public Comparendo[] darListaLarga()
	{
		return 	listaLarga;
	}

	public ArrayList<Comparendo> darVerificado()
	{
		return verificador;
	}

	public Comparendo[] darListaCorta()
	{
		return 	listaCorta;
	}

	public MaxColaCP<Comparendo> darMaxColaCP()
	{
		return maxCola;
	}

	public MaxHeapCP<Comparendo> darMaxHeapCP()
	{
		return maxHeap;
	}

	//---------------------------------------------


	//Tamaño ----------------------------------------------

	public int darTamnoListaLarga()
	{
		return tamanoLargo;
	}

	public int darTamanoMuestra()
	{
		return tamanoCorto;
	}

	public int darTamanoMaxHeap()
	{
		return maxHeap.darNumElementos();
	}

	public int darTmanoMaxCola()
	{
		return maxCola.darNumElementos();
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


				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				listaLarga[tamanoLargo] = c;
				tamanoLargo++;
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public int hacerMuestra(int numero)
	{
		if(tamanoLargo>numero)
		{
			listaCorta = new Comparendo[numero];
			int indice = (int) tamanoLargo/numero;
			for(int i = 0; i<numero; i++)
			{
				if(indice<tamanoLargo)
				{
					listaCorta[i] = listaLarga[indice];
				}
				else 
				{
					indice = 0;
					listaCorta[i] = listaLarga[indice];
				}
				indice++;
				tamanoCorto++;
			}

		}
		else 
		{
			listaCorta = listaLarga;
			tamanoCorto = tamanoLargo;
		}
		return tamanoCorto;
	}

	public void cargarMaxColaCP()
	{
		maxCola = new MaxColaCP<Comparendo>(listaCorta);
	}

	public void cargarMaxHeapCP()
	{
		maxHeap = new MaxHeapCP<Comparendo>(listaCorta);
	}

	public MaxColaCP<Comparendo> requerimienrto1(String[] lista, int numero)
	{
		MaxColaCP<Comparendo> temporal = new MaxColaCP<Comparendo>();
		boolean termine = false;
		int maximo = maxCola.darNumElementos() ;
		
		for(int i = 0; i<maximo && termine==false;i++)
		{
			Comparendo variable = maxCola.sacarMax();
			boolean termine2 =false;
			for(int j = 0;j<lista.length && termine2 == false;j++)
			{
				if(lista[j].equals(variable.darCarro()))
				{
					temporal.agregar(variable);
					termine2 = true;
				}
			}
			if(temporal.darNumElementos() == numero)
			{
				termine = true;
			}
		}
		return temporal;
	}

	public MaxHeapCP<Comparendo> requerimienrto2(String[] lista, int numero)
	{
		MaxHeapCP<Comparendo> temporal = new MaxHeapCP<Comparendo>(numero);
		boolean termine = false;
		int maximo = maxHeap.darNumElementos() ;

		for(int i = 0; i<maximo && termine==false;i++)
		{
			Comparendo variable = maxHeap.sacarMax();
			boolean termine2 =false;
			for(int j = 0;j<lista.length && termine2 == false;j++)
			{
				if(lista[j].equals(variable.darCarro()))
				{
					temporal.agregar(variable);
					termine2 = true;
				}
			}
			if(temporal.darNumElementos() == numero)
			{
				termine = true;
			}
		}

		return temporal;
	}

	public String[] tranformar(String[] lista)
	{
		String[] listica = new String[lista.length];

		for(int i = 0; i<lista.length;i++)
		{
			if(lista[i].equals("1"))
			{
				listica[i] = "AUTOMÃ“VIL";
			} 
			else if(lista[i].equals("2"))
			{
				listica[i] = "BICICLETA";
			}
			else if(lista[i].equals("3"))
			{
				listica[i] = "BUS";
			}
			else if(lista[i].equals("4"))
			{
				listica[i] = "BUSETA";
			}
			else if(lista[i].equals("5"))
			{
				listica[i] = "CAMIONETA";
			}
			else if(lista[i].equals("6"))
			{
				listica[i] = "CAMPERO";
			}
			else if(lista[i].equals("7"))
			{
				listica[i] =  "MOTOCICLETA";
			}
		}
		return listica;
	}
}


