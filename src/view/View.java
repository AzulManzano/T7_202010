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
		System.out.println("1. Cargar grafo");
		System.out.println("2. Salvar grafo en archivo Json");
		System.out.println("3. Cargar grafo desde archivo Json");
		System.out.println("4. Dibujar malla vial de Bogota");
		System.out.println("5. Cargar estaciones de policia");
		System.out.println("6. Dibujar malla vial de Bogota con estaciones de policia");
		System.out.println("0. SALIR");
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
}
