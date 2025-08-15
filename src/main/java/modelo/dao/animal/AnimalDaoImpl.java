package modelo.dao.animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidade.animal.Animal;

public class AnimalDaoImpl implements AnimalDao {

	public Long inserirAnimal(Animal animal) throws SQLException {
	    Connection conexao = null;
	    PreparedStatement insert = null;
	    ResultSet chavePrimariaAnimal = null;
	    Long idGerado = null;

	    try {
	        conexao = conectarBanco();
	        insert = conexao.prepareStatement(
	            "INSERT INTO animal (especie_animal, raca_animal, cor_animal, porte_animal) VALUES (?, ?, ?, ?)",
	            Statement.RETURN_GENERATED_KEYS
	        );

	        insert.setString(1, animal.getEspecie());
	        insert.setString(2, animal.getRaca());
	        insert.setString(3, animal.getCor());
	        insert.setString(4, animal.getPorte());

	        insert.executeUpdate();
	        System.out.println("Animal salvo!");

	        chavePrimariaAnimal = insert.getGeneratedKeys();
	        if (chavePrimariaAnimal.next()) {
	            idGerado = chavePrimariaAnimal.getLong(1);
	            animal.setId(idGerado);
	        }
	    } finally {
	        if (chavePrimariaAnimal != null) chavePrimariaAnimal.close();
	        if (insert != null) insert.close();
	        if (conexao != null) conexao.close();
	    }

	    return idGerado;
	}


	public void deletarAnimal(Animal animal) {

		Connection conexao = null;
		PreparedStatement delete = null;

		try {

			conexao = conectarBanco();
			delete = conexao.prepareStatement("DELETE FROM animal WHERE id_animal = ?");

			delete.setLong(1, animal.getId());

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

	
	public Animal buscarAnimalPorId(Long id) throws SQLException {
	    if (id == null) return null;

	    String sql = "SELECT id_animal, especie_animal, raca_animal, cor_animal, porte_animal " +
	                 "FROM animal WHERE id_animal = ?";

	    try (Connection conexao = conectarBanco();
	         PreparedStatement ps = conexao.prepareStatement(sql)) {

	        ps.setLong(1, id);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (!rs.next()) return null;

	            Long   Id     = rs.getLong("id_animal");
	            String especie = rs.getString("especie_animal");
	            String raca    = rs.getString("raca_animal");
	            String cor     = rs.getString("cor_animal");
	            String porte   = rs.getString("porte_animal");

	            return new Animal(Id, especie, raca, cor, porte);
	        }
	    }
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

