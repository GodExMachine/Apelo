package modelo.dao.adocao;

import modelo.dao.evento.EventoDaoImpl;
import modelo.dao.contato.ContatoDao;
import modelo.dao.contato.ContatoDaoImpl;
import modelo.entidade.adocao.Adocao;
import modelo.entidade.contato.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdocaoDaoImpl extends EventoDaoImpl implements AdocaoDao {

	public void inserirAdocao(Adocao adocao) throws SQLException {
	
		ContatoDao contatoDao = new ContatoDaoImpl();
		contatoDao.inserirContato(adocao.getContato());


		super.inserirEvento(adocao);
	}
}
