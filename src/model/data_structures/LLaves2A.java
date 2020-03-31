package model.data_structures;

public class LLaves2A implements Comparable<LLaves2A>
{
	private Integer mes;
	private Integer dia;

	public LLaves2A(Integer pmes, Integer pdia)
	{
		mes = pmes;
		dia =pdia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		LLaves2A other = (LLaves2A) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}

	@Override
	public int compareTo(LLaves2A o) 
	{
		
		return 0;
	}
	
	
}
