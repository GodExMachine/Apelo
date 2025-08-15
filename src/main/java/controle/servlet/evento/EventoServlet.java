package controle.servlet;

import modelo.dao.evento.EventoDao;
import modelo.dao.evento.EventoDaoImpl;
import modelo.entidade.evento.Evento;
import modelo.entidade.endereco.Endereco;
import modelo.dao.endereco.EnderecoDao;
import modelo.dao.endereco.EnderecoDaoImpl;
import modelo.dao.animal.AnimalDao;
import modelo.dao.animal.AnimalDaoImpl;
import modelo.entidade.animal.Animal;
import modelo.dao.usuario.UsuarioDao;
import modelo.dao.usuario.UsuarioDaoImpl;
import modelo.entidade.usuario.Usuario;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@WebServlet(urlPatterns = { "/cadastro-animal", "/inserir-evento", "/listar-eventos", "/homepage","/detalhes-animal" })
public class EventoServlet extends HttpServlet {

	private EventoDao eventoDao;
	private EnderecoDao enderecoDao;
	private AnimalDao animalDao;
	private UsuarioDao usuarioDao;


	public void init() {
		eventoDao = new EventoDaoImpl();
		enderecoDao = new EnderecoDaoImpl();
		animalDao = new AnimalDaoImpl();
		usuarioDao = new UsuarioDaoImpl();

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
			case "/cadastro-animal":
				mostrarTelaCadastroAnimal(request, response);
				break;

			case "/inserir-evento":
				inserirEvento(request, response);
				break;

			case "/detalhes-animal":
				mostrarDetalhesAnimal(request, response);
				break;

			case "/listar-eventos":
				listarEventos(request, response);
				break;

			case "/homepage":
				mostrarHomepage(request, response);
				break;

			default:
				mostrarTelaErro404(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void mostrarTelaCadastroAnimal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-animal.jsp");
		dispatcher.forward(request, response);
	}

	private void inserirEvento(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {

	    Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

	
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

	
	    String especie = request.getParameter("especie");
	    String raca = request.getParameter("raca");
	    String cor = request.getParameter("cor");
	    String porte = request.getParameter("porte");

	    Animal animal = new Animal(null, especie, raca, cor, porte);
	    Long idAnimal = animalDao.inserirAnimal(animal);

	
	    LocalDate dataEvento = LocalDate.parse(request.getParameter("dataEvento"));
	    String comentario = request.getParameter("comentario");

	    String tipoEvento = request.getParameter("tipoEvento");

	    Evento evento = new Evento(idUsuario, idEndereco, idAnimal, dataEvento, comentario, tipoEvento);
	    eventoDao.inserirEvento(evento);

	    response.sendRedirect("index.jsp");
	}

	private void mostrarDetalhesAnimal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Long idAnimal = Long.parseLong(request.getParameter("idAnimal"));

		Animal animal = animalDao.buscarAnimalPorId(idAnimal);

		List<Object[]> eventosComUsuario = eventoDao.listarPorAnimalComUsuario(idAnimal);

		request.setAttribute("animal", animal);
		request.setAttribute("eventosComUsuario", eventosComUsuario);

		RequestDispatcher dispatcher = request.getRequestDispatcher("detalhes-animal.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarHomepage(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {

		List<Evento> eventos = eventoDao.listarUltimoEventoPorAnimal();
		request.setAttribute("eventos", eventos);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
	    dispatcher.forward(request, response);
	}



	private void mostrarTelaErro404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
		dispatcher.forward(request, response);
	}


	
	
	
	private void listarEventos(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {

		List<Evento> eventos = eventoDao.listarEventos();
		List<Endereco> enderecos = new ArrayList<>();
		List<Animal> animais = new ArrayList<>();

		for (Evento evento : eventos) {
		    enderecos.add(enderecoDao.buscarEnderecoPorId(evento.getIdEndereco()));
		    animais.add(animalDao.buscarAnimalPorId(evento.getIdAnimal()));
		}

		request.setAttribute("eventos", eventos);
		request.setAttribute("enderecos", enderecos);
		request.setAttribute("animais", animais);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("listar-eventos.jsp");
	    dispatcher.forward(request, response);
	}


	
}