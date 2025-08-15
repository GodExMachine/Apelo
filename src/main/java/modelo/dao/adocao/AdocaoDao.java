package modelo.dao.adocao;

import modelo.dao.evento.EventoDao;
import modelo.entidade.adocao.Adocao;
import java.sql.SQLException;

public interface AdocaoDao extends EventoDao {
    void inserirAdocao(Adocao adocao) throws SQLException;
}
