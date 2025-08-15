package usuario.controle.servlet;

import modelo.dao.usuario.UsuarioDao;
import modelo.dao.usuario.UsuarioDaoImpl;
import modelo.entidade.usuario.Usuario;

import modelo.dao.endereco.EnderecoDao;
import modelo.dao.endereco.EnderecoDaoImpl;
import modelo.entidade.endereco.Endereco;

import modelo.dao.evento.EventoDao;
import modelo.dao.evento.EventoDaoImpl;
import modelo.entidade.evento.Evento;


import modelo.dao.animal.AnimalDao;
import modelo.dao.animal.AnimalDaoImpl;
import modelo.entidade.animal.Animal;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//@WebServlet(urlPatterns = {"/cadastro-usuario","/inserir-usuario","/login-usuario","/login","/homepage","/perfil-usuario"})
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {

//	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;
	private EnderecoDao enderecoDao;
	private EventoDao eventoDao;
	private AnimalDao animalDao;
	
	
	public void init() {
	    usuarioDao = new UsuarioDaoImpl();
	    enderecoDao = new EnderecoDaoImpl();
	    eventoDao = new EventoDaoImpl();
	    animalDao   = new AnimalDaoImpl(); 
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {

			switch (action) {

			case "/novo-usuario":
				mostrarTelaCadastroUsuario(request, response);
				break;

			case "/inserir-usuario":
				inserirUsuario(request, response);
				break;

			case "/login-usuario":
				mostrarTelaLoginUsuario(request, response);
				break;

			case "/login":
				loginUsuario(request, response);
				break;
				
			case "/logout":
			    logoutUsuario(request, response);
			    break;

			case "/listar-eventos-usuario":
				listarEventosPorUsuario(request, response);
				break;

			case "/perfil-usuario":
				mostrarPerfilUsuario(request, response);
				break;

			default:
				mostrarTelaErro404(request, response);
				break;

			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void mostrarHomepage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarTelaCadastroUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("novo-usuario.jsp");
		dispatcher.forward(request, response);
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {

	    String nome = request.getParameter("nome");
	    String sobrenome = request.getParameter("sobrenome");
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");

	    String logradouro = request.getParameter("logradouro");
	    String numero = request.getParameter("numero");
	    String bairro = request.getParameter("bairro");
	    String cidade = request.getParameter("cidade");
	    String estado = request.getParameter("estado");
	    String complemento = request.getParameter("complemento");
	    String cep = request.getParameter("cep");

	    
	    Endereco endereco = new Endereco(null, logradouro, numero, complemento, bairro, cidade, estado, cep);

	    
	    Long idEndereco = enderecoDao.buscarIdEnderecoExistente(endereco);

	 
	    if (idEndereco == null) {
	        idEndereco = enderecoDao.inserirEndereco(endereco);
	    }

	    Usuario usuario = new Usuario(nome, sobrenome, email, senha, idEndereco);
	    usuarioDao.inserirUsuario(usuario);

	    response.sendRedirect("index.jsp");
	

	}

	private void mostrarTelaLoginUsuario(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("login-usuario.jsp");
		dispatcher.forward(request, response);

	}

	private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Usuario usuario = usuarioDao.buscarPorEmailESenha(email, senha);

		if (usuario != null) {
			System.out.println("Logado com sucesso!");
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuario);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("erro", "Email ou senha inválidos.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login-usuario.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void logoutUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws IOException {
	  
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
		
	private void mostrarPerfilUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    
	    HttpSession session = request.getSession();
	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

	    if (usuario == null) {
	        response.sendRedirect("login-usuario");
	        return;
	    }

	    Long idEndereco = usuario.getIdEndereco(); 

	    Endereco endereco = enderecoDao.buscarEnderecoPorId(idEndereco); 

	    request.setAttribute("usuario", usuario);
	    request.setAttribute("endereco", endereco);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("perfil-usuario.jsp");
	    dispatcher.forward(request, response);
	}

	private void mostrarTelaErro404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}

	private void listarEventosPorUsuario(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {

	    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
	    if (usuario == null) {
	        response.sendRedirect("login-usuario");
	        return;
	    }
	    Long idUsuario = usuario.getId();

	    List<Evento> eventos = eventoDao.listarPorUsuario(idUsuario);
	    List<Endereco> enderecos = new ArrayList<>();
	    List<Animal> animais = new ArrayList<>();

	    for (Evento evento : eventos) {
	        enderecos.add(enderecoDao.buscarEnderecoPorId(evento.getIdEndereco()));
	        animais.add(animalDao.buscarAnimalPorId(evento.getIdAnimal()));
	    }

	    request.setAttribute("eventos", eventos);
	    request.setAttribute("enderecos", enderecos);
	    request.setAttribute("animais", animais);
	    request.setAttribute("usuarioLogado", usuario);


	    RequestDispatcher dispatcher = request.getRequestDispatcher("listar-eventos-usuario.jsp");
	    dispatcher.forward(request, response);
	}


	

	
	
	
	
	

}