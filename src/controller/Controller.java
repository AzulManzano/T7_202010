package controller;

import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
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
				modelo.cargarDatos();
			}
			switch(option)
			{
			case 1:
				view.printMessage("Los datos fueron leidos satisfactoriamente");
				view.printMessage("  La cantidad de comparendos que fueron leidos es: " + modelo.darTamCola());
				view.printMessage("");
				view.printMessage("La informacion del primer comparendo es:");
				view.printMessage(modelo.darCola().getElement().darInformacion());
				view.printMessage("");
				view.printMessage("La informacion del ultimo comparendo es:");
				view.printMessage(modelo.darCola().getElement().darInformacion());
				view.printMessage("");
				view.printMessage("");
				break;

			case 2:
				view.printMessage("Se ha hecho la copia");
				view.printMessage("El numero de comparendos copiados es: "+ modelo.copiarComparendos().darTamano());
			case 3:
				view.printMessage("Se ha ordenado");
				// Copiar los comparendos originales en un arreglo de objetos Comparables – Requerimiento 1
				 Comparable copia_Comparendos [ ] = modelo.copiarComparendos();
				 long startTime = System.currentTimeMillis(); // medición tiempo actual
				 // solucion Requerimiento 2, 3 o 4
				 modelo.xxxxxSort( copia_Comparendos );
				 long endTime = System.currentTimeMillis(); // medición tiempo actual
				 long duration = endTime - startTime; // duracion de ejecucion del algoritmo
				 view.printMensage("Tiempo de ordenamiento: " + duration + " milisegundos");
				 // mostrar los resultados del algoritmo xxxxxSort que quedaron en el arreglo
				 // copia_Comparendos: los 10 primeros y los 10 últimos comparendos resultantes

			case 4:			
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
