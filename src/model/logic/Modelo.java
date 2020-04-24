package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    //	Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted
	//		
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//Ya que mi computador no tiene la capacidad de leer los 500000 datos del archivo grande no pude hacer las tablas de datos con esa informacion, 
	//por ese motivo tuve que trabajar con este archivo que solo tiene 50000. Gracias por tu comprension

	public static String PATH = "./data/Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted.geojson";
	// PORFAVOR LEER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private MaxHeapCP<Comparendo> listaCarga;
	private RedBlackBST<Date,Comparendo> arbol = new RedBlackBST<Date,Comparendo>();
	private Queue<Comparendo> estructuraAplicacion2C;
	private MaxHeapCP<Comparendo> estructura3C;

	double costoCola;
	double costoMaxHeap;


	int numeroDiasTotales2C;
	int numeroComparendos2C;

	int diasMax400, numer400, dias400, diasMin400; 
	int diasMax40, numer40, dias40, diasMin40; 
	int diasMax4, numer4, dias4, diasMin4; 


	int numeroDiasTotales3C;
	int numeroComparendos3C;
	
	int diasMax400N, numer400N, dias400N, diasMin400N; 
	int diasMax40N, numer40N, dias40N, diasMin40N; 
	int diasMax4N, numer4N, dias4N, diasMin4N; 
	
	int comparendosProcesar;

	public Modelo()
	{
		listaCarga = new MaxHeapCP<Comparendo>();
		estructuraAplicacion2C = new Queue<Comparendo>();
		estructura3C = new MaxHeapCP<Comparendo>();

		costoCola = 0;
		costoMaxHeap =0;

		diasMax400 = 0; 
		diasMin400 = 1000; 
		diasMax40 = 0; 
		diasMin40 = 1000; 
		diasMax4 = 0;  
		diasMin4 = 1000;
		
		diasMax400N = 0; 
		diasMin400N = 1000; 
		diasMax40N = 0; 
		diasMin40N = 1000; 
		diasMax4N = 0;  
		diasMin4N = 1000;
	}


	// Retorno de estructuras -----------------------

	public MaxHeapCP<Comparendo> darListaDeCarga()
	{
		return listaCarga;
	}

	public RedBlackBST<Date,Comparendo> darArbol()
	{
		return arbol;
	}

	public double darCostoCola()
	{
		return costoCola;
	}

	public double darCostoMaxHeap()
	{
		return costoMaxHeap;
	}
	public int darTam()
	{
		int valor = comparendosProcesar;
		return valor;
	}
	//---------------------------------------------


	//Tamaño ----------------------------------------------

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
				arbol.put(FECHA_HORA, c);
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

	public MaxHeapCP<Comparendo> requerimiento3A(String pFechaInicio,String pFechaFinal, String pLocalidad) throws ParseException
	{
		MaxHeapCP<Comparendo> elementos = clonar();
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();

		
		String string = pFechaInicio;
		String[] parts = string.split("-");
		String part1 = parts[0];
		String[] Fechita1 = part1.split("/");
		
		int mesInicial = Integer.parseInt(Fechita1[1]) -1;
		int diaIncial = Integer.parseInt(Fechita1[2]);
		
		Date fechaInicio = new Date(118, mesInicial,diaIncial);
		String part2 = parts[1];
		String[] horita = part2.split(":");
		
		fechaInicio.setHours(Integer.parseInt(horita[0]));
		fechaInicio.setMinutes(Integer.parseInt(horita[1]));
		fechaInicio.setSeconds(Integer.parseInt(horita[2]));
		
		String string1 = pFechaFinal;
		String[] partsC = string1.split("-");
		String part1C = partsC[0];
		String[] Fechita1C = part1C.split("/");
		
		int mesFinal = Integer.parseInt(Fechita1C[1]) -1;
		int diaFinal = Integer.parseInt(Fechita1C[2]);
		
		Date fechaFinal = new Date(118, mesFinal,diaFinal);
		String part2C = partsC[1];
		String[] horitaC = part2C.split(":");
		
		fechaFinal.setHours(Integer.parseInt(horitaC[0]));
		fechaFinal.setMinutes(Integer.parseInt(horitaC[1]));
		fechaFinal.setSeconds(Integer.parseInt(horitaC[2]));
		
		
		RedBlackBST<Date,Comparendo> arbol = new RedBlackBST<Date,Comparendo>();

		int tam = elementos.darNumElementos();
		for(int i = 0; i<tam; i++)
		{
			Comparendo este = elementos.sacarMax();
			arbol.put(este.darFecha(), este);
		}

		Queue<Comparendo> datosPre = arbol.valuesInRange(fechaInicio, fechaFinal);
		int tamFnal = datosPre.getSize();
		for(int j = 0; j<tamFnal;j++)
		{
			Comparendo fin = datosPre.dequeue();
			fin.cambiarComparacion(3);

			if(fin.darLocalidad().equalsIgnoreCase(pLocalidad))
			{
				respuesta.agregar(fin);
			}
		}

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
			actual.cambiarComparacion(3);

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

	public MaxHeapCP<Comparendo> requerimiento3B(double  pLatitudInicial,double pLatitudFinal, String pTipoVeiculo)
	{
		MaxHeapCP<Comparendo> elementos = clonar();
		MaxHeapCP<Comparendo> respuesta = new MaxHeapCP<Comparendo>();

		RedBlackBST<Double,Comparendo> arbol = new RedBlackBST<Double,Comparendo>();

		int tam = elementos.darNumElementos();
		for(int i = 0; i<tam; i++)
		{
			Comparendo este = elementos.sacarMax();
			arbol.put(este.darLatitud(), este);
		}

		Queue<Comparendo> datosPre = arbol.valuesInRange(pLatitudInicial, pLatitudFinal);

		int tamFnal = datosPre.getSize();

		for(int j = 0; j<tamFnal;j++)
		{
			Comparendo fin = datosPre.dequeue();
			fin.cambiarComparacion(0);

			if(fin.darClaseVeiculo().equalsIgnoreCase(pTipoVeiculo))
			{
				respuesta.agregar(fin);
			}
		}


		return respuesta;
	}

	public String requerimiento1C(int rangoInicial, int rangoFinal, int tamno)
	{
		MaxHeapCP<Comparendo> estructura = new MaxHeapCP<Comparendo>();
		comparendosProcesar = arbol.size()/365;
		Date fechaInicio = new Date(118, 0,rangoInicial);
		Date fechaDia = new Date(118, 0,rangoFinal);
		Date fechaFinal = new Date(118, 0,rangoFinal+1);

		Queue<Comparendo> datosPre = arbol.valuesInRange(fechaInicio, fechaFinal);

		
		
		String respueta ="";
		//		2018/01/01-2018/01/07 | ************

		String diaInicial = fechaInicio.getDate()+"";
		String mesInicail = fechaInicio.getMonth()+1+"";

		String diaF = fechaDia.getDate()+"";
		String mesF = fechaDia.getMonth()+1+"";

		if(diaInicial.length() ==1)
			diaInicial = "0"+diaInicial;
		if(mesInicail.length() == 1)
			mesInicail = "0"+mesInicail;
		if(diaF.length() == 1)
			diaF = "0"+diaF;
		if(mesF.length() ==1)
			mesF = "0"+mesF;

		respueta = "2018/"+mesInicail+"/"+diaInicial+"-"+"2018/"+mesF+"/"+diaF+"  |  ";

		int tamnoCola = datosPre.getSize();

		boolean termine = false;

		while(termine == false)
		{
			if(tamnoCola - tamno>=0)
			{
				tamnoCola = tamnoCola -tamno;
				respueta = respueta +"*";
			}
			else
				termine = true;

		}

		return respueta;
	}

	public String requerimiento2C(int rangoInicial, int rangoFinal, int tamno)
	{	

		Date fechaInicio = new Date(118, 0,rangoInicial);
		Date fechaFinal = new Date(118, 0,rangoFinal);

		calcularCosto(estructuraAplicacion2C,fechaInicio);
		comparendosProcesar = arbol.size()/365;

		Queue<Comparendo> comDia = arbol.valuesInRange(fechaInicio, fechaFinal);
		
		int tam = comDia.getSize();

		for(int i =0; i<tam; i++)
		{
			estructuraAplicacion2C.enqueue(comDia.dequeue());
		}

		String respueta1 ="";
		String respueta2 ="";

		String diaInicial = fechaInicio.getDate()+"";
		String mesInicail = fechaInicio.getMonth()+1+"";

		if(diaInicial.length() ==1)
			diaInicial = "0"+diaInicial;
		if(mesInicail.length() == 1)
			mesInicail = "0"+mesInicail;

		respueta1 = "2018/"+mesInicail+"/"+diaInicial+"  |  ";
		respueta2 = "            |  ";

		int tamnoCola = estructuraAplicacion2C.getSize();

		boolean termine = false;
		int contador = 0;
		while(termine == false)
		{ 

			if(tamnoCola - tamno>=0)
			{
				contador++;
				tamnoCola = tamnoCola - tamno;
				respueta1 = respueta1 +"*";
			}
			else
				termine = true;
			if(contador == 2)
				termine = true;

			if(contador == 0 && termine == true)
				respueta1 = respueta1 +"*";
		}


		for(int i = 0; i<comparendosProcesar && estructuraAplicacion2C.isEmpty()==false; i++)
		{

			Comparendo comaren= estructuraAplicacion2C.dequeue();
			numeroDiasTotales2C += comaren.darNumerosDias();
			numeroComparendos2C++;

			if(comaren.darDescripcion().contains("INMOVILIZADO") == true)
			{
				dias400 += comaren.darNumerosDias();
				numer400++;
				if(diasMax400 < comaren.darNumerosDias())
					diasMax400 = comaren.darNumerosDias();
				if(diasMin400 > comaren.darNumerosDias())
					diasMin400= comaren.darNumerosDias();
			}
			else if(comaren.darDescripcion().contains("LICENCIA") == true)
			{
				dias40 += comaren.darNumerosDias();
				numer40++;
				if(diasMax40 < comaren.darNumerosDias())
					diasMax40 = comaren.darNumerosDias();
				if(diasMin40 > comaren.darNumerosDias())
					diasMin40= comaren.darNumerosDias();
			}
			else
			{
				dias4 += comaren.darNumerosDias();
				numer4++;
				if(diasMax4 < comaren.darNumerosDias())
					diasMax4 = comaren.darNumerosDias();
				if(diasMin4 > comaren.darNumerosDias())
					diasMin4= comaren.darNumerosDias();
			}

		}

		int tamanoTotal = estructuraAplicacion2C.getSize();
		termine = false;

		while(termine == false)
		{
			if(tamanoTotal - tamno>=0)
			{
				tamanoTotal = tamanoTotal - tamno;
				respueta2 = respueta2 +"#";
			}
			else
				termine = true;
		}

		return respueta1+"\n"+respueta2;
	}

	private void calcularCosto(Queue<Comparendo> cola, Date fecha)
	{
		Queue<Comparendo> copia = new Queue<Comparendo>();

		int tam = cola.getSize();

		for(int i = 0; i < tam; i++)
		{
			Comparendo este = cola.dequeue();
			este.cambiarDia(fecha);
			copia.enqueue(este);

			if(este.darDescripcion().contains("INMOVILIZADO") == true)
				costoCola+= 400;
			else if(este.darDescripcion().contains("LICENCIA") == true)
				costoCola+= 40;
			else
				costoCola+= 4;	
		}	
		estructuraAplicacion2C = copia;		
	}


	public String promedioDeDias2C()
	{
		Queue<Comparendo> comDia = new Queue<Comparendo>();

		for(int i = 0; estructuraAplicacion2C.isEmpty()==false; i++)
		{

			Comparendo comaren= estructuraAplicacion2C.dequeue();
			numeroDiasTotales2C += comaren.darNumerosDias();
			numeroComparendos2C++;
			comDia.enqueue(comaren);
		}

		estructuraAplicacion2C = comDia;

		int valor = numeroDiasTotales2C/numeroComparendos2C;
		return valor+"";
	}

	public String tablaInformacion()
	{
		Queue<Comparendo> comDia = new Queue<Comparendo>();

		for(int i = 0; estructuraAplicacion2C.isEmpty()==false; i++)
		{

			Comparendo comaren= estructuraAplicacion2C.dequeue();
			if(comaren.darDescripcion().contains("INMOVILIZADO") == true)
			{
				dias400 += comaren.darNumerosDias();
				numer400++;
				if(diasMax400 < comaren.darNumerosDias())
					diasMax400 = comaren.darNumerosDias();
				if(diasMin400 > comaren.darNumerosDias())
					diasMin400= comaren.darNumerosDias();
			}
			else if(comaren.darDescripcion().contains("LICENCIA") == true)
			{
				dias40 += comaren.darNumerosDias();
				numer40++;
				if(diasMax40 < comaren.darNumerosDias())
					diasMax40 = comaren.darNumerosDias();
				if(diasMin40 > comaren.darNumerosDias())
					diasMin40= comaren.darNumerosDias();
			}
			else
			{
				dias4 += comaren.darNumerosDias();
				numer4++;
				if(diasMax4 < comaren.darNumerosDias())
					diasMax4 = comaren.darNumerosDias();
				if(diasMin4 > comaren.darNumerosDias())
					diasMin4= comaren.darNumerosDias();
			}
			comDia.enqueue(comaren);
		}

		estructuraAplicacion2C = comDia;

		String respuesta = "";
		int valor400 = dias400/numer400;
		int valor40 = dias40/numer40;
		int valor4 = dias4/numer4;
		               
		respuesta +="        $400              "+ diasMin400 +"                 "+ valor400+"                 "+diasMax400+"\n"+"----------------------------------------------------------------------------"+"\n";
		respuesta +="        $40               "+ diasMin40 +"                 "+ valor40+"                 "+diasMax40+"\n"+"----------------------------------------------------------------------------"+"\n";
		respuesta +="        $4                "+ diasMin4 +"                 "+ valor4+"                 "+diasMax4;

		return respuesta;
	}

	public String requerimiento3C(int rangoInicial, int rangoFinal, int tamno)
	{
		Date fechaInicio = new Date(118, 0,rangoInicial);
		Date fechaFinal = new Date(118, 0,rangoFinal);

		calcularCostoNuevoSistema(estructura3C,fechaInicio);

		Queue<Comparendo> comDia = arbol.valuesInRange(fechaInicio, fechaFinal);
		comparendosProcesar = arbol.size()/365;

		int tam = comDia.getSize();

		for(int i =0; i<tam; i++)
		{
			Comparendo este = comDia.dequeue();
			este.cambiarComparacion(4);
			estructura3C.agregar(este);
		}

		String respueta1 ="";
		String respueta2 ="";

		String diaInicial = fechaInicio.getDate()+"";
		String mesInicail = fechaInicio.getMonth()+1+"";

		if(diaInicial.length() ==1)
			diaInicial = "0"+diaInicial;
		if(mesInicail.length() == 1)
			mesInicail = "0"+mesInicail;

		respueta1 = "2018/"+mesInicail+"/"+diaInicial+"  |  ";
		respueta2 = "            |  ";

		int tamnoCola = estructura3C.darNumElementos();

		boolean termine = false;
		int contador = 0;

		while(termine == false)
		{ 

			if(tamnoCola - tamno>=0)
			{
				contador++;
				tamnoCola = tamnoCola - tamno;
				respueta1 = respueta1 +"*";
			}
			else
				termine = true;
			if(contador == 2)
				termine = true;

			if(contador == 0 && termine == true)
				respueta1 = respueta1 +"*";
		}

		for(int i = 0; i<comparendosProcesar && estructura3C.esVacia()==false; i++)
		{
			Comparendo comaren = estructura3C.sacarMax();
			numeroDiasTotales3C += comaren.darNumerosDias();
			numeroComparendos3C++;
			
			if(comaren.darDescripcion().contains("INMOVILIZADO") == true)
			{
				dias400N += comaren.darNumerosDias();
				numer400N++;
				if(diasMax400N < comaren.darNumerosDias())
					diasMax400N = comaren.darNumerosDias();
				if(diasMin400N > comaren.darNumerosDias())
					diasMin400N= comaren.darNumerosDias();
			}
			else if(comaren.darDescripcion().contains("LICENCIA") == true)
			{
				dias40N += comaren.darNumerosDias();
				numer40N++;
				if(diasMax40N < comaren.darNumerosDias())
					diasMax40N = comaren.darNumerosDias();
				if(diasMin40N > comaren.darNumerosDias())
					diasMin40N= comaren.darNumerosDias();
			}
			else
			{
				dias4N += comaren.darNumerosDias();
				numer4N++;
				if(diasMax4N < comaren.darNumerosDias())
					diasMax4N = comaren.darNumerosDias();
				if(diasMin4N > comaren.darNumerosDias())
					diasMin4N= comaren.darNumerosDias();
			}
		}

		int tamanoTotal = estructura3C.darNumElementos();
		termine = false;

		while(termine == false)
		{
			if(tamanoTotal - tamno>=0)
			{
				tamanoTotal = tamanoTotal - tamno;
				respueta2 = respueta2 +"#";
			}
			else
				termine = true;
		}

		return respueta1+"\n"+respueta2;
	}

	private void calcularCostoNuevoSistema(MaxHeapCP<Comparendo> cola, Date fecha)
	{
		MaxHeapCP<Comparendo> copia = new MaxHeapCP<Comparendo>();

		int tam = cola.darNumElementos();

		for(int i = 0; i < tam; i++)
		{
			Comparendo este = cola.sacarMax();
			este.cambiarDia(fecha);
			este.cambiarComparacion(4);
			copia.agregar(este);
			
			if(este.darDescripcion().contains("INMOVILIZADO") == true)
				costoMaxHeap+= 400;
			else if(este.darDescripcion().contains("LICENCIA") == true)
				costoMaxHeap+= 40;
			else
				costoMaxHeap+= 4;	
		}	
		estructura3C = copia;	
		
		
		
	}

	public String promedioDeDias3C()
	{
		MaxHeapCP<Comparendo> copia = new MaxHeapCP<Comparendo>();

		for(int i = 0; estructura3C.esVacia()==false; i++)
		{
			Comparendo compa = estructura3C.sacarMax();
			numeroDiasTotales3C += compa.darNumerosDias();
			numeroComparendos3C++;
			copia.agregar(compa);
		}
		estructura3C = copia;
		int valor = numeroDiasTotales3C/numeroComparendos3C;
		return valor+"";
	}

	public String tablaNuevoSistema()
	{
		MaxHeapCP<Comparendo> copia = new MaxHeapCP<Comparendo>();

		for(int i = 0; estructura3C.esVacia()==false; i++)
		{
			Comparendo comaren = estructura3C.sacarMax();
			if(comaren.darDescripcion().contains("INMOVILIZADO") == true)
			{
				dias400N += comaren.darNumerosDias();
				numer400N++;
				if(diasMax400N < comaren.darNumerosDias())
					diasMax400N = comaren.darNumerosDias();
				if(diasMin400N > comaren.darNumerosDias())
					diasMin400N= comaren.darNumerosDias();
			}
			else if(comaren.darDescripcion().contains("LICENCIA") == true)
			{
				dias40N += comaren.darNumerosDias();
				numer40N++;
				if(diasMax40N < comaren.darNumerosDias())
					diasMax40N = comaren.darNumerosDias();
				if(diasMin40N > comaren.darNumerosDias())
					diasMin40N= comaren.darNumerosDias();
			}
			else
			{
				dias4N += comaren.darNumerosDias();
				numer4N++;
				if(diasMax4N < comaren.darNumerosDias())
					diasMax4N = comaren.darNumerosDias();
				if(diasMin4N > comaren.darNumerosDias())
					diasMin4N= comaren.darNumerosDias();
			}
			copia.agregar(comaren);
		}
		estructura3C = copia;

		String respuesta = "";
		int valor400 = dias400N/numer400N;
		int valor40 = dias40N/numer40N;
		int valor4 = dias4N/numer4N;

		respuesta +="        $400              "+ diasMin400N +"                 "+ valor400+"                 "+diasMax400N+"\n"+"----------------------------------------------------------------------------"+"\n";
		respuesta +="        $40               "+ diasMin40N +"                 "+ valor40+"                 "+diasMax40N+"\n"+"----------------------------------------------------------------------------"+"\n";
		respuesta +="        $4                "+ diasMin4N +"                 "+ valor4+"                 "+diasMax4N;

		return respuesta;
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

	public String tranformarMedioDeteccion(String lista)
	{
		String listica = "";

		if(lista.equals("1"))
		{
			listica = "AUTOMÃ“VIL";
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
			listica = "PÃºblico";
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
			listica =  "ANTONIO NARIÃ‘O";
		}

		return listica;
	}
}


