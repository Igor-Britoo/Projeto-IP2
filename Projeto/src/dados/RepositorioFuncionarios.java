package dados;

import negocios.beans.Funcionario;

public class RepositorioFuncionarios {
	
	private RepositorioUsuarios<Funcionario> repositorio;

	public RepositorioFuncionarios(RepositorioUsuarios<Funcionario> repositorio) {
		this.repositorio = repositorio;
	}

	public RepositorioUsuarios<Funcionario> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioUsuarios<Funcionario> repositorio) {
		this.repositorio = repositorio;
	}

}
