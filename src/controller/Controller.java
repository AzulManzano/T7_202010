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
				view.printMessage("  "+ modelo.darListaDeCarga().darMax().darInformacionDeCarga());

				
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

				int tam2A = elemeto2A.darNumElementos();

				if(tam2A>=20)
				{
					for(int i =0; i<20;i++)
					{
						view.printMessage(elemeto2A.sacarMax().darInformacion1A());
					}
				}
				else
				{
					for(int i =0; i<tam2A;i++)
					{
						view.printMessage(elemeto2A.sacarMax().darInformacion1A());
					}
				}
				view.printMessage("");
				view.printMessage("");
				break;

			case 4:
				view.printMessage("");
				view.printMessage("BUSCAR LOS COMPARENDOS QUE TIENEN UNA FECHA-HORA EN UN RANGO Y QUE SON DE UNA LOCALIDAD DADA.");
				view.printMessage("Ingrese la fecha que servira como limite inferior en el seguiente formato (YYYY/MM/DD-HH:MM:ss)");
				String fechaInicio = lector.next();
				view.printMessage("Ingrese la fecha que servira como limite superior en el seguiente formato (YYYY/MM/DD-HH:MM:ss)");
				String fechaFinal = lector.next();
				view.printMenuLocalidad();
				String loca = lector.next();
				view.printMessage(" - La infrmacion de loc comparendos que cumplen esta propiedad es:");

				String localidad = modelo.transformarLocalidad(loca);



				MaxHeapCP<Comparendo> elemeto3A = modelo.requerimiento3A(fechaInicio, fechaFinal, localidad);

				int tam = elemeto3A.darNumElementos();
				if(tam != 0)
				{
					if(tam >=20)
					{

						for(int i = 0; i<20;i++)
						{
							view.printMessage(elemeto3A.sacarMax().darInformacion3A());
						}
					}
					else 
					{
						for(int i = 0; i<tam;i++)
						{
							view.printMessage(elemeto3A.sacarMax().darInformacion3A());
						}
					}
				}
				else
					view.printMessage("No hay comparendos que cumplan estos requerimientos");
				view.printMessage("");
				view.printMessage("");
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
					int tamanito = elemeto2B.darNumElementos();
					view.printMessage("La informacion de los comparendos, que cumplen esas espesificaciones, es la siguiente: ");

					if(tamanito>=20)
					{
						for(int i =0; i<20;i++)
						{
							view.printMessage(elemeto2B.sacarMax().darInformacion2B());
						}
					}
					else
					{
						for(int i =0; i<tamanito;i++)
						{
							view.printMessage(elemeto2B.sacarMax().darInformacion2B());
						}
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
				view.printMessage("BUSCAR LOS COMPARENDOS QUE TIENEN UNA LATITUD EN UN RANGO DADO Y QUE INVOLUCRARON UN TIPO DE VEHÍCULO PARTICULAR");			
				view.printMessage("Ingrese la latitud que servira como limite inferir ");
				Double latinai = lector.nextDouble();
				view.printMessage("Ingrese la latitud que servira como limite superior ");
				Double latFinal = lector.nextDouble();
				view.printMenuTipoVeiculo();
				String v = lector.next();
				view.printMessage(" - La informacion de los comparendos que cumplen esta propiedad es:");

				String veic = modelo.tranformarTipoVeculo(v);

				MaxHeapCP<Comparendo> elemeto3B = modelo.requerimiento3B(latinai, latFinal, veic);

				int tam3B = elemeto3B.darNumElementos();
				if(tam3B != 0)
				{
					if(tam3B<20)
					{

						for(int i = 0; i<tam3B;i++)
						{
							view.printMessage(elemeto3B.sacarMax().darInformacion3B());
						}
					}
					else 
					{
						for(int i = 0; i<20;i++)
						{
							view.printMessage(elemeto3B.sacarMax().darInformacion3B());
						}
					}

				}
				else
					view.printMessage("No hay comparendos que cumplan estos requerimientos");
				view.printMessage("");
				view.printMessage("");
				break;

			case 8:
				view.printMessage("VISUALIZAR DATOS EN UNA TABLA ASCII");
				view.printMessage("Ingrese el numero de dias que quiere ver por rango:");
				int tamanoArreglo = lector.nextInt();
				view.printMessage("");
				view.printMessage("Rango de fechas        |  Comparendos durante el año");
				view.printMessage("----------------------------------------------------");

				boolean termine = false;
				int i = tamanoArreglo;
				int j = 1;
				while(termine == false )
				{
					if(i>=365)
					{
						i=365;
						termine = true;
					}
					view.printMessage(modelo.requerimiento1C(j, i, tamanoArreglo*100));
					i+= tamanoArreglo;
					j+= tamanoArreglo;


				}

				view.printMessage("");
				view.printMessage("Cada * representa "+ tamanoArreglo*100 +" Comparendos");
				view.printMessage("");
				view.printMessage("");
				break;

			case 9:
				view.printMessage("EL COSTO DE LOS TIEMPOS DE ESPERA HOY EN DÍA (COLA)");
				view.printMessage("");
				view.printMessage("Fecha       | Comparendos procesados          ***");
				view.printMessage("            | Comparendos que están en espera ###");
				view.printMessage("-------------------------------------------------");
				for(int k = 1; k<366;k++)
				{
					view.printMessage(modelo.requerimiento2C(k, k+1, 750));
				}
				view.printMessage("");
				view.printMessage("Cada * y # representa "+750 +" Comparendos");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("INFORMACION ADICIONAL");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("Costo total que generan las    |  $"+ modelo.darCostoCola());
				view.printMessage("  penalizaciones en 2018       |");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("Número de días en promedio que |  "+modelo.promedioDeDias2C());
				view.printMessage("  debe esperar un comparendo   |");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("TABLA DE INFORMACION");
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage(" Costo diario del |  Tiempo mínimo   | Tiempo promedio  |  Tiempo máximo   |");
				view.printMessage("    comparendo    | de espera (días) | de espera (días) |  deespera (días) |");
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage(modelo.tablaInformacion());
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage("");
				view.printMessage("");
				break;

			case 10:
				view.printMessage("EL COSTO DE LOS TIEMPOS DE ESPERA USANDO EL NUEVO SISTEMA");
				view.printMessage("");
				
				view.printMessage("Fecha       | Comparendos procesados          ***");
				view.printMessage("            | Comparendos que están en espera ###");
				view.printMessage("-------------------------------------------------");
				for(int k = 1; k<366;k++)
				{
					view.printMessage(modelo.requerimiento3C(k, k+1, 750));
				}
				view.printMessage("");
				view.printMessage("Cada * y # representa "+750 +" Comparendos");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("INFORMACION ADICIONAL");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("Costo total que generan las    |  $"+modelo.darCostoMaxHeap());
				view.printMessage("  penalizaciones en 2018       |");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("Número de días en promedio que |  "+modelo.promedioDeDias3C());
				view.printMessage("  debe esperar un comparendo   |");
				view.printMessage("----------------------------------------------------------");
				view.printMessage("");
				view.printMessage("");
				view.printMessage("TABLA DE INFORMACION");
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage(" Costo diario del |  Tiempo mínimo   | Tiempo promedio  |  Tiempo máximo   |");
				view.printMessage("    comparendo    | de espera (días) | de espera (días) |  deespera (días) |");
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage(modelo.tablaNuevoSistema());
				view.printMessage("----------------------------------------------------------------------------");
				view.printMessage("");
				view.printMessage("");
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
