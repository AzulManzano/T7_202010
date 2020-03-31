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

import model.data_structures.RedBlackBST;
import model.data_structures.Comparendo;
import model.data_structures.LLaves2A;
import model.data_structures.LLaves2B;
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

	public static String PATH = "./data/Comparendos_DEI_2018_Bogot�_D.C_50000_.geojson";
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private MaxHeapCP<Comparendo> listaCarga;


	public Modelo()
	{
		listaCarga = new MaxHeapCP<Comparendo>();
	}


	// Retorno de estructuras -----------------------

	public MaxHeapCP<Comparendo> darListaDeCarga()
	{
		return listaCarga;
	}
	//---------------------------------------------


	//Tama�o ----------------------------------------------

	public int darTotalComparendos()
	{
		return listaCarga.darNumElementos();
	}

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

				listaCarga.agregar(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public MaxHeapCP<Comparendo> requerimiento1A()
	{
		MaxHeapCP<Comparendo> elementos = clonar();
		int num = elementos.darNumElementos();
		MaxHeapCP<Comparendo> elementosFinales = new MaxHeapCP<Comparendo>();

		for(int i = 0; i<num;i++)
		{
			Comparendo este = elementos.sacarMax();
			este.cambiarComparacion(1);


			elementosFinales.agregar(este);
		}
		return elementosFinales;
	}

	public MaxHeapCP<Comparendo> requerimiento2A(Integer pMes, Integer pDia)
	{
		LLaves2A llave = new LLaves2A(pMes,pDia);
		SeparateChaining<LLaves2A,MaxHeapCP<Comparendo>> estructura = new SeparateChaining<LLaves2A,MaxHeapCP<Comparendo>>();
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();
		MaxHeapCP<Comparendo> trabajable = clonar();

		int corredor = trabajable.darNumElementos();

		for(int i = 0; i <corredor;i++)
		{
			Comparendo actual = trabajable.sacarMax();
			actual.cambiarComparacion(0);

			Integer diaIndicado = actual.darFecha().getDay();
			Integer mesIndicado = actual.darFecha().getMonth();
			LLaves2A llaveq = new LLaves2A(mesIndicado,diaIndicado);


			MaxHeapCP<Comparendo> agregada = new MaxHeapCP<Comparendo>();
			if(estructura.get(llaveq) == null)
			{
				agregada.agregar(actual);
			}
			else
			{
				MaxHeapCP<Comparendo> estano = estructura.delete(llaveq);
				estano.agregar(actual);
				agregada = estano;
			}


			estructura.put(llaveq, agregada);
		}

		respuesta = estructura.get(llave);

		return respuesta;
	}
	
	public MaxHeapCP<Comparendo> requerimiento3A(String pFechaInicio,String pFechaFinal, String pLocalidad)
	{
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();
		
		return respuesta;
	}

	public MaxHeapCP<Comparendo> requerimiento1B()
	{
		MaxHeapCP<Comparendo> elementos = clonar();
		int num = elementos.darNumElementos();
		MaxHeapCP<Comparendo> elementosFinales = new MaxHeapCP<Comparendo>();

		for(int i = 0; i<num;i++)
		{
			Comparendo este = elementos.sacarMax();
			este.cambiarComparacion(2);


			elementosFinales.agregar(este);
		}

		return elementosFinales;
	}

	public MaxHeapCP<Comparendo> requerimiento2B(String pmedio_dete,String pclase_vehi, String ptipo_servi,String plocalidad)
	{
		LLaves2B llave = new LLaves2B(pmedio_dete,pclase_vehi,ptipo_servi,plocalidad);
		SeparateChaining<LLaves2B,MaxHeapCP<Comparendo>> estructura = new SeparateChaining<LLaves2B,MaxHeapCP<Comparendo>>();
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();
		MaxHeapCP<Comparendo> trabajable = clonar();

		int corredor = trabajable.darNumElementos();

		for(int i = 0; i <corredor;i++)
		{
			Comparendo actual = trabajable.sacarMax();
			actual.cambiarComparacion(0);

			LLaves2B llaveq = new LLaves2B(actual.darMedioDeteccion(),actual.darClaseVeiculo(),actual.darTipoServicio(),actual.darLocalidad());


			MaxHeapCP<Comparendo> agregada = new MaxHeapCP<Comparendo>();
			if(estructura.get(llaveq) == null)
			{
				agregada.agregar(actual);
			}
			else
			{
				MaxHeapCP<Comparendo> estano = estructura.delete(llaveq);
				estano.agregar(actual);
				agregada = estano;
			}


			estructura.put(llaveq, agregada);
		}

		respuesta = estructura.get(llave);

		return respuesta;
	}
	
	public MaxHeapCP<Comparendo> requerimiento3B(String pLatitudInicial,String pLatitudFinal, String pTipoVeiculo)
	{
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();
		
		return respuesta;
	}
	
	public SeparateChaining<Date,MaxHeapCP<Comparendo>> requerimiento1C(int rango)
	{
		SeparateChaining<Date,MaxHeapCP<Comparendo>> estructura = new SeparateChaining<Date,MaxHeapCP<Comparendo>>();
		
		return estructura;
	}
	
	public RedBlackBST<Date,MaxHeapCP<Comparendo>> requerimiento2C()
	{
		RedBlackBST<Date,MaxHeapCP<Comparendo>> estructura = new RedBlackBST<Date,MaxHeapCP<Comparendo>>();
		
		return estructura;
	}
	
	public RedBlackBST<Date,MaxHeapCP<Comparendo>> requerimiento3C()
	{
		RedBlackBST<Date,MaxHeapCP<Comparendo>> estructura = new RedBlackBST<Date,MaxHeapCP<Comparendo>>();
		
		return estructura;
	}
	

	public MaxHeapCP<Comparendo> clonar()
	{
		MaxHeapCP<Comparendo> nueva =  new MaxHeapCP<Comparendo>();
		MaxHeapCP<Comparendo> vieja =  new MaxHeapCP<Comparendo>();
		int cor = listaCarga.darNumElementos();

		for(int i = 0; i<cor;i++)
		{
			Comparendo agre = listaCarga.sacarMax();
			nueva.agregar(agre);
			vieja.agregar(agre);
		}

		listaCarga = vieja;

		return nueva;			
	}

	public String indicarMes(int inicial)
	{
		if(inicial == 0)
			return "Enero";
		else if(inicial == 1)
			return "Febrero";
		else if(inicial == 2)
			return "Marzo";
		else if(inicial == 3)
			return "Abril";
		else if(inicial == 4)
			return "Mayo";
		else if(inicial == 5)
			return "Junio";
		else if(inicial == 6)
			return "Julio";
		else if(inicial == 7)
			return "Agosto";
		else if(inicial == 8)
			return "Septiembre";
		else if(inicial == 9)
			return "Octubre";
		else if(inicial == 10)
			return "Noviembre";
		else if(inicial == 11)
			return "Diciembre";

		return "";
	}

	public int indicarDia(String inicial)
	{
		if(inicial.equalsIgnoreCase("L"))
			return 1;
		else if(inicial.equalsIgnoreCase("M"))
			return 2;
		else if(inicial.equalsIgnoreCase("I"))
			return 3;
		else if(inicial.equalsIgnoreCase("J"))
			return 4;
		else if(inicial.equalsIgnoreCase("V"))
			return 5;
		else if(inicial.equalsIgnoreCase("S"))
			return 6;
		else if(inicial.equalsIgnoreCase("D"))
			return 0;
		return -1;
	}

	public String indicarDia2(String inicial)
	{
		if(inicial.equalsIgnoreCase("L"))
			return "Lunes";
		else if(inicial.equalsIgnoreCase("M"))
			return "Martes";
		else if(inicial.equalsIgnoreCase("I"))
			return "Miercoles";
		else if(inicial.equalsIgnoreCase("J"))
			return "Jueves";
		else if(inicial.equalsIgnoreCase("V"))
			return "Viernes";
		else if(inicial.equalsIgnoreCase("S"))
			return "Sabado";
		else if(inicial.equalsIgnoreCase("D"))
			return "Domingo";
		return "";
	}

	public String tranformarTipoVeculo(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "AUTOMÓVIL";
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
	
	public String tranformarMedioDeteccion(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "AUTOMÓVIL";
		} 
		else if(lista.equals("2"))
		{
			listica = "DEAP";
		}
		

		return listica;
	}
	
	public String tranformarTipoServicio(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "Público";
		} 
		else if(lista.equals("2"))
		{
			listica = "Oficial";
		}
		else if(lista.equals("3"))
		{
			listica = "Particular";
		}
		

		return listica;
	}
	
	public String transformarLocalidad(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "PUENTE ARANDA";
		} 
		else if(lista.equals("2"))
		{
			listica = "FONTIBON";
		}
		else if(lista.equals("3"))
		{
			listica = "ENGATIVA";
		}
		else if(lista.equals("4"))
		{
			listica = "SUBA";
		}
		else if(lista.equals("5"))
		{
			listica = "USME";
		}
		else if(lista.equals("6"))
		{
			listica = "CIUDAD BOLIVAR";
		}
		else if(lista.equals("7"))
		{
			listica =  "USAQUEN";
		}
		else if(lista.equals("8"))
		{
			listica = "BOSA";
		}
		else if(lista.equals("9"))
		{
			listica = "SAN CRISTOBAL";
		}
		else if(lista.equals("10"))
		{
			listica = "KENNEDY";
		}
		else if(lista.equals("11"))
		{
			listica = "CHAPINERO";
		}
		else if(lista.equals("12"))
		{
			listica = "MARTIRES";
		}
		else if(lista.equals("13"))
		{
			listica =  "BARRIOS UNIDOS";
		}
		else if(lista.equals("14"))
		{
			listica =  "TUNJUELITO";
		}
		else if(lista.equals("15"))
		{
			listica =  "SANTA FE";
		}
		else if(lista.equals("16"))
		{
			listica =  "ANTONIO NARIÑO";
		}

		return listica;
	}
}


