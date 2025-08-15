package modelo.factory.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/cadastro?user=root&password=root");
    }

}
