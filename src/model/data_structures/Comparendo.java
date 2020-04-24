package model.data_structures;


import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	private int objectId;
	private Date fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;

	private int comparacion;

	private Haversine distanciador;
	
	private int numeroDias;



	public Comparendo(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;

		comparacion =0;
		distanciador = new Haversine();
		
		numeroDias = 0;
	}

	public int darNumerosDias()
	{
		return numeroDias;
	}
	
	public String darLocalidad()
	{
		return localidad;
	}

	public String darMedioDeteccion()
	{
		return medio_dete;
	}

	public String darClaseVeiculo()
	{
		return clase_vehi;
	}

	public String darTipoServicio()
	{
		return tipo_servi;
	}

	public String darCarro()
	{
		return clase_vehi;
	}

	public Date darFecha()
	{
		return fecha_hora;
	}

	public double darLatitud()
	{
		return latitud;
	}

	public double darLongitud()
	{
		return longitud;
	}

	public int darObjec()
	{
		return objectId;
	}

	public String darInfraccion()
	{
		return infraccion;
	}

	public int darComparador()
	{
		return comparacion;
	}

	public String darTipoVeiculo()
	{
		return tipo_servi;
	}

	public void cambiarComparacion(int cam)
	{
		comparacion = cam;
	}
	
	public String darDescripcion()
	{
		return des_infrac;
	}

	//	OBJECTID, FECHA_HORA, INFRACCION,CLASE_VEHICULO, TIPO_SERVICIO, LOCALIDAD

	public String darInformacionDeCarga()
	{
		return "[OBJECTID: " + objectId+ ", FECHA_HORA,: " + fecha_hora + ", INFRACCION: "+ infraccion+ ", CLASE_VEHICULO: "+ clase_vehi + ", TIPO_SERVICIO: " + tipo_servi +", LOCALIDAD: " + localidad + "]";
	}
	public String darInformacion()
	{
		return "[OBJECTID: " + objectId+ ", FECHA_HORA,: " + fecha_hora + ", LOCALIDAD: " + localidad + ", INFRACCION: "+ infraccion + "]";
	}

	public String darDatos()
	{
		return "[OBJECTID: " + objectId+ ", FECHA_HORA,: " + fecha_hora + ", TIPO_SERVI: " + tipo_servi + ", CLASE_VEHI: "+ clase_vehi + ", INFRACCION: "+ infraccion + "]";
	}
	//	 id, tipo de servicio, infracción, fecha-hora, clase de vehículo, longitud y latitud geográficas.

	public String darInformacion1A()
	{
		return "[OBJECTID: " + objectId+ ", TIPO_SERVI: " + tipo_servi+", INFRACCION: "+ infraccion+", FECHA_HORA,: " + fecha_hora + ", CLASE_VEHI: "+ clase_vehi + "]";
	}
	
	public String darInformacion3A()
	{
		return "[OBJECTID: " + objectId+ ", TIPO_SERVI: " + tipo_servi+", INFRACCION: "+ infraccion+", FECHA_HORA,: " + fecha_hora + ", CLASE_VEHI: "+ clase_vehi + ", LOCALIDAD: " + localidad +"]";
	}

	public String darInformacion1B()
	{
		return "[OBJECTID: " + objectId+ ", TIPO_SERVI: " + tipo_servi+", INFRACCION: "+ infraccion+", FECHA_HORA,: " + fecha_hora + ", CLASE_VEHI: "+ clase_vehi +", LATITUD: " + latitud + ", LONGITUD: "+longitud+"]";
	}

	 public String darInformacion3B()
		{
			return "[OBJECTID: " + objectId+ ", TIPO_SERVI: " + tipo_servi+", INFRACCION: "+ infraccion+", FECHA_HORA,: " + fecha_hora + ", CLASE_VEHI: "+ clase_vehi + ", LATITUD: " + latitud +"]";
		}
	public String darInformacion2B()
	{
		return "[OBJECTID: " + objectId+ ", TIPO_SERVI: " + tipo_servi+", INFRACCION: "+ infraccion+", FECHA_HORA,: " + fecha_hora + ", CLASE_VEHI: "+ clase_vehi + ", LOCALIDAD: " + localidad + "]";
	}

	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}


	public int compareTo(Comparendo arg) 
	{
		int valor = 101;
		if(comparacion == 0)
		{
			if(this.darObjec()>arg.darObjec())
			{
				valor = 1;
			}
			else if(this.darObjec()<arg.darObjec())
			{
				valor = -1;
			}
			else if(this.darObjec()==arg.darObjec())
			{
				valor = 0;
			}
		}
		else if(comparacion == 1)
		{
			if(this.darTipoVeiculo().equalsIgnoreCase("PÃºblico") && (arg.darTipoVeiculo().equalsIgnoreCase("Oficial") || arg.darTipoVeiculo().equalsIgnoreCase("Particular")))
			{
				valor = 1;
			}
			else if(this.darTipoVeiculo().equalsIgnoreCase("Oficial") && arg.darTipoVeiculo().compareTo("Oficial") !=0)
			{
				if(arg.darTipoVeiculo().equalsIgnoreCase("PÃºblico"))
				{
					valor = -1;
				}
				else if(arg.darTipoVeiculo().equalsIgnoreCase("Particular"))
				{
					valor = 1;
				}	
			}
			else if(this.darTipoVeiculo().equalsIgnoreCase("Particular") && (arg.darTipoVeiculo().equalsIgnoreCase("Oficial") || arg.darTipoVeiculo().equalsIgnoreCase("PÃºblico")))
			{
				valor = -1;
			}
			else if(this.darTipoVeiculo().equalsIgnoreCase(arg.darTipoVeiculo()))
			{
				if(this.darInfraccion().compareTo(arg.darInfraccion())>0)
				{
					valor = 1;
				}
				else if(this.darInfraccion().compareTo(arg.darInfraccion())<0)
				{
					valor = -1;
				}
				else if(this.darInfraccion().compareTo(arg.darInfraccion())==0)
				{
					if(this.darObjec()>arg.darObjec())
					{
						valor = 1;
					}
					else if(this.darObjec()<arg.darObjec())
					{
						valor = -1;
					}
					else if(this.darObjec()==arg.darObjec())
					{
						valor = 0;
					}
				}
			}
		}
		else if(comparacion == 2)
		{
			if(distanciador.distance(4.647586, -74.078122, this.darLatitud(), this.darLongitud())<distanciador.distance(4.647586, -74.078122, arg.darLatitud(), arg.darLongitud()))
			{
				valor = 1;
			}
			else if(distanciador.distance(4.647586, -74.078122, this.darLatitud(), this.darLongitud())>distanciador.distance(4.647586, -74.078122, arg.darLatitud(), arg.darLongitud()))
			{
				valor = -1;
			}
			else if(distanciador.distance(4.647586, -74.078122, this.darLatitud(), this.darLongitud()) == distanciador.distance(4.647586, -74.078122, arg.darLatitud(), arg.darLongitud()))
			{
				valor = 0;
			}
		}
		else if(comparacion == 3)
		{
			if(this.darFecha().compareTo(arg.darFecha())<0)
			{
				valor = 1;
			}
			else if(this.darFecha().compareTo(arg.darFecha())>0)
			{
				valor = -1;
			}
			else if(this.darFecha().compareTo(arg.darFecha())==0)
			{
				valor = 0;
			}
		}
		else if(comparacion == 4)
		{
			if(this.darDescripcion().contains("INMOVILIZADO") == true && arg.darDescripcion().contains("INMOVILIZADO") == false)
			{
				valor = 1;
			}
			else if(this.darDescripcion().contains("INMOVILIZADO") == true && arg.darDescripcion().contains("INMOVILIZADO") == true)
			{
				if(this.darFecha().compareTo(arg.darFecha())<0)
				{
					valor = 1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())>0)
				{
					valor = -1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())==0)
				{
					valor = 0;
				}
			}
			
			else if(this.darDescripcion().contains("LICENCIA") == true && arg.darDescripcion().contains("INMOVILIZADO") == true)
			{
				valor = -1;
			}
			else if(this.darDescripcion().contains("LICENCIA") == true && arg.darDescripcion().contains("LICENCIA") == true)
			{
				if(this.darFecha().compareTo(arg.darFecha())<0)
				{
					valor = 1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())>0)
				{
					valor = -1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())==0)
				{
					valor = 0;
				}
			}
			else if(this.darDescripcion().contains("LICENCIA") == true && arg.darDescripcion().contains("INMOVILIZADO") == false && arg.darDescripcion().contains("LICENCIA") == false)
			{
				valor = 1;
			}
			
			else if(this.darDescripcion().contains("LICENCIA") == false && this.darDescripcion().contains("INMOVILIZADO") == false && (arg.darDescripcion().contains("INMOVILIZADO") == true || arg.darDescripcion().contains("LICENCIA") == true))
			{
				valor = -1;
			}
			else if(this.darDescripcion().contains("LICENCIA") == false && this.darDescripcion().contains("INMOVILIZADO") == false && arg.darDescripcion().contains("INMOVILIZADO") == false && arg.darDescripcion().contains("LICENCIA") == false)
			{
				if(this.darFecha().compareTo(arg.darFecha())<0)
				{
					valor = 1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())>0)
				{
					valor = -1;
				}
				else if(this.darFecha().compareTo(arg.darFecha())==0)
				{
					valor = 0;
				}
			}
		}

		return valor;
	}
	
	public void cambiarDia(Date fecha)
	{
		numeroDias =numeroDiaAno(fecha)- numeroDiaAno(fecha_hora);
	}
	

	private int numeroDiaAno(Date fecha)
	{
		int respuesta = 0;
		
		if(fecha.getMonth() == 0)
			respuesta = fecha.getDate();
		else if(fecha.getMonth() == 1)
			respuesta = fecha.getDate() +31;
		else if(fecha.getMonth() == 2)
			respuesta = fecha.getDate() +59;
		else if(fecha.getMonth() == 3)
			respuesta = fecha.getDate() +90;
		else if(fecha.getMonth() == 4)
			respuesta = fecha.getDate() +120;
		else if(fecha.getMonth() == 5)
			respuesta = fecha.getDate() +151;
		else if(fecha.getMonth() == 6)
			respuesta = fecha.getDate() +181;
		else if(fecha.getMonth() == 7)
			respuesta = fecha.getDate() +212;
		else if(fecha.getMonth() == 8)
			respuesta = fecha.getDate() +243;
		else if(fecha.getMonth() == 9)
			respuesta = fecha.getDate() +273;
		else if(fecha.getMonth() == 10)
			respuesta = fecha.getDate() +304;
		else if(fecha.getMonth() == 11)
			respuesta = fecha.getDate() +334;
			
		return respuesta;
	}


}
