package controle.servlet;

import modelo.dao.adocao.AdocaoDao;
import modelo.dao.adocao.AdocaoDaoImpl;
import modelo.dao.animal.AnimalDao;
import modelo.dao.animal.AnimalDaoImpl;
import modelo.dao.contato.ContatoDao;
import modelo.dao.contato.ContatoDaoImpl;
import modelo.dao.endereco.EnderecoDao;
import modelo.dao.endereco.EnderecoDaoImpl;
import modelo.dao.evento.EventoDao;
import modelo.dao.evento.EventoDaoImpl;

import modelo.entidade.adocao.Adocao;
import modelo.entidade.animal.Animal;
import modelo.entidade.contato.Contato;
import modelo.entidade.endereco.Endereco;
import modelo.entidade.evento.Evento;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/cadastro-adocao", "/inserir-adocao", "/listar-adocoes"})
public class AdocaoServlet extends HttpServlet {

    private AdocaoDao adocaoDao;
    private EnderecoDao enderecoDao;
    private AnimalDao animalDao;
    private ContatoDao contatoDao;
    private EventoDao eventoDao;


    public void init() {
        adocaoDao = new AdocaoDaoImpl();
        enderecoDao = new EnderecoDaoImpl();
        animalDao = new AnimalDaoImpl();
        contatoDao = new ContatoDaoImpl();
        eventoDao = new EventoDaoImpl();
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
                case "/cadastro-adocao":
                    mostrarTelaCadastroAdocao(request, response);
                    break;

                case "/inserir-adocao":
                    inserirAdocao(request, response);
                    break;

                case "/listar-adocoes":
                    listarAdocoes(request, response);
                    break;

                default:
                    mostrarTelaErro404(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void mostrarTelaCadastroAdocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idAnimal = Long.parseLong(request.getParameter("idAnimal"));
        Animal animal = animalDao.buscarAnimalPorId(idAnimal);
        request.setAttribute("animal", animal);

        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-adocao.jsp");
        dispatcher.forward(request, response);
    }


    private void inserirAdocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String idAnimalParam = request.getParameter("idAnimal");
        if (idAnimalParam == null || idAnimalParam.isEmpty()) {
            throw new ServletException("ID do animal não informado no formulário.");
        }
        Long idAnimal = Long.parseLong(idAnimalParam);

        Long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

        Endereco endereco = new Endereco(null,
                request.getParameter("logradouro"),
                request.getParameter("numero"),
                request.getParameter("complemento"),
                request.getParameter("bairro"),
                request.getParameter("cidade"),
                request.getParameter("estado"),
                request.getParameter("cep"));

        Long idEndereco = enderecoDao.buscarIdEnderecoExistente(endereco);
        if (idEndereco == null) {
            idEndereco = enderecoDao.inserirEndereco(endereco);
        }

        Contato contato = new Contato();
        contato.setTelefone(request.getParameter("telefone"));
        contato.setInstagram(request.getParameter("instagram"));

        String tipoEvento = request.getParameter("tipoEvento");
        
        Adocao adocao = new Adocao();
        adocao.setContato(contato);
        adocao.setIdUsuario(idUsuario);
        adocao.setIdEndereco(idEndereco);
        adocao.setIdAnimal(idAnimal);
        adocao.setDataEvento(LocalDate.parse(request.getParameter("dataEvento")));
        adocao.setComentario(request.getParameter("comentario"));
        adocao.setTipoEvento(tipoEvento);

        adocaoDao.inserirAdocao(adocao);
        
        response.sendRedirect("index.jsp");
        
        

	 
        
    }




    private void listarAdocoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setAttribute("adocoes", adocaoDao.listarEventos()); // herdado de EventoDao
        RequestDispatcher dispatcher = request.getRequestDispatcher("listar-adocoes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarTelaErro404(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("erro404.jsp");
        dispatcher.forward(request, response);
    }
}
