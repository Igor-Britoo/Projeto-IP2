package dados;

import java.util.List;

import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;

public interface IRepositorioGenerico<T> {
	void cadastrar(T elemento) throws ElementoJaExisteException;
	void remover(T elemento) throws ElementoNaoExisteException;
	void atualizar(T elemento) throws ElementoNaoExisteException;
	List<T> listar();
}
