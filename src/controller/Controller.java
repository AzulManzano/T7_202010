package controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.SeparateChaining;
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

				view.printMessage("El total de comparendos cargados son: "+modelo.darTotalComparendos());
				view.printMessage("");
				view.printMessage("-El comparendo con el mayor OBJECTID encontrado fue:");
				view.printMessage("  "+ modelo.darListaDeCarga().sacarMax().darInformacionDeCarga());


				view.printMessage("");
				view.printMessage("");
				break;

			case 2:
				view.printMessage("");
				view.printMessage("Ingrese la cantidad de comparendo que desea ver:");
				int corredor = lector.nextInt();
				MaxHeapCP<Comparendo> elemeto1A = modelo.requerimiento1A();

				view.printMessage("La informacion de los "+corredor+" comparendos es la siguiente:");
				for(int i =0; i<corredor;i++)
				{
					view.printMessage(elemeto1A.sacarMax().darInformacion1A());
				}

				view.printMessage("");
				view.printMessage("");
				break;
			case 3:
				view.printMessage("");
				view.printMessage("BUSCAR LOS COMPARENDOS POR MES Y DIA DE LA SEMANA");
				view.printMessage("-Ingresa el número del mes(1-12):");
				int mes = lector.nextInt()-1;
				view.printMessage("-Ingresa el el día de la semana(L,M,I,J,V,S,D):");
				String letrDia = lector.next();
				int dia = modelo.indicarDia(letrDia);
				view.printMessage("La informacion de los comparendos en los dias "+ modelo.indicarDia2(letrDia)+" del mes de "+modelo.indicarMes(mes)+" es la siguiente:");

				MaxHeapCP<Comparendo> elemeto2A = modelo.requerimiento2A(mes,dia);

				for(int i =0; i<20;i++)
				{
					view.printMessage(elemeto2A.sacarMax().darInformacion1A());
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 4:

				break;

			case 5:
				view.printMessage("");
				view.printMessage("Ingrese la cantidad de comparendo que desea ver:");
				int corredor1 = lector.nextInt();
				MaxHeapCP<Comparendo> elemeto1B = modelo.requerimiento1B();

				view.printMessage("La informacion de los "+corredor1+" comparendos es la siguiente:");
				for(int i =0; i<corredor1;i++)
				{
					view.printMessage(elemeto1B.sacarMax().darInformacion1B());
				}

				view.printMessage("");
				view.printMessage("");
				break;

			case 6:

				view.printMessage("");
				view.printMessage("BUSCAR LOS COMPARENDOS POR MEDIO DE DETECCIÓN, CLASE DE VEHÍCULO, TIPO DE SERVICIO Y LOCALIDAD.");
				view.printMessage("");
				view.printMenuMedioDeteccion();
				String medioN = lector.next();
				String medio = modelo.tranformarMedioDeteccion(medioN);

				view.printMenuTipoVeiculo();
				String TipoVn = lector.next();
				String tipoV = modelo.tranformarTipoVeculo(TipoVn);

				view.printMenuTipoServicio();
				String tipoSN = lector.next();
				String tipoS = modelo.tranformarTipoServicio(tipoSN);

				view.printMenuLocalidad();
				String locN = lector.next();
				String loc = modelo.transformarLocalidad(locN);

				MaxHeapCP<Comparendo> elemeto2B = modelo.requerimiento2B(medio, tipoV, tipoS,loc);

				if(elemeto2B != null)
				{
					view.printMessage("La informacion de los comparendos, que cumplen esas espesificaciones, es la siguiente: ");



					for(int i =0; i<20;i++)
					{
						view.printMessage(elemeto2B.sacarMax().darInformacion2B());
					}
				}
				else
				{
					view.printMessage("No se encontra ningun comparendo con las indicaciones dadas.");
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 7:

				break;

			case 8:

				break;

			case 9:

				break;

			case 10:

				break;

			case 11:			
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
