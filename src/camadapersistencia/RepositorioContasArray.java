package camadapersistencia;

import java.util.Enumeration;

import negocio.Conta;

public class RepositorioContasArray implements RepositorioContas, Enumeration {

	private Conta[] contas;
	private int indice;
	private int indiceEnumeration;

	public RepositorioContasArray(int tamanho) {

		contas = new Conta[tamanho];
		indice = 0;
		indiceEnumeration = 0;

	}

	private int procurarIndice(String numero) {

		int i = 0;
		boolean achou;
		for (achou = false; !achou && i < indice;) {

			if (contas[i].getId().equals(numero)) {

				achou = true;

			} else {
				i++;

			}

		}
		if (!achou)
			i = -1;
		return i;
	}

public Conta procurar(String Id) {

Conta c = null;
int i = procurarIndice(Id);
if (i == -1) {

return null;

} else {

c = contas[i];
return c;
}
}

public void inserir(Conta c) {

	if (indice < contas.length) {

		if (c != null) {

			if (procurar(c.getId()) == null) {

				contas[indice] = c;
				indice = indice + 1;

				}

			} else {
				throw new NullPointerException("Conta inválida");
			}

		}

	}

	public void atualizar(Conta c) throws ContaNaoCadastradaException {

		if (c != null) {

			int i = procurarIndice(c.getId());
			if (i != -1) {

				contas[i] = c;

			} else {
				throw new ContaNaoCadastradaException("Conta de ID " + c.getId() + " nao existe no sistema");
			}

		} else

			throw new NullPointerException("Conta inválida: " + c);

	}

	public void remover(String numero) throws ContaNaoCadastradaException {

		int i = procurarIndice(numero);
		if (i != -1) {

			indice = indice - 1;
			contas[i] = contas[indice];
			contas[indice] = null;

		} else {

			throw new ContaNaoCadastradaException("Conta " + numero + " não existe no sistema");

		}

	}

	public boolean existe(String numero) {
		int i = procurarIndice(numero);
		return i != -1;

	}

public boolean hasMoreElements() {

return indiceEnumeration < indice;

}

public Object nextElement() {

Object resposta = contas[indiceEnumeration];
indiceEnumeration = indiceEnumeration + 1;
return resposta;

}

public Enumeration retornaTodos() throws RepositorioException {

indiceEnumeration = 0;
return this;

}

}