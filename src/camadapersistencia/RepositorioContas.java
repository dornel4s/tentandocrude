package camadapersistencia;
import negocio.Conta;

public interface RepositorioContas {
	public void inserir(Conta conta) throws RepositorioException;
	public void remover(String s) throws RepositorioException, ContaNaoCadastradaException;
	public Conta procurar(String s) throws RepositorioException, ContaNaoCadastradaException;
	public boolean existe(String s) throws RepositorioException;
	public void atualizar(Conta conta) throws RepositorioException, ContaNaoCadastradaException;

}
