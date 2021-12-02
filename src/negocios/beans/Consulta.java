package negocios.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta implements Serializable,Comparable<Consulta>{

	private static final long serialVersionUID = -2685231445753318916L;
	private Paciente pacienteDaConsulta;
	private Medico medicoDaConsulta;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	
	public Consulta(Paciente pacienteDaConsulta, Medico medicoDaConsulta, LocalDateTime inicio) {
		super();
		this.pacienteDaConsulta = pacienteDaConsulta;
		this.medicoDaConsulta = medicoDaConsulta;
		this.inicio = inicio;
		this.termino = inicio.plusMinutes(25);
	}
	
	@Override
	public int compareTo(Consulta o) {
		return this.getInicio().compareTo(o.getInicio());
	}

	public Paciente getPacienteDaConsulta() {
		return pacienteDaConsulta;
	}

	public void setPacienteDaConsulta(Paciente pacienteDaConsulta) {
		this.pacienteDaConsulta = pacienteDaConsulta;
	}

	public Medico getMedicoDaConsulta() {
		return medicoDaConsulta;
	}

	public void setMedicoDaConsulta(Medico medicoDaConsulta) {
		this.medicoDaConsulta = medicoDaConsulta;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}
	
	public String getInicioString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
		return formatador.format(inicio);
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}
	
	public boolean isNull(Object obj) {
		Consulta other = (Consulta) obj;
		
		return other.inicio != null
				&& other.medicoDaConsulta != null
				&& other.medicoDaConsulta.getCpf() != null
				&& other.pacienteDaConsulta != null
				&& other.pacienteDaConsulta.getCpf() != null;
	}

	@Override
	public int hashCode() {
		if(!this.isNull(this)) {
			return this.hashCode();
		}else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Consulta other = (Consulta) obj;
		return this.isNull(obj)
				&& other.inicio.equals(this.inicio)
				&& other.medicoDaConsulta.getCpf().equals(this.medicoDaConsulta.getCpf())
				&& other.pacienteDaConsulta.getCpf().equals(this.pacienteDaConsulta.getCpf());
	}
	

	@Override
	public String toString() {
		
		return String.format("Paciente: %s\nMédico: %s\nHorário: %s",
				this.pacienteDaConsulta.toString(),
				this.medicoDaConsulta.toString(),
				this.inicio);
	}

	
}
