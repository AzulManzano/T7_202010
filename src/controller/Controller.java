package controller;

import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
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
				view.printMessage("");
				view.printMessage("Ingrese el tamaño de la muestra con la cual quiere trabajar:");
				int tamanoMuestra = lector.nextInt();
				modelo.hacerMuestra(tamanoMuestra);
				view.printMessage("");
				
				long startTime = System.currentTimeMillis();
				modelo.cargarMaxColaCP();
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime; 

				long startTime1 = System.currentTimeMillis();
				modelo.cargarMaxHeapCP();
				long endTime1 = System.currentTimeMillis();
				long duration1 = endTime1 - startTime1; 
				
				view.printMessage("El total de comparandos cargados es de "+modelo.darTamnoListaLarga()+" y el tamaño de la muestra con la que se va trabajar es de "+modelo.darTamanoMuestra()+" - "+ modelo.darTmanoMaxCola()+" - "+modelo.darTamanoMaxHeap());

				view.printMessage("  -El tiempo de agragr en la MaxColaCP es de: "+duration+" milisegundos.");
				view.printMessage("  -El tiempo de agragr en la MaxHeapCP es de: "+duration1+" milisegundos.");
				view.printMessage("");
				view.printMessage("");
				break;

			case 2:
				view.printMessage("");
				view.printMenuLista();
				String requisito1A = lector.next();
				String[] parts1 = requisito1A.split(",");
				String[] listaTipos1 = modelo.tranformar(parts1);

				view.printMessage("");
				view.printMessage("Ingrese el numero de comparendos que desea ver:");
				int requisito1B =lector.nextInt();

				long startTime0 = System.currentTimeMillis();
				MaxColaCP<Comparendo> corredor1  = modelo.requerimienrto1(listaTipos1,requisito1B);
				long endTime0 = System.currentTimeMillis();
				long duration0 = endTime0 - startTime0; 

				int tamanoCola = corredor1.darNumElementos();
				if(tamanoCola != 0)
				{
					view.printMessage("El tiempo que tardo fue de: "+duration0+" milisegundos. Y la informacion de los "+tamanoCola+" es:");
					for(int i = 0; i<tamanoCola;i++)
					{
						view.printMessage(corredor1.sacarMax().darInformacion());
					}
					view.printMessage("");
					view.printMessage("El tiempo que tardo fue de: "+duration0+" milisegundos. y fueron encontrados "+tamanoCola+" comparendos.");
				}
				else
				{
					view.printMessage("El tiempo que tardo fue de: "+duration0+" milisegundos.Y no se encontro ningun comparendo con las espesificaciones dadas. ");
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:
				view.printMessage("");
				view.printMenuLista();
				String requisito2A = lector.next();
				String[] parts2 = requisito2A.split(",");
				String[] listaTipos2 = modelo.tranformar(parts2);

				view.printMessage("");
				view.printMessage("Ingrese el numero de comparendos que desea ver:");
				int requisito2B =lector.nextInt();

				long startTime10 = System.currentTimeMillis();
				MaxHeapCP<Comparendo> corredor2 = modelo.requerimienrto2(listaTipos2,requisito2B);
				long endTime10 = System.currentTimeMillis();
				long duration10 = endTime10 - startTime10; 

				int tamanoHeap = corredor2.darNumElementos();
				if(tamanoHeap != 0)
				{
					view.printMessage("El tiempo que tardo fue de: "+duration10+" milisegundos.Y la informacion de los "+tamanoHeap+" es:");
					for(int i = 0; i<tamanoHeap;i++)
					{
						view.printMessage(corredor2.sacarMax().darInformacion());
					}
					view.printMessage("");
					view.printMessage("El tiempo que tardo fue de: "+duration10+" milisegundos. Y fueron encontrados "+tamanoHeap+" comparendos.");
				}
				else
				{
					view.printMessage("El tiempo que tardo fue de: "+duration10+" milisegundos.Y no se encontro ningun comparendo con las espesificaciones dadas. ");
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
