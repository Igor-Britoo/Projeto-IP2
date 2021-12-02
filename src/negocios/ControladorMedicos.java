package negocios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import negocios.beans.Especialidade;
import negocios.beans.Medico;

public class ControladorMedicos {

    private IRepositorioGenerico<Medico> repositorioMedicos;
    private static ControladorMedicos instance;

    private ControladorMedicos() {
        this.repositorioMedicos = new RepositorioGenerico<Medico>("medicos.dat");
    }

    public static ControladorMedicos getInstance() {
        if (instance == null) {
            instance = new ControladorMedicos();
        }
        return instance;
    }

    public RepositorioGenerico<Medico> getRepositorioMedicos() {
        return ((RepositorioGenerico<Medico>)repositorioMedicos);
    }
    
    public void cadastrar(Medico medico) throws ElementoJaExisteException{
    	repositorioMedicos.cadastrar(medico);
    }
	public void remover(Medico medico) throws ElementoNaoExisteException{
		repositorioMedicos.remover(medico);
	}
	public void atualizar(Medico medico) throws ElementoNaoExisteException{
		repositorioMedicos.atualizar(medico);
	}
	public List<Medico> listar(){
		return repositorioMedicos.listar();
	}
	
	public List<Medico> listarEspecialidade(Especialidade especialidade){
		List<Medico> lista = new ArrayList<>();
		for(Medico m: listar()){
			if(m.getAreaDeAtuacao() == especialidade) {
				lista.add(m);
			}
		}
		return Collections.unmodifiableList(lista);
	}

}
