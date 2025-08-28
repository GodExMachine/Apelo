package modelo.dao.usuario;

import modelo.entidade.usuario.Usuario;
import modelo.entidade.foto.Foto;
import modelo.dao.foto.FotoDao;
import modelo.dao.foto.FotoDaoImpl;
import modelo.entidade.endereco.Endereco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.Statement;
import java.util.Base64; 



public class UsuarioDaoImpl implements UsuarioDao{
	
public Long inserirUsuario(Usuario usuario) {
	    Connection conexao = null;
	    PreparedStatement insert = null;
	    ResultSet rs = null;
	    Long idGerado = null;

	    try {
	        conexao = conectarBanco();
	        insert = conexao.prepareStatement(
	            "INSERT INTO usuario (nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, id_endereco) VALUES (?,?,?,?,?)",
	            Statement.RETURN_GENERATED_KEYS
	        );

	        insert.setString(1, usuario.getNome());
	        insert.setString(2, usuario.getSobrenome());
	        insert.setString(3, usuario.getEmail());
	        insert.setString(4, usuario.getSenha());
	        insert.setLong(5, usuario.getIdEndereco());

	        insert.executeUpdate();

	        rs = insert.getGeneratedKeys();
	        if (rs.next()) {
	            idGerado = rs.getLong(1);
	        }

	        System.out.println("Usuário salvo! ID: " + idGerado);
	    } catch (SQLException erro) {
	        erro.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (insert != null) insert.close();
	            if (conexao != null) conexao.close();
	        } catch (SQLException erro) {
	            erro.printStackTrace();
	        }
	    }
	    return idGerado;
	}

public void deletarUsuario(Usuario usuario) {
		
		Connection conexao = null;
		PreparedStatement delete = null;

		try {

			conexao = conectarBanco();
			delete = conexao.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?");

			delete.setLong(1, usuario.getId());

			delete.execute();

		} catch (SQLException erro) {
			erro.printStackTrace();
		}

		finally {

			try {

				if (delete != null)
					delete.close();

				if (conexao != null)
					conexao.close();

			} catch (SQLException erro) {

				erro.printStackTrace();
			}
		}
	}
	
public void atualizarNomeUsuario(Usuario usuario, String novoNome) {
	
	Connection conexao = null;
	PreparedStatement update = null;

	try {

		conexao = conectarBanco();
		update = conexao.prepareStatement("UPDATE usuario SET nome_usuario = ? WHERE id_usuario = ?");
		
		update.setString(1, novoNome);
		update.setLong(2, usuario.getId());

		update.execute();

	} catch (SQLException erro) {
		erro.printStackTrace();
	}

	finally {

		try {

			if (update != null)
				update.close();

			if (conexao != null)
				conexao.close();

		} catch (SQLException erro) {

			erro.printStackTrace();
		}
	}
}

public void atualizarEmailUsuario(Usuario usuario, String novoEmail) {
	
	Connection conexao = null;
	PreparedStatement update = null;

	try {

		conexao = conectarBanco();
		update = conexao.prepareStatement("UPDATE usuario SET email_usuario = ? WHERE id_usuario = ?");
		
		update.setString(1, novoEmail);
		update.setLong(2, usuario.getId());

		update.execute();

	} catch (SQLException erro) {
		erro.printStackTrace();
	}

	finally {

		try {

			if (update != null)
				update.close();

			if (conexao != null)
				conexao.close();

		} catch (SQLException erro) {

			erro.printStackTrace();
		}
	}
}

public void atualizarSobrenomeUsuario(Usuario usuario, String novoSobrenome) {
	
	Connection conexao = null;
	PreparedStatement update = null;

	try {

		conexao = conectarBanco();
		update = conexao.prepareStatement("UPDATE usuario SET sobrenome_usuario = ? WHERE id_usuario = ?");
		
		update.setString(1, novoSobrenome);
		update.setLong(2, usuario.getId());

		update.execute();

	} catch (SQLException erro) {
		erro.printStackTrace();
	}

	finally {

		try {

			if (update != null)
				update.close();

			if (conexao != null)
				conexao.close();

		} catch (SQLException erro) {

			erro.printStackTrace();
		}
	}
}

public void atualizarSenhaUsuario(Usuario usuario, String novaSenha) {
	
	Connection conexao = null;
	PreparedStatement update = null;

	try {

		conexao = conectarBanco();
		update = conexao.prepareStatement("UPDATE usuario SET senha_usuario = ? WHERE id_usuario = ?");
		
		update.setString(1, novaSenha);
		update.setLong(2, usuario.getId());

		update.execute();

	} catch (SQLException erro) {
		erro.printStackTrace();
	}

	finally {

		try {

			if (update != null)
				update.close();

			if (conexao != null)
				conexao.close();

		} catch (SQLException erro) {

			erro.printStackTrace();
		}
	}
}

public Usuario buscarPorEmailESenha(String email, String senha) {
    Connection conexao = null;
    PreparedStatement select = null;
    Usuario usuario = null;

    try {
        conexao = conectarBanco();
        String sql = "SELECT id_usuario, nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, id_endereco FROM usuario WHERE email_usuario = ? AND senha_usuario = ?";
        select = conexao.prepareStatement(sql);
        select.setString(1, email);
        select.setString(2, senha);

        ResultSet resultado = select.executeQuery();

        if (resultado.next()) {
            Long id = resultado.getLong("id_usuario");
            String nome = resultado.getString("nome_usuario");
            String sobrenome = resultado.getString("sobrenome_usuario");
            String emailUsuario = resultado.getString("email_usuario");
            String senhaUsuario = resultado.getString("senha_usuario");
            Long idEndereco = resultado.getLong("id_endereco");

            usuario = new Usuario(nome, sobrenome, emailUsuario, senhaUsuario, idEndereco);
            usuario.setId(id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (select != null) select.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return usuario;
}



public Object[] buscarUsuarioPorId(Long id) {
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Object[] resultado = null;

    String sql = "SELECT u.*, e.*, f.byte_foto, f.extensao_foto " +
                 "FROM usuario u " +
                 "JOIN endereco e ON u.id_endereco = e.id_endereco " +
                 "LEFT JOIN foto f ON f.id_usuario = u.id_usuario " +
                 "WHERE u.id_usuario = ?";

    try {
        conexao = conectarBanco();
        stmt = conexao.prepareStatement(sql);
        stmt.setLong(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario(
                rs.getString("nome_usuario"),
                rs.getString("sobrenome_usuario"),
                rs.getString("email_usuario"),
                rs.getString("senha_usuario"),
                rs.getLong("id_endereco")
            );
            usuario.setId(rs.getLong("id_usuario"));

            Endereco endereco = new Endereco(
                rs.getLong("id_endereco"),
                rs.getString("logradouro_endereco"),
                rs.getString("numero_endereco"),
                rs.getString("complemento_endereco"),
                rs.getString("bairro_endereco"),
                rs.getString("cidade_endereco"),
                rs.getString("estado_endereco"),
                rs.getString("cep_endereco")
            );

            byte[] dadosFoto = rs.getBytes("byte_foto");
            String extensao = rs.getString("extensao_foto");

            
            resultado = new Object[]{usuario, endereco, dadosFoto, extensao};
        }
    } catch (SQLException e) {
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

    return resultado;
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