package camadapersistencia;


public interface MecanismoPersistencia {
public void conectar() throws BDException;
public void desconectar() throws BDException;
public void iniciarTransacao() throws BDException;
public void confirmarTransacao() throws BDException;
public void cancelarTransacao() throws BDException;
}