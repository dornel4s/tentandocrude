package negocio;

import java.util.Enumeration;

import camadapersistencia.ContaNaoCadastradaException;
import camadapersistencia.RepositorioContas;
import camadapersistencia.RepositorioException;

public class CadastroContas {

	private RepositorioContas contas;

	public CadastroContas(RepositorioContas repositorio) {

		if (repositorio == null)

		{
			throw new IllegalArgumentException("Reposit�rio invalido");
		} else {
			contas = repositorio;
		}
	}

	public void cadastrar(Conta conta) throws RepositorioException, ContaJaCadastradaException {

		if (conta == null) {
			throw new IllegalArgumentException("Conta inv�lida");
		}
		String numero = conta.getId();
		if (!contas.existe(numero)) {
			contas.inserir(conta);
		} else {
			throw new ContaJaCadastradaException();
		}
	}

	public Conta procurar(String numero) throws RepositorioException, ContaNaoCadastradaException {

		if (numero == null) {
			throw new IllegalArgumentException("N�mero inv�lido");
		} else {
			return contas.procurar(numero);
		}
	}

	public void atualizar(Conta conta) throws RepositorioException, ContaNaoCadastradaException {

		if (conta == null) {
			throw new IllegalArgumentException("Conta inv�lida");
		} else {
			contas.atualizar(conta);
		}
	}

	public void remover(String numero) throws RepositorioException, ContaNaoCadastradaException {

		if (numero == null) {
			throw new IllegalArgumentException("N�mero inv�lido");
		} else {
			contas.remover(numero);
		}
	}

	/*
	 * public Enumeration retornaTodos() throws RepositorioException {
	 * 
	 * return contas.retornaTodos();
	 * 
	 * }
	 */
}
