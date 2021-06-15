package negocio;

public class ContaJaCadastradaException extends Exception {

	public ContaJaCadastradaException() {
		super("Conta Já cadastrada");
	}

	public ContaJaCadastradaException(String s) {
		super(s);
	}
}
