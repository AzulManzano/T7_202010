package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.Bogota_Vertice;
import model.data_structures.Comparendo;
import model.data_structures.Graph;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.logic.Modelo;
import view.View;

public class Controller 
{
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;


		view.bienvenida();

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			if(option == 1)
			{
				try
				{
					modelo.cargarVertices("data/bogota_vertices.txt", modelo.darGrafo(),modelo.darLimitado());
					modelo.cargarArcos("data/bogota_arcos.txt", modelo.darGrafo(),modelo.darLimitado());
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			switch(option)
			{
			case 1:			
				view.printMessage("");
				view.printMessage("EL GRAFO SE CREO SATISFACTORIAMENTE");
				view.printMessage("  -El total de vertices es: "+ modelo.darNumeroVertices());
				view.printMessage("  -El total de arcos es: "+ modelo.darNumeroArcos());
				view.printMessage("");
				view.printMessage("");
				
				break;


			case 2:			
				modelo.crearJson();
				view.printMessage("");
				view.printMessage("EL GRAFO SE SALVO SATISFACTORIAMNETE EN UN ARCHIVO JSON");
				view.printMessage("  -Se guardaron "+ modelo.darNumeroVertices()+" vertices.");
				view.printMessage("  -Se guardaron "+ modelo.darNumeroArcos()+" arcos.");
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:			
				modelo.cargarInfo();
				view.printMessage("");
				view.printMessage("EL GRAFO SE CARGO SATISFACTORIAMENTE DESDE EL ARCHIVO JSON");
				view.printMessage("  -El total de vertices es: "+ modelo.darNumeroVertices());
				view.printMessage("  -El total de arcos es: "+ modelo.darNumeroArcos());
				view.printMessage("");
				view.printMessage("");
				break;
				
			case 4:		
				view.printMessage("");
				try
				{
					modelo.escribir().hacerMallaVial(modelo.darLimitado());
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				view.printMessage("SE GENERO EL MAPA SATISFACTORIAMENTE");
				view.printMessage("  -El total de vertices dibujados son: "+ modelo.darLimitado().V());
				view.printMessage("  -El total de arcos debujados son: "+ modelo.darLimitado().E());
				view.printMessage("");
				view.printMessage("NOTA: El mapa se encuentra en la carpeta data, y su nombre es mapaMallaVial.html");
				view.printMessage("");
				view.printMessage("");
			
				break;

			case 5:			
				view.printMessage("");
				modelo.cargarEstaciones();
				view.printMessage("LAS ESTACIONES DE POLICIA FUERON CARGADAS SATISFACTORIAMENTE");
				view.printMessage("  -La cantidad de estaciones cargadas son: "+ modelo.darEstaciones().getSize());
				view.printMessage("  -De cada uno se cargaron los siguientes datos:");
				view.printMessage("    +ID");
				view.printMessage("    +NOMBRE");
				view.printMessage("    +DIRECCION");
				view.printMessage("    +LATITUD");
				view.printMessage("    +LONGITUD");
				view.printMessage("");
				view.printMessage("");
				break;
				
				
			case 6:		
				view.printMessage("");
				int numeroESTACIONESLimi = modelo.darEstacionesDelimitadas().getSize();
				try
				{
					modelo.escribir().hacerMallaVialConEstaciones(modelo.darLimitado(), modelo.darEstacionesDelimitadas());
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				view.printMessage("SE GENERO EL MAPA SATISFACTORIAMENTE");
				view.printMessage("  -El total de vertices dibujados son: "+ modelo.darLimitado().V());
				view.printMessage("  -El total de arcos debujados son: "+ modelo.darLimitado().E());
				view.printMessage("  -La cantidad de estaciones dibujadas son: "+ numeroESTACIONESLimi);
				view.printMessage("");
				view.printMessage("NOTA: El mapa se encuentra en la carpeta data, y su nombre es mapaEstaciones.html");
				view.printMessage("");
				view.printMessage("");
				
				break;
				
			case 0:			
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}
}
