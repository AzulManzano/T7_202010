package model.data_structures;

public class LLaves2B implements Comparable<LLaves2B>
{
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String localidad;
	
	public LLaves2B(String pmedio_dete,String pclase_vehi, String ptipo_servi,String plocalidad)
	{
		medio_dete =pmedio_dete;
		clase_vehi = pclase_vehi;
		tipo_servi = ptipo_servi;
		localidad = plocalidad;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase_vehi == null) ? 0 : clase_vehi.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((medio_dete == null) ? 0 : medio_dete.hashCode());
		result = prime * result + ((tipo_servi == null) ? 0 : tipo_servi.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LLaves2B other = (LLaves2B) obj;
		if (clase_vehi == null) {
			if (other.clase_vehi != null)
				return false;
		} else if (!clase_vehi.equals(other.clase_vehi))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (medio_dete == null) {
			if (other.medio_dete != null)
				return false;
		} else if (!medio_dete.equals(other.medio_dete))
			return false;
		if (tipo_servi == null) {
			if (other.tipo_servi != null)
				return false;
		} else if (!tipo_servi.equals(other.tipo_servi))
			return false;
		return true;
	}



	public int compareTo(LLaves2B arg0) 
	{
		return 0;
	}
	

}
