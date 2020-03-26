package view;

import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	public void bienvenida()
	{
		System.out.println(".---.  _                                _    .-. \n"     
				+": .; ::_;                              :_;   : :      \n"
				+":   .'.-. .--. ,-.,-..-..-. .--. ,-.,-..-. .-' : .--. \n"
				+": .; :: :' '_.': ,. :: `; :' '_.': ,. :: :' .; :' .; :\n"
				+":___.':_;`.__.':_;:_;`.__.'`.__.':_;:_;:_;`.__.'`.__.'\n");
		System.out.println("");
	}

	public void printMenu()
	{
		System.out.println("MENU DEL USUARIO");
		System.out.println("1. Cargar datos");
		System.out.println("2. Procesar la LinearProbing");
		System.out.println("3. Procesar la SeparateChaining");
		System.out.println("4. Pruebas de desempeño. ");
		System.out.println("5. Salir");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar
	}
	
	public void printMenuLista()
	{
		System.out.println("INDIQUE QUE TIPO DE VEICULO QUIERE CONSULTAR:");
		System.out.println(" 1.Automovil");
		System.out.println(" 2.Bicicleta");
		System.out.println(" 3.Bus");
		System.out.println(" 4.Buseta");
		System.out.println(" 5.Camioneta");
		System.out.println(" 6.Campero");
		System.out.println(" 7.Motocicleta");

	}
}
