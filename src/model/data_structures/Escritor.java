package model.data_structures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor 
{
	public Escritor()
	{

	}

	public void hacerMallaVial(Graph<Integer, Bogota_Vertice> grafo) throws IOException 
	{
		String ruta = "data/mapaMallaVial.html";
		File archivo = new File(ruta);
		BufferedWriter bw;

		bw = new BufferedWriter(new FileWriter(archivo));

		bw.write("<!DOCTYPE html>\n");
		bw.write("<html> \n");
		bw.write("<head>\n");
		bw.write("  <meta name=\"viewport\" content=\"initial-scale=1.0,\n");
		bw.write("user-scalable=no\">\n" );
		bw.write("  <meta charset=\"utf-8\">\n"); 
		bw.write("  <title>Simple Polylines</title>\n"); 
		bw.write("  <style>\n"); 
		bw.write("    #map {\n"); 
		bw.write("      height: 100%;\n"); 
		bw.write("    }\n"); 
		bw.write("    html,\n"); 
		bw.write("    body {\n"); 
		bw.write("      height: 100%;\n"); 
		bw.write("      margin: 0;\n"); 
		bw.write("      padding: 0;\n"); 
		bw.write("    }\n"); 
		bw.write("  </style>\n"); 
		bw.write("</head>\n"); 
		bw.write("<body>\n"); 
		bw.write("  <div id=\"map\"></div>\n"); 
		bw.write("  <script>\n"); 
		bw.write("    function initMap() {\n"); 
		bw.write("      var map = new google.maps.Map\n"); 
		bw.write("(document.getElementById('map'), {\n"); 
		bw.write("        zoom: 15,\n"); 
		bw.write("        center: {\n"); 
		bw.write("          lat: 4.609537,\n"); 
		bw.write("          lng: -74.078715\n"); 
		bw.write("        },\n"); 
		bw.write("        mapTypeId: 'roadmap'\n"); 
		bw.write("      });\n"); 
		bw.write("      var line;\n"); 
		bw.write("      var path;\n"); 
		//--------------------------
		//Encuadre 
		//--------------------------

		bw.write("      line = [{\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("       },\n"); 
		bw.write("       {\n"); 
		bw.write("          lat: 4.621360,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("        },\n"); 
		bw.write("        {\n"); 
		bw.write("          lat: 4.621360,\n"); 
		bw.write("          lng:  -74.062707\n"); 
		bw.write("        },\n"); 
		bw.write("	      {\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:   -74.062707\n"); 
		bw.write("        },\n"); 
		bw.write("     	  {\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("        }\n"); 
		bw.write("      ];\n"); 
		bw.write("      path = new google.maps.Polyline({\n"); 
		bw.write("        path: line,\n"); 
		bw.write("        strokeColor: '#FF0000',\n"); 
		bw.write("        strokeWeight: 1\n"); 
		bw.write("      });\n"); 
		bw.write("      path.setMap(map);\n"); 
		//--------------------------
		//Vertices
		//--------------------------

		int tam = grafo.V();
		Queue<Integer> llaves = grafo.vectores();
		for(int i = 0; i<tam; i++)
		{
			Bogota_Vertice elemneto = grafo.getInfoVertex(llaves.dequeue());
			bw.write("      path = new google.maps.Circle({\n"); 
			bw.write("		center: {\n"); 
			bw.write("          lat: "+elemneto.darLatitud()+",\n"); 
			bw.write("          lng: "+elemneto.darLongitud()+"\n"); 
			bw.write("        },\n"); 
			bw.write("	      radius:10,\n"); 
			bw.write("        strokeColor: '#FF0000',\n"); 
			bw.write("        strokeWeight: 1\n"); 
			bw.write("      });\n"); 
			bw.write("      path.setMap(map);\n"); 

		}

		//--------------------------
		//Arcos
		//--------------------------

		int numAr = grafo.E();
		Queue<Integer> arcos = grafo.vectores();
		for(int i = 0; i<tam; i++)
		{
			Integer origen = arcos.dequeue();
			Queue<Integer> llavesArcos = grafo.adj(origen);
			while(llavesArcos.isEmpty() == false)
			{
				Integer destino = llavesArcos.dequeue();

				if(origen < destino)
				{
					Bogota_Vertice verOrigen = grafo.getInfoVertex(origen);
					Bogota_Vertice verDestino = grafo.getInfoVertex(destino);
					bw.write("      line = [{\n"); 
					bw.write("          lat: "+verOrigen.darLatitud()+",\n"); 
					bw.write("          lng:  "+verOrigen.darLongitud()+",\n"); 
					bw.write("       },\n"); 
					bw.write("       {\n"); 
					bw.write("          lat: "+verDestino.darLatitud()+",\n"); 
					bw.write("          lng:  "+verDestino.darLongitud()+"\n"); 
					bw.write("        }\n"); 
					bw.write("      ];\n"); 
					bw.write("      path = new google.maps.Polyline({\n"); 
					bw.write("        path: line,\n"); 
					bw.write("        strokeColor: '#FF0000',\n"); 
					bw.write("        strokeWeight: 1\n"); 
					bw.write("      });\n"); 
					bw.write("      path.setMap(map);\n");
				}
			}
		}

		//--------------------------------------------------
		bw.write("    }\n");
		bw.write("  </script>\n"); 
		bw.write("  <script async defer \n"); 
		bw.write("src=\"https://maps.googleapis.com/maps/api/js?\n"); 
		bw.write("key=&callback=initMap\">\n"); 
		bw.write("  </script>\n"); 
		bw.write("</body>\n"); 
		bw.write("</html>\n"); 


		bw.close();
	}

	public void hacerMallaVialConEstaciones(Graph<Integer, Bogota_Vertice> grafo, Queue<EstacionPolicia> estaciones) throws IOException 
	{
		String ruta = "data/mapaEstaciones.html";
		File archivo = new File(ruta);
		BufferedWriter bw;

		bw = new BufferedWriter(new FileWriter(archivo));

		bw.write("<!DOCTYPE html>\n");
		bw.write("<html> \n");
		bw.write("<head>\n");
		bw.write("  <meta name=\"viewport\" content=\"initial-scale=1.0,\n");
		bw.write("user-scalable=no\">\n" );
		bw.write("  <meta charset=\"utf-8\">\n"); 
		bw.write("  <title>Simple Polylines</title>\n"); 
		bw.write("  <style>\n"); 
		bw.write("    #map {\n"); 
		bw.write("      height: 100%;\n"); 
		bw.write("    }\n"); 
		bw.write("    html,\n"); 
		bw.write("    body {\n"); 
		bw.write("      height: 100%;\n"); 
		bw.write("      margin: 0;\n"); 
		bw.write("      padding: 0;\n"); 
		bw.write("    }\n"); 
		bw.write("  </style>\n"); 
		bw.write("</head>\n"); 
		bw.write("<body>\n"); 
		bw.write("  <div id=\"map\"></div>\n"); 
		bw.write("  <script>\n"); 
		bw.write("    function initMap() {\n"); 
		bw.write("      var map = new google.maps.Map\n"); 
		bw.write("(document.getElementById('map'), {\n"); 
		bw.write("        zoom: 15,\n"); 
		bw.write("        center: {\n"); 
		bw.write("          lat: 4.609537,\n"); 
		bw.write("          lng: -74.078715\n"); 
		bw.write("        },\n"); 
		bw.write("        mapTypeId: 'roadmap'\n"); 
		bw.write("      });\n"); 
		bw.write("      var line;\n"); 
		bw.write("      var path;\n"); 
		//--------------------------
		//Estaciones 
		//--------------------------
		int numEstaciones = estaciones.getSize();
		for(int i = 0; i<numEstaciones; i++)
		{
			EstacionPolicia este = estaciones.dequeue();
			bw.write("      var marker = new google.maps.Marker({\n");
			bw.write("      position: {\n");
			bw.write("          lat: "+este.darLatitud()+",\n");
			bw.write("          lng: "+este.darLongitud()+"\n");
			bw.write("        },\n");
			bw.write("      title:\""+este.darNombre()+"\"\n");
			bw.write("      });\n");
			bw.write("      marker.setMap(map);\n");
		}

		//--------------------------
		//Encuadre 
		//--------------------------

		bw.write("      line = [{\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("       },\n"); 
		bw.write("       {\n"); 
		bw.write("          lat: 4.621360,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("        },\n"); 
		bw.write("        {\n"); 
		bw.write("          lat: 4.621360,\n"); 
		bw.write("          lng:  -74.062707\n"); 
		bw.write("        },\n"); 
		bw.write("	      {\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:   -74.062707\n"); 
		bw.write("        },\n"); 
		bw.write("     	  {\n"); 
		bw.write("          lat: 4.597714,\n"); 
		bw.write("          lng:  -74.094723\n"); 
		bw.write("        }\n"); 
		bw.write("      ];\n"); 
		bw.write("      path = new google.maps.Polyline({\n"); 
		bw.write("        path: line,\n"); 
		bw.write("        strokeColor: '#FF0000',\n"); 
		bw.write("        strokeWeight: 1\n"); 
		bw.write("      });\n"); 
		bw.write("      path.setMap(map);\n"); 
		//--------------------------
		//Vertices
		//--------------------------

		int tam = grafo.V();
		Queue<Integer> llaves = grafo.vectores();
		for(int i = 0; i<tam; i++)
		{
			Bogota_Vertice elemneto = grafo.getInfoVertex(llaves.dequeue());
			bw.write("      path = new google.maps.Circle({\n"); 
			bw.write("		center: {\n"); 
			bw.write("          lat: "+elemneto.darLatitud()+",\n"); 
			bw.write("          lng: "+elemneto.darLongitud()+"\n"); 
			bw.write("        },\n"); 
			bw.write("	      radius:10,\n"); 
			bw.write("        strokeColor: '#FF0000',\n"); 
			bw.write("        strokeWeight: 1\n"); 
			bw.write("      });\n"); 
			bw.write("      path.setMap(map);\n"); 

		}

		//--------------------------
		//Arcos
		//--------------------------

		int numAr = grafo.E();
		Queue<Integer> arcos = grafo.vectores();
		for(int i = 0; i<tam; i++)
		{
			Integer origen = arcos.dequeue();
			Queue<Integer> llavesArcos = grafo.adj(origen);
			while(llavesArcos.isEmpty() == false)
			{
				Integer destino = llavesArcos.dequeue();

				if(origen < destino)
				{
					Bogota_Vertice verOrigen = grafo.getInfoVertex(origen);
					Bogota_Vertice verDestino = grafo.getInfoVertex(destino);
					bw.write("      line = [{\n"); 
					bw.write("          lat: "+verOrigen.darLatitud()+",\n"); 
					bw.write("          lng:  "+verOrigen.darLongitud()+",\n"); 
					bw.write("       },\n"); 
					bw.write("       {\n"); 
					bw.write("          lat: "+verDestino.darLatitud()+",\n"); 
					bw.write("          lng:  "+verDestino.darLongitud()+"\n"); 
					bw.write("        }\n"); 
					bw.write("      ];\n"); 
					bw.write("      path = new google.maps.Polyline({\n"); 
					bw.write("        path: line,\n"); 
					bw.write("        strokeColor: '#FF0000',\n"); 
					bw.write("        strokeWeight: 1\n"); 
					bw.write("      });\n"); 
					bw.write("      path.setMap(map);\n");
				}
			}
		}

		//--------------------------------------------------
		bw.write("    }\n");
		bw.write("  </script>\n"); 
		bw.write("  <script async defer \n"); 
		bw.write("src=\"https://maps.googleapis.com/maps/api/js?\n"); 
		bw.write("key=&callback=initMap\">\n"); 
		bw.write("  </script>\n"); 
		bw.write("</body>\n"); 
		bw.write("</html>\n"); 


		bw.close();
	}
}
