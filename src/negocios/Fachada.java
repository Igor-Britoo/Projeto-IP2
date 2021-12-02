package negocios;

import java.util.List;

import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;
import negocios.beans.Consulta;
import negocios.beans.Especialidade;
import negocios.beans.Funcionario;
import negocios.beans.Medico;
import negocios.beans.Paciente;

public class Fachada {
	
	private static Fachada instance;
	
	private ControladorConsultas controladorConsultas;
	private ControladorFuncionarios controladorFuncionarios;
	private ControladorMedicos controladorMedicos;
	private ControladorPacientes controladorPacientes;
	
	
	private Fachada() {
		this.controladorConsultas = ControladorConsultas.getInstance();
		this.controladorFuncionarios = ControladorFuncionarios.getInstance();
		this.controladorMedicos = ControladorMedicos.getInstance();
		this.controladorPacientes = ControladorPacientes.getInstance();
	}
	
	public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }
	
	public void cadastrarConsulta(Consulta consulta) throws ElementoJaExisteException{
    	this.controladorConsultas.cadastrar(consulta);
    }
	public void removerConsulta(Consulta consulta) throws ElementoNaoExisteException{
		this.controladorConsultas.remover(consulta);
	}
	public void atualizarConsulta(Consulta consulta) throws ElementoNaoExisteException{
		this.controladorConsultas.atualizar(consulta);
	}
	public List<Consulta> listarConsultas(){
		return this.controladorConsultas.listar();
	}
	public List<Consulta> listarConsultasPorDataHorario(){
		return this.controladorConsultas.listarConsultasPorData();
	}
	
	
	public void cadastrarFuncionario(Funcionario funcionario) throws ElementoJaExisteException{
    	this.controladorFuncionarios.cadastrar(funcionario);
    }
	public void removerFuncionario(Funcionario funcionario) throws ElementoNaoExisteException{
		this.controladorFuncionarios.remover(funcionario);
	}
	public void atualizarFuncionario(Funcionario funcionario) throws ElementoNaoExisteException{
		this.controladorFuncionarios.atualizar(funcionario);
	}
	public List<Funcionario> listarFuncionarios(){
		return this.controladorFuncionarios.listar();
	}
	
	
	public void cadastrarMedico(Medico medico) throws ElementoJaExisteException{
    	this.controladorMedicos.cadastrar(medico);
    }
	public void removerMedico(Medico medico) throws ElementoNaoExisteException{
		this.controladorMedicos.remover(medico);
	}
	public void atualizarMedico(Medico medico) throws ElementoNaoExisteException{
		this.controladorMedicos.atualizar(medico);
	}
	public List<Medico> listarMedicos(){
		return this.controladorMedicos.listar();
	}
	public List<Medico> listarMedicosEspecialidade(Especialidade especialidade){
		return this.controladorMedicos.listarEspecialidade(especialidade);
	}
	
	
	public void cadastrarPaciente(Paciente paciente) throws ElementoJaExisteException{
    	this.controladorPacientes.cadastrar(paciente);
    }
	public void removerPaciente(Paciente paciente) throws ElementoNaoExisteException{
		this.controladorPacientes.remover(paciente);
	}
	public void atualizarPaciente(Paciente paciente) throws ElementoNaoExisteException{
		this.controladorPacientes.atualizar(paciente);
	}
	public List<Paciente> listarPacientes(){
		return this.controladorPacientes.listar();
	}
	
}
