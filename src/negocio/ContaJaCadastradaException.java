package negocio;

public class ContaJaCadastradaException extends Exception {

	public ContaJaCadastradaException() {
		super("Conta J� cadastrada");
	}

	public ContaJaCadastradaException(String s) {
		super(s);
	}
}
