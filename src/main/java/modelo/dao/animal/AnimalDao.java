package modelo.dao.animal;

import java.sql.SQLException;
import modelo.entidade.animal.Animal;

public interface AnimalDao {
    Long inserirAnimal(Animal animal) throws SQLException;
    Animal buscarAnimalPorId(Long id) throws SQLException;
    void deletarAnimal(Animal animal);
}
