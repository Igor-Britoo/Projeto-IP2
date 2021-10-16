package dados;

import exceptions.UsuarioJaExisteException;
import exceptions.UsuarioNaoExisteException;

public interface IRepositorioUsuarios<U> {
	void cadastrar(U usuario) throws UsuarioJaExisteException;
	void remover(U usuario) throws UsuarioNaoExisteException;
	void atualizar(U usuario) throws UsuarioNaoExisteException;
	String listar();
}
