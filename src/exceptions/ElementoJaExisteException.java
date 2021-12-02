package exceptions;

public class ElementoJaExisteException extends Exception {
	
	private static final long serialVersionUID = -3701073496865455883L;
	
	private Object elemento;
    
	public ElementoJaExisteException(Object elemento) {
		super("Esse elemento j� est� cadastrado no reposit�rio e n�o pode ser adicionado novamente");
		this.elemento = elemento;   
	}

	public Object getElemento() {
		return elemento;
	}

	public void setElemento(Object elemento) {
		this.elemento = elemento;
	}

}
