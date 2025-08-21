package modelo.dao.foto;

import java.sql.SQLException;
import modelo.entidade.foto.Foto;

public interface FotoDao {
    Long inserirFoto(Foto foto) throws SQLException;
    
}
