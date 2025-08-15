package modelo.dao.endereco;

import modelo.entidade.endereco.Endereco;
import java.sql.SQLException;

public interface EnderecoDao {
    Long inserirEndereco(Endereco endereco) throws SQLException;
    void deletarEndereco(Long idEndereco) throws SQLException;
    void atualizarEndereco(Endereco endereco) throws SQLException;
    Endereco buscarEnderecoPorId(Long id);
    Long buscarIdEnderecoExistente(Endereco e) throws SQLException;


}