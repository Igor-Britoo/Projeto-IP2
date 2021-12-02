package negocios;

import java.util.List;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import negocios.beans.Funcionario;

public class ControladorFuncionarios {

    private IRepositorioGenerico<Funcionario> repositorioFuncionarios;
    private static ControladorFuncionarios instance;

    private ControladorFuncionarios() {
        this.repositorioFuncionarios = new RepositorioGenerico<Funcionario>("funcionarios.dat");
    }

    public static ControladorFuncionarios getInstance() {
        if (instance == null) {
            instance = new ControladorFuncionarios();
        }
        return instance;
    }
    
    public void cadastrar(Funcionario funcionario) throws ElementoJaExisteException{
    	repositorioFuncionarios.cadastrar(funcionario);
    }
	public void remover(Funcionario funcionario) throws ElementoNaoExisteException{
		repositorioFuncionarios.remover(funcionario);
	}
	public void atualizar(Funcionario funcionario) throws ElementoNaoExisteException{
		repositorioFuncionarios.atualizar(funcionario);
	}
	public List<Funcionario> listar(){
		return repositorioFuncionarios.listar();
	}
    
}
