package modelo.dao.usuario;  

import modelo.entidade.usuario.Usuario; 

public interface UsuarioDao {
	
	Long inserirUsuario(Usuario usuario);

	void deletarUsuario(Usuario usuario);

	void atualizarNomeUsuario(Usuario usuario, String novoNome);

	void atualizarEmailUsuario(Usuario usuario, String novoEmail);

	void atualizarSobrenomeUsuario(Usuario usuario, String novoSobrenome);

	void atualizarSenhaUsuario(Usuario usuario, String novaSenha);
	
	Usuario buscarPorEmailESenha(String email, String senha);
	
	Object[] buscarUsuarioPorId(Long id);

}