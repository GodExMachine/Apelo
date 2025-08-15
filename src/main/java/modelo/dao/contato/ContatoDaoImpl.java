package modelo.dao.contato;

import modelo.entidade.contato.Contato;
import java.sql.*;

public class ContatoDaoImpl implements ContatoDao {

    
    public Long inserirContato(Contato contato) throws SQLException {
        String sql = "INSERT INTO contato (telefone_contato, instagram_contato) VALUES (?, ?)";

        try (Connection conexao = conectarBanco();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contato.getTelefone());
            ps.setString(2, contato.getInstagram());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

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
}
