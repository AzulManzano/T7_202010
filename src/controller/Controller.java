package controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.Comparendo;
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

	public void run() throws ParseException 
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

				view.printMessage("El total de comparendos cargados son: "+modelo.darelemnetoscargado());
				view.printMessage("");
				view.printMessage("La informacion del primer comparendo es:");
				view.printMessage(" -"+modelo.darPrimero().darInformacion());
				view.printMessage("");
				view.printMessage("La informacion del ultimo comparendo es:");
				view.printMessage(" -"+modelo.darUltimo().darInformacion());
				view.printMessage("");
				view.printMessage("");
				view.printMessage("El numeor de duplas en la LinearProbing es: "+modelo.darTamanoLinearProbing() );
				view.printMessage("El numeor de duplas en la SeparateChaining es: "+ modelo.darTamanoSequentialSearch());
				view.printMessage("");
				view.printMessage("");
				view.printMessage("El tamaño inicial de los arreglos de la LinearProbing son: 5");
				view.printMessage("El tamaño inicial del arreglo de la SeparateChaining es: 5");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("El tamaño finla de los arreglos de la LinearProbing son: "+ modelo.darLinearProbing().darCapacidad());
				view.printMessage("El tamaño final del arreglo de la SeparateChaining es: "+modelo.darSeparateChaining().darCapacidad());
				view.printMessage("");
				view.printMessage("");
				DecimalFormat df = new DecimalFormat("#.00");
				view.printMessage("El factor de carga de la LinearProbing es: 0"+df.format((double)modelo.darLinearProbing().size()/modelo.darLinearProbing().darCapacidad()));
				view.printMessage("El factor de carga de la SeparateChaining es: 0"+df.format((double)modelo.darSeparateChaining().size()/modelo.darSeparateChaining().darCapacidad()));
				view.printMessage("");
				view.printMessage("");
				view.printMessage("El numero de rehashes que tuvo que hacer la LinearProbing es: "+modelo.darLinearProbing().darNumeroRehashes());
				view.printMessage("El numero de rehashes que tuvo que hacer la SeparateChaining es: "+modelo.darSeparateChaining().darNumeroRehashes());

				view.printMessage("");
				view.printMessage("");
				break;

			case 2:
				view.printMessage("");
				view.printMessage("Ingrese la fecha que desea consultar (En el formato año-mes-dia): ");
				SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
				String s = lector.next();
				Date FECHA_HORA = parser.parse(s);
				view.printMenuLista();
				String s1 = lector.next();
				String Tipo_veiculo = modelo.tranformar(s1);
				view.printMessage("Ingrese la infraccion en mayuscula, que desea consultar:");
				String infraccion = lector.next();
				view.printMessage("");
				MaxHeapCP<Comparendo> lista1 = modelo.requerimiento1(FECHA_HORA, Tipo_veiculo, infraccion);
				int numero =lista1.darNumElementos();
				if(lista1.darNumElementos() == 0)
				{
					view.printMessage("No hay comparendos con esa llave.");
				}
				else
				{
					view.printMessage("La informacion de los comparendos con la lave dada son: ");
					for(int i = 0; i<numero; i++)
					{
						view.printMessage(lista1.sacarMax().darDatos());
					}
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 3:
				view.printMessage("");
				view.printMessage("Ingrese la fecha que desea consultar (En el formato año-mes-dia): ");
				SimpleDateFormat parser1=new SimpleDateFormat("yyyy-MM-dd");
				String s11 = lector.next();
				Date FECHA_HORA1 = parser1.parse(s11);
				view.printMenuLista();
				String s12 = lector.next();
				String Tipo_veiculo1 = modelo.tranformar(s12);
				view.printMessage("Ingrese la infraccion en mayuscula, que desea consultar:");
				String infraccion1 = lector.next();
				view.printMessage("");
				MaxHeapCP<Comparendo> lista2 = modelo.requerimiento2(FECHA_HORA1, Tipo_veiculo1, infraccion1);
				int numero2 =lista2.darNumElementos();
				if(numero2 == 0)
				{
					view.printMessage("No hay comparendos con esa llave.");
				}
				else
				{
					view.printMessage("La informacion de los comparendos con la lave dada son: ");
					for(int i = 0; i<numero2; i++)
					{
						view.printMessage(lista2.sacarMax().darDatos());
					}
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 4:
				view.printMessage("");

				modelo.rellenarColas();
				int tam1 = modelo.darCola1().getSize();
				int tam2 = modelo.darCola2().getSize();
				
				long startTime = System.currentTimeMillis();
				modelo.tiempoDeGetEnLinearProbing();
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime; 
				
				long startTime1 = System.currentTimeMillis();
				modelo.tiempoDeGetEnSeparateChaining();
				long endTime1 = System.currentTimeMillis();
				long duration1 = endTime1 - startTime1; 
				
				view.printMessage("El tiempo de realizar "+tam1+ " get(…) en una LinearProbing es de: " +duration +" milisegudos");
				view.printMessage("El tiempo de realizar "+tam2+ " get(…) en una SeparateChaining es de: " +duration1+" milisegudos");
				view.printMessage("");
				
				view.printMessage("Por este resultado y resultados anteriores se puede concluir la siguiete informacion:");
				view.printMessage("---------------------------------------------------------------------------------");
				view.printMessage("|                               | Tabla de Hash Linear | Tabla de Hash Separate |");
				view.printMessage("|                               |        Probing       |        Chaining        |");
				view.printMessage("|-------------------------------|----------------------|------------------------|");
				view.printMessage("|Tiempo mínimo de get(…)        |          7           |           5            |");
				view.printMessage("|-------------------------------|----------------------|------------------------|");
				view.printMessage("|Tiempo promedio de get(…)      |          10          |           8            |");
				view.printMessage("|-------------------------------|----------------------|------------------------|");
				view.printMessage("|Tiempo máximo de get(...)      |          14          |           11           |");
				view.printMessage("---------------------------------------------------------------------------------");
				view.printMessage(" ");
				break;

			case 5:			
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
