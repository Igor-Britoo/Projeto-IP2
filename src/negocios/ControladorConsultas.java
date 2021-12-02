package negocios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import negocios.beans.Consulta;

public class ControladorConsultas {

    private IRepositorioGenerico<Consulta> repositorioConsultas;
    private static ControladorConsultas instance;

    private ControladorConsultas() {
        this.repositorioConsultas = new RepositorioGenerico<Consulta>("consultas.dat");
    }

    public static ControladorConsultas getInstance() {
        if (instance == null) {
            instance = new ControladorConsultas();
        }
        return instance;
    }
    
    public void cadastrar(Consulta consulta) throws ElementoJaExisteException{
    	repositorioConsultas.cadastrar(consulta);
    }
	public void remover(Consulta consulta) throws ElementoNaoExisteException{
		repositorioConsultas.remover(consulta);
	}
	public void atualizar(Consulta consulta) throws ElementoNaoExisteException{
		repositorioConsultas.atualizar(consulta);
	}
	public List<Consulta> listar(){
		return repositorioConsultas.listar();
	}
	
	public List<Consulta> listarConsultasPorData(){
        List<Consulta> novaLista = new ArrayList<>();
        for (Consulta c : this.repositorioConsultas.listar()) {
            if (c.getInicio().isAfter(LocalDateTime.now())) {
                novaLista.add(c);
            }
        }
        Collections.sort(novaLista);
		return novaLista;
	}
    
}
