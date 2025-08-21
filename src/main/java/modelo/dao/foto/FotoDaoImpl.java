package modelo.dao.foto;

import modelo.entidade.foto.Foto;
import java.sql.*;

public class FotoDaoImpl implements FotoDao {

	private Connection conectarBanco() throws SQLException {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return DriverManager.getConnection(
	        "jdbc:mysql://localhost/projeto?user=root&password=root&serverTimezone=America/Sao_Paulo"
	    );
	}

 
	public Long inserirFoto(Foto foto) throws SQLException {
	    String sql = "INSERT INTO foto (id_usuario, id_evento, byte_foto, extensao_foto) VALUES (?, ?, ?, ?)";

	    try (Connection con = conectarBanco();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        if (foto.getIdUsuario() == null) {
	            ps.setNull(1, Types.BIGINT);
	        } else {
	            ps.setLong(1, foto.getIdUsuario());
	        }

	        ps.setLong(2, foto.getIdEvento()); 

	        ps.setBytes(3, foto.getByteFoto());
	        ps.setString(4, foto.getExtensaoFoto());

	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getLong(1);
	            }
	        }
	    }
	    return null;
	}




    
}
