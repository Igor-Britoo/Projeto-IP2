package exceptions;

public class ElementoNaoExisteException extends Exception{

	private static final long serialVersionUID = -3953316493082541228L;
	
	private Object elemento;
	
	public ElementoNaoExisteException(Object elemento) {
		super("Esse elemento não existe no repositorio");
		this.elemento = elemento;
	}

	public Object getElemento() {
		return elemento;
	}

	public void setElemento(Object elemento) {
		this.elemento = elemento;
	}

	
	
}
