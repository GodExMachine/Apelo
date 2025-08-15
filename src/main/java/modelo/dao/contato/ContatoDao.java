package modelo.dao.contato;

import modelo.entidade.contato.Contato;
import java.sql.SQLException;

public interface ContatoDao {
	Long inserirContato(Contato contato) throws SQLException;

}
