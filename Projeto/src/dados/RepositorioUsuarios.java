package dados;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.UsuarioJaExisteException;
import exceptions.UsuarioNaoExisteException;

import negocios.beans.Funcionario;
import negocios.beans.FuncionarioRH;
import negocios.beans.Paciente;
import negocios.beans.Usuario;

public class RepositorioUsuarios<U> implements IRepositorioUsuarios<U> {
	
	protected List<U> usuarios;
	
	public RepositorioUsuarios() {
		this.usuarios = new ArrayList<U>();
	}

	@Override
	public void cadastrar(U usuario) throws UsuarioJaExisteException {
		if(!this.procurar(usuario)) {
			this.usuarios.add(usuario);
		}else{
			throw new UsuarioJaExisteException(usuario);
		}
	}

	@Override
	public void remover(U usuario) throws UsuarioNaoExisteException {
		if(this.procurar(usuario)) {
			this.usuarios.remove(usuario);
		}else{
			throw new UsuarioNaoExisteException(usuario);
		}
	}

	@Override
	public void atualizar(U usuario) throws UsuarioNaoExisteException {
		if(this.procurar(usuario)) {
			int indice = this.usuarios.indexOf(usuario);
			this.usuarios.set(indice, usuario);
		}else{
			throw new UsuarioNaoExisteException(usuario);
		}
		
	}

	public String listar() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String lista = String.format("%16s || %20s || %13s || %20s\n", 
						"TIPO", "NOME", "CPF", "Data de Nascimento");
		
		for(U usuario : this.usuarios) {		
			if(usuario instanceof Paciente) {
				lista += String.format("%-16s || %-20s || %13s || %20s\n",
						((Paciente)usuario).getTipo(),
						((Usuario)usuario).getNome(),
						((Usuario)usuario).getCpf(),
						formatador.format(((Usuario)usuario).getDataDeNascimento()));
				
			}else if (usuario instanceof Funcionario) {
				lista += String.format("%-16s || %-20s || %13s || %20s\n",
						((Funcionario)usuario).getTipo(),
						((Usuario)usuario).getNome(),
						((Usuario)usuario).getCpf(),
						formatador.format(((Usuario)usuario).getDataDeNascimento()));
			}
			else if(usuario instanceof FuncionarioRH) {
				lista += String.format("%-16s || %-20s || %13s || %20s\n",
						((FuncionarioRH)usuario).getTipo(),
						((Usuario)usuario).getNome(),
						((Usuario)usuario).getCpf(),
						formatador.format(((Usuario)usuario).getDataDeNascimento()));
			}
		}
		return lista;
	}

	public boolean procurar(U usuario) {
		boolean existe = false;
		if(this.usuarios.contains(usuario)) existe = true;
		return existe;
	}
	
}
