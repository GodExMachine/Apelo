package modelo.dao.evento;

import modelo.entidade.evento.Evento;
import modelo.entidade.animal.Animal;
import modelo.entidade.usuario.Usuario;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


public class EventoDaoImpl implements EventoDao {


	public Long inserirEvento(Evento evento) throws SQLException {
	    System.out.println("Inserindo evento: " + evento.getComentario());
	    String sql = "INSERT INTO evento (id_usuario, id_endereco, id_animal, data_evento, comentario, tipo_evento) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection con = conectarBanco();
	         PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setLong(1, evento.getIdUsuario());
	        stmt.setLong(2, evento.getIdEndereco());

	        if (evento.getIdAnimal() == null) {
	            stmt.setNull(3, Types.BIGINT);
	        } else {
	            stmt.setLong(3, evento.getIdAnimal());
	        }

	        stmt.setDate(4, Date.valueOf(evento.getDataEvento()));
	        stmt.setString(5, evento.getComentario());

	        if (evento.getTipoEvento() == null) {
	            stmt.setNull(6, Types.VARCHAR);
	        } else {
	            stmt.setString(6, evento.getTipoEvento());
	        }

	        int linhas = stmt.executeUpdate();
	        System.out.println("Linhas afetadas: " + linhas);

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                Long idGerado = rs.getLong(1);
	                evento.setId(idGerado);
	                System.out.println("ID gerado: " + idGerado);
	                return idGerado;
	            }
	        }
	    }
	    System.out.println("Falha ao inserir evento.");
	    return null;
	}


	public Evento buscarPorId(Long id) throws SQLException {
		String sql = "SELECT * FROM evento WHERE id_evento = ?";
		Evento evento = null;
		try (Connection con = conectarBanco(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					evento = new Evento(rs.getLong("id_evento"), rs.getLong("id_usuario"), rs.getLong("id_endereco"),
							
							rs.getDate("data_evento").toLocalDate(), rs.getString("comentario"));
				}
			}
		}
		return evento;
	}

	public List<Evento> listarEventos() throws SQLException {
		String sql = "SELECT * FROM evento";
		List<Evento> eventos = new ArrayList<>();
		try (Connection con = conectarBanco();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Evento evento = new Evento(rs.getLong("id_evento"), rs.getLong("id_usuario"), rs.getLong("id_endereco"),
						rs.getDate("data_evento").toLocalDate(), rs.getString("comentario"));
				eventos.add(evento);
			}
		}
		return eventos;
	}

	public List<Evento> listarPorUsuario(Long idUsuario) throws SQLException {
		String sql = "SELECT id_evento, id_usuario, id_endereco, id_animal, data_evento, comentario, tipo_evento " +
	             "FROM evento WHERE id_usuario = ? ORDER BY data_evento DESC";

	    List<Evento> eventos = new ArrayList<>();
	    try (Connection con = conectarBanco();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setLong(1, idUsuario);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	              
	            	Evento evento = new Evento(
	            		    rs.getLong("id_evento"),
	            		    rs.getLong("id_usuario"),
	            		    rs.getLong("id_endereco"),
	            		    rs.getLong("id_animal"),
	            		    rs.getDate("data_evento").toLocalDate(),
	            		    rs.getString("comentario"),
	            		    rs.getString("tipo_evento")
	            		);

	                    evento.setIdAnimal((Long) rs.getObject("id_animal"));

	                eventos.add(evento);
	            }
	        }
	    }
	    return eventos;
	}
	
	
	
	
	public List<Evento> listarUltimoEventoPorAnimal() throws SQLException {
	    List<Evento> eventos = new ArrayList<>();

	    String sql = "SELECT e.* " +
	                 "FROM evento e " +
	                 "INNER JOIN ( " +
	                 "    SELECT id_animal, MAX(data_evento) AS ultima_data " +
	                 "    FROM evento " +
	                 "    GROUP BY id_animal " +
	                 ") ult ON e.id_animal = ult.id_animal AND e.data_evento = ult.ultima_data " +
	                 "ORDER BY e.data_evento DESC";

	    try (Connection conn = conectarBanco();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Evento evento = new Evento(
	                rs.getLong("id_evento"),
	                rs.getLong("id_usuario"),
	                rs.getLong("id_endereco"),
	                rs.getLong("id_animal"),
	                rs.getDate("data_evento").toLocalDate(),
	                rs.getString("comentario"),
	                rs.getString("tipo_evento")
	            );
	            eventos.add(evento);
	        }
	    }
	    return eventos;
	}






	public List<Evento> listarPorAnimal(Long idAnimal) throws SQLException {
	    List<Evento> eventos = new ArrayList<>();
	    String sql = "SELECT * FROM evento WHERE id_animal = ? ORDER BY data_evento DESC";

	    try (Connection con = conectarBanco();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setLong(1, idAnimal);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	Evento evento = new Evento(
	            		    rs.getLong("id_evento"),
	            		    rs.getLong("id_usuario"),
	            		    rs.getLong("id_endereco"),
	            		    rs.getLong("id_animal"),
	            		    rs.getDate("data_evento").toLocalDate(),
	            		    rs.getString("comentario"),
	            		    rs.getString("tipo_evento")
	            		);
	                evento.setIdAnimal((Long) rs.getObject("id_animal"));
	                evento.setTipoEvento(rs.getString("tipo_evento"));
	                eventos.add(evento);
	                
	            }
	        }
	    }
	    return eventos;
	}

	public List<Object[]> listarPorAnimalComUsuario(Long idAnimal) throws SQLException {
	    List<Object[]> lista = new ArrayList<>();
	    String sql = "SELECT e.*, u.nome_usuario, u.sobrenome_usuario " +
	                 "FROM evento e " +
	                 "JOIN usuario u ON e.id_usuario = u.id_usuario " +
	                 "WHERE e.id_animal = ? " +
	                 "ORDER BY e.data_evento DESC";

	    try (Connection con = conectarBanco();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setLong(1, idAnimal);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Evento evento = new Evento(
	                        rs.getLong("id_evento"),
	                        rs.getLong("id_usuario"),
	                        rs.getLong("id_endereco"),
	                        rs.getLong("id_animal"),
	                        rs.getDate("data_evento").toLocalDate(),
	                        rs.getString("comentario"),
	                        rs.getString("tipo_evento")
	                );

	               
	                String nomeCompleto = rs.getString("nome_usuario") + " " + rs.getString("sobrenome_usuario");
	                lista.add(new Object[]{evento, nomeCompleto});
	            }
	        }
	    }
	    return lista;
	}

	
	
	
	private Connection conectarBanco() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/projeto?user=root&password=root&serverTimezone=America/Sao_Paulo");
	}
}
