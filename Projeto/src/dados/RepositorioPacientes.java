package dados;

import negocios.beans.Paciente;

public class RepositorioPacientes {
	
	private RepositorioUsuarios<Paciente> repositorio;

	public RepositorioPacientes(RepositorioUsuarios<Paciente> repositorio) {
		this.repositorio = repositorio;
	}

	public RepositorioUsuarios<Paciente> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioUsuarios<Paciente> repositorio) {
		this.repositorio = repositorio;
	}
	
	
	
}
