package modelo.dao.evento;

import modelo.entidade.evento.Evento;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public interface EventoDao {
    Long inserirEvento(Evento evento) throws SQLException;
    Evento buscarPorId(Long id) throws SQLException;
    List<Evento> listarEventos() throws SQLException;
    List<Evento> listarPorUsuario(Long idUsuario) throws SQLException;
    List<Object[]> listarUltimoEventoPorAnimal() throws SQLException;
    List<Evento> listarPorAnimal(Long idAnimal) throws SQLException;
    List<Object[]> listarPorAnimalComUsuario(Long idAnimal) throws SQLException;
    List<Object[]> listarUltimoEventoPorAnimalPorEspecie(String especie) throws SQLException;
    List<Object[]> listarUltimoEventoPorAnimalPorTipoEvento(String tipoEvento) throws SQLException;
    List<Object[]> listarUltimoEventoPorAnimalPorCidade(String cidade) throws SQLException;
    List<Evento> listarUltimoEventoPorAnimalPorData(LocalDate data) throws SQLException;

}
