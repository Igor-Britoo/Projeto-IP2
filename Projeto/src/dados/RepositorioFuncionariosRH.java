package dados;

import negocios.beans.FuncionarioRH;

public class RepositorioFuncionariosRH {
	
	private RepositorioUsuarios<FuncionarioRH> repositorio;

	public RepositorioFuncionariosRH(RepositorioUsuarios<FuncionarioRH> repositorio) {
		this.repositorio = repositorio;
	}

	public RepositorioUsuarios<FuncionarioRH> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioUsuarios<FuncionarioRH> repositorio) {
		this.repositorio = repositorio;
	}
	
}
