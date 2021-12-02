package dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.ElementoJaExisteException;
import exceptions.ElementoNaoExisteException;

public class RepositorioGenerico<T> implements IRepositorioGenerico<T>{
	protected List<T> elementos;
	private String filename;
	

    @SuppressWarnings("unchecked")
	public RepositorioGenerico(String filename) {
		
        this.filename = filename;
        this.elementos = new ArrayList<>();
        
        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.filename); 
        
        if (listaElementos != null && listaElementos instanceof List<?>){
            this.elementos = (List<T>) listaElementos;
        }
    }
	
	@Override
	public void cadastrar(T elemento) throws ElementoJaExisteException {
		if(!this.procurar(elemento)) {
			this.elementos.add(elemento);
		}else{
			throw new ElementoJaExisteException(elemento);
		}
		RepositorioFileUtil.salvarArquivo(elementos, this.filename);
	}
	
	@Override
	public void remover(T elemento) throws ElementoNaoExisteException {
		if(this.procurar(elemento)) {
			this.elementos.remove(elemento);
		}else{
			throw new ElementoNaoExisteException(elemento);
		}
		RepositorioFileUtil.salvarArquivo(elementos, this.filename);
	}
	
	@Override
	public void atualizar(T elemento) throws ElementoNaoExisteException {
		if(this.procurar(elemento)) {
			int indice = this.elementos.indexOf(elemento);
			this.elementos.set(indice, elemento);
		}else{
			throw new ElementoNaoExisteException(elemento);
		}
		RepositorioFileUtil.salvarArquivo(elementos, this.filename);
		
	}

	@Override
	public List<T> listar() {
		return Collections.unmodifiableList(this.elementos);
	}
	
	public boolean procurar(T elemento) {
		boolean existe = false;
		if(this.elementos.contains(elemento)) {
			for(T e: this.elementos) {
				if(e.equals(elemento)) {
					existe = true;
				}
			}
		}
		
		return existe;
	}
	
	
}

