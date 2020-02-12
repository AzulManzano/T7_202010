package controller;

import java.util.Scanner;

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
				view.printMessage("  La cantidad de comparendos que fueron leidos es: " + modelo.darTamCola() + "." );
				view.printMessage("");
				view.printMessage("-La información del primer comparendo de la cola es:"); 
				view.printMessage("  "+ modelo.darComparendoQueue());
				view.printMessage("");
				view.printMessage("-La información del primer comparendo de la pila es:");
				view.printMessage("  " + modelo.darComparendoStack());
				view.printMessage("");
				view.printMessage("");
				break;

			case 2:			
				if(modelo.darIniciacion() == 0)
				{
					view.printMessage("--------- \n Debe leer los datos antes. !! \n---------");
				}
				else 
				{
					
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:
				if(modelo.darIniciacion() == 0)
				{
					view.printMessage("--------- \n Debe leer los datos antes. !! \n---------");
				}
				else 
				{
					
				}
				view.printMessage("");
				view.printMessage("");
				break;

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
