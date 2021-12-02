package negocios;

import java.util.List;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import negocios.beans.Paciente;

public class ControladorPacientes {

    private IRepositorioGenerico<Paciente> repositorioPacientes;
    private static ControladorPacientes instance;

    private ControladorPacientes() {
        this.repositorioPacientes = new RepositorioGenerico<Paciente>("pacientes.dat");
    }

    public static ControladorPacientes getInstance() {
        if (instance == null) {
            instance = new ControladorPacientes();
        }
        return instance;
    }
    
    public void cadastrar(Paciente paciente) throws ElementoJaExisteException{
    	repositorioPacientes.cadastrar(paciente);
    }
	public void remover(Paciente paciente) throws ElementoNaoExisteException{
		repositorioPacientes.remover(paciente);
	}
	public void atualizar(Paciente paciente) throws ElementoNaoExisteException{
		repositorioPacientes.atualizar(paciente);
	}
	public List<Paciente> listar(){
		return repositorioPacientes.listar();
	}
	
}
