package modelo.dao.endereco;

import modelo.entidade.endereco.Endereco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoDaoImpl implements EnderecoDao {

	public Long inserirEndereco(Endereco endereco) throws SQLException {
	    Connection conexao = null;
	    PreparedStatement insert = null;
	    ResultSet chavePrimariaEndereco = null;
	    Long idGerado = null;

	    try {
	        conexao = conectarBanco();
	        insert = conexao.prepareStatement(
	            "INSERT INTO endereco (logradouro_endereco, numero_endereco, complemento_endereco, bairro_endereco, cidade_endereco, estado_endereco, cep_endereco) VALUES (?, ?, ?, ?, ?, ?, ?)",
	            Statement.RETURN_GENERATED_KEYS
	        );

	        insert.setString(1, endereco.getLogradouro());
	        insert.setString(2, endereco.getNumero());
	        insert.setString(3, endereco.getComplemento());
	        insert.setString(4, endereco.getBairro());
	        insert.setString(5, endereco.getCidade());
	        insert.setString(6, endereco.getEstado());
	        insert.setString(7, endereco.getCep());
	        System.out.println("Endereço salvo!");
	        insert.executeUpdate();

	        chavePrimariaEndereco = insert.getGeneratedKeys();

	        if (chavePrimariaEndereco.next()) {
	            idGerado = chavePrimariaEndereco.getLong(1);
	            endereco.setId(idGerado);
	        }
	    } finally {
	        if (chavePrimariaEndereco != null) chavePrimariaEndereco.close();
	        if (insert != null) insert.close();
	        if (conexao != null) conexao.close();
	    }

	    return idGerado;
	}

    public void deletarEndereco(Long idEndereco) throws SQLException {
        Connection conexao = null;
        PreparedStatement delete = null;

        try {
            conexao = conectarBanco();
            delete = conexao.prepareStatement("DELETE FROM endereco WHERE id_endereco = ?");
            delete.setLong(1, idEndereco);
            delete.executeUpdate();
        } finally {
            if (delete != null) delete.close();
            if (conexao != null) conexao.close();
        }
    }

    public void atualizarEndereco(Endereco endereco) throws SQLException {
        Connection conexao = null;
        PreparedStatement update = null;

        try {
            conexao = conectarBanco();
            update = conexao.prepareStatement(
                "UPDATE endereco SET logradouro_endereco = ?, numero_endereco = ?, complemento_endereco = ?, bairro_endereco = ?, cidade_endereco = ?, estado_endereco = ?, cep_endereco = ? WHERE id_endereco = ?"
            );

            update.setString(1, endereco.getLogradouro());
            update.setString(2, endereco.getNumero());
            update.setString(3, endereco.getComplemento());
            update.setString(4, endereco.getBairro());
            update.setString(5, endereco.getCidade());
            update.setString(6, endereco.getEstado());
            update.setString(7, endereco.getCep());
            update.setLong(8, endereco.getId());

            update.executeUpdate();
        } finally {
            if (update != null) update.close();
            if (conexao != null) conexao.close();
        }
    }

    public Endereco buscarEnderecoPorId(Long id) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Endereco endereco = null;

        try {
            conexao = conectarBanco();
            String sql = "SELECT id_endereco, logradouro_endereco, numero_endereco, complemento_endereco, bairro_endereco, cidade_endereco, estado_endereco, cep_endereco FROM endereco WHERE id_endereco = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	endereco = new Endereco(
            		    rs.getLong("id_endereco"),
            		    rs.getString("logradouro_endereco"),
            		    rs.getString("numero_endereco"),
            		    rs.getString("complemento_endereco"),
            		    rs.getString("bairro_endereco"),
            		    rs.getString("cidade_endereco"),
            		    rs.getString("estado_endereco"),
            		    rs.getString("cep_endereco")
            		);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereço: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return endereco;
    }

    public Long buscarIdEnderecoExistente(Endereco e) throws SQLException {
        String sql =
            "SELECT id_endereco FROM endereco " +
            "WHERE logradouro_endereco = ? " +
            "  AND numero_endereco     = ? " +
            "  AND bairro_endereco     = ? " +
            "  AND cidade_endereco     = ? " +
            "  AND estado_endereco     = ? " +
            "  AND cep_endereco        = ? " +
            "  AND (complemento_endereco <=> ?)"; 

        try (Connection con = conectarBanco();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getLogradouro());
            ps.setString(2, e.getNumero());
            ps.setString(3, e.getBairro());
            ps.setString(4, e.getCidade());
            ps.setString(5, e.getEstado());
            ps.setString(6, e.getCep());
            ps.setString(7, e.getComplemento()); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id_endereco");
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
    	return DriverManager.getConnection("jdbc:mysql://localhost/projeto?user=root&password=root&serverTimezone=America/Sao_Paulo");
    }

}
