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
import modelo.dao.foto.FotoDao;
import modelo.dao.foto.FotoDaoImpl;
import modelo.entidade.foto.Foto;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import java.io.InputStream;
import java.util.Base64;


@MultipartConfig
@WebServlet(urlPatterns = { "/cadastro-animal", "/inserir-evento", "/listar-eventos", "/homepage","/detalhes-animal","/buscar-por-especie","/buscar-por-evento","/buscar-por-cidade","/buscar-por-data"})
public class EventoServlet extends HttpServlet {

	private EventoDao eventoDao;
	private EnderecoDao enderecoDao;
	private AnimalDao animalDao;
	private UsuarioDao usuarioDao;
	private FotoDao fotoDao;



	public void init() {
		eventoDao = new EventoDaoImpl();
		enderecoDao = new EnderecoDaoImpl();
		animalDao = new AnimalDaoImpl();
		usuarioDao = new UsuarioDaoImpl();
		fotoDao = new FotoDaoImpl();


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
				
			case "/buscar-por-especie":
			    buscarPorEspecie(request, response);
			    break;
			   
			case "/buscar-por-evento":
			    buscarPorEvento(request, response);
			    break;

			case "/buscar-por-cidade":
			    buscarPorCidade(request, response);
			    break;

			case "/buscar-por-data":
			    buscarPorData(request, response);
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

	 
	    String logradouro = request.getParameter("logradouro");
	    String numero = request.getParameter("numero");
	    String complemento = request.getParameter("complemento");
	    String bairro = request.getParameter("bairro");
	    String cidade = request.getParameter("cidade");
	    String estado = request.getParameter("estado");
	    String cep = request.getParameter("cep");

	    Endereco endereco = new Endereco(null, logradouro, numero, complemento, bairro, cidade, estado, cep);

	    Long idEndereco = enderecoDao.buscarIdEnderecoExistente(endereco);
	    if (idEndereco == null) {
	        idEndereco = enderecoDao.inserirEndereco(endereco);
	    }

	  
	    Long idAnimal = null;
	    String idAnimalParam = request.getParameter("idAnimal"); // vem no form da adoção
	    if (idAnimalParam != null && !idAnimalParam.isBlank()) {
	        
	        idAnimal = Long.parseLong(idAnimalParam);
	    } else {
	       
	        String especie = request.getParameter("especie");
	        String raca = request.getParameter("raca");
	        String cor = request.getParameter("cor");
	        String porte = request.getParameter("porte");

	        Animal animal = new Animal(null, especie, raca, cor, porte);
	        idAnimal = animalDao.inserirAnimal(animal);
	    }

	
	    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
	    if (usuarioLogado == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
	    Long idUsuario = usuarioLogado.getId();

	    
	    LocalDate dataEvento = LocalDate.parse(request.getParameter("dataEvento"));
	    String comentario = request.getParameter("comentario");
	    String tipoEvento = request.getParameter("tipoEvento");

	    Evento evento = new Evento(null, idUsuario, idEndereco, idAnimal, dataEvento, comentario, tipoEvento);
	    Long idEvento = eventoDao.inserirEvento(evento);

	    
	    Part parteFoto = request.getPart("foto");
	    if (parteFoto != null && parteFoto.getSize() > 0) {
	        try (InputStream is = parteFoto.getInputStream()) {
	            byte[] dadosFoto = is.readAllBytes();

	            String nomeArquivo = parteFoto.getSubmittedFileName();
	            String extensao = "";
	            if (nomeArquivo != null && nomeArquivo.contains(".")) {
	                extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1).toLowerCase();
	            }

	            
	            Foto foto = new Foto(null, idUsuario, idEvento, dadosFoto, extensao);
	            fotoDao.inserirFoto(foto);
	        }
	    }


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

	    List<Object[]> eventos = eventoDao.listarUltimoEventoPorAnimal();
	    List<Object[]> eventosComFotoBase64 = new ArrayList<>();

	    for (Object[] item : eventos) {
	        Evento evento = (Evento) item[0];
	        Animal animal = (Animal) item[1];
	        Endereco endereco = (Endereco) item[2];
	        byte[] dadosFoto = (byte[]) item[3];
	        String extensao = (String) item[4];

	        String fotoBase64 = null;
	        if (dadosFoto != null) {
	            fotoBase64 = Base64.getEncoder().encodeToString(dadosFoto);
	        }

	       
	        eventosComFotoBase64.add(new Object[]{evento, animal, endereco, fotoBase64, extensao});
	    }

	    request.setAttribute("eventos", eventosComFotoBase64);
	    
	    

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

	private void buscarPorEspecie(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {

	    String especie = request.getParameter("especie");
	    List<Object[]> eventos = new ArrayList<>();

	    if (especie != null && !especie.isEmpty()) {
	        eventos = eventoDao.listarUltimoEventoPorAnimalPorEspecie(especie);
	    }

	  
	    request.setAttribute("eventos", eventos);
	    request.setAttribute("filtroSelecionado", especie);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
	    dispatcher.forward(request, response);
	}

	private void buscarPorEvento(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {

	    String tipoEvento = request.getParameter("tipo_evento");
	    List<Object[]> eventos = new ArrayList<>();

	    if (tipoEvento != null && !tipoEvento.isEmpty()) {
	        eventos = eventoDao.listarUltimoEventoPorAnimalPorTipoEvento(tipoEvento);
	    }

	    request.setAttribute("eventos", eventos);
	    request.setAttribute("filtroSelecionado", tipoEvento);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
	    dispatcher.forward(request, response);
	}

	private void buscarPorCidade(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {

	    String cidade = request.getParameter("cidade");
	    List<Object[]> eventos = new ArrayList<>();

	    if (cidade != null && !cidade.isEmpty()) {
	        eventos = eventoDao.listarUltimoEventoPorAnimalPorCidade(cidade);
	    }

	    request.setAttribute("eventos", eventos);
	    request.setAttribute("filtroSelecionado", cidade);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
	    dispatcher.forward(request, response);
	}

	private void buscarPorData(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, SQLException {

	    String dataStr = request.getParameter("data_evento");
	    List<Evento> eventos = new ArrayList<>();

	    if (dataStr != null && !dataStr.isEmpty()) {
	        LocalDate data = LocalDate.parse(dataStr);
	        eventos = eventoDao.listarUltimoEventoPorAnimalPorData(data);
	    }

	    request.setAttribute("eventos", eventos);
	    request.setAttribute("filtroSelecionado", dataStr);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
	    dispatcher.forward(request, response);
	}

	
	
}