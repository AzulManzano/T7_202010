package model.data_structures;

import java.util.Date;

public class LavesCompuestas implements Comparable<LavesCompuestas>
{
	private Date fecha;
	private String clase_vehi;
	private String infraccion;

	public LavesCompuestas(Date pFacha, String pClase, String pInfraccion)
	{
		fecha = pFacha;
		clase_vehi = pClase;
		infraccion = pInfraccion;
	}

	public String toString() {
		return "LavesCompuestas [fecha=" + fecha + ", clase_vehi=" + clase_vehi + ", infraccion=" + infraccion + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase_vehi == null) ? 0 : clase_vehi.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((infraccion == null) ? 0 : infraccion.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LavesCompuestas other = (LavesCompuestas) obj;
		if (clase_vehi == null) {
			if (other.clase_vehi != null)
				return false;
		} else if (!clase_vehi.equals(other.clase_vehi))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (infraccion == null) {
			if (other.infraccion != null)
				return false;
		} else if (!infraccion.equals(other.infraccion))
			return false;
		return true;
	}

	@Override
	public int compareTo(LavesCompuestas o) 
	{
		if(this.equals(o))
		{
			return 0;
		}
		return 1;
	}

}
