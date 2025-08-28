<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Detalhes do Animal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

    <c:if test="${empty sessionScope.usuarioLogado}">
        <%@ include file="/assets/paginas/menuDeslogado.jsp"%>
    </c:if>

    <c:if test="${not empty sessionScope.usuarioLogado}">
        <%@ include file="/assets/paginas/menuLogado.jsp"%>
    </c:if>

    <h2>Detalhes do Animal</h2>
    <div class="info-box">
        <p><strong>ID:</strong> ${animal.id}</p>
        <p><strong>Espécie:</strong> ${animal.especie}</p>
        <p><strong>Raça:</strong> ${animal.raca}</p>
        <p><strong>Cor:</strong> ${animal.cor}</p>
        <p><strong>Porte:</strong> ${animal.porte}</p>
    </div>

    <!-- Se usuário estiver logado -->
    <c:if test="${not empty sessionScope.usuarioLogado}">
        <div>
            <a>Inserir novo: </a>
            <a href="cadastro-evento?tipo=avistamento&idAnimal=${animal.id}" class="botao">Avistamento</a>
            <a href="cadastro-evento?tipo=resgate&idAnimal=${animal.id}" class="botao">Resgate</a>
            <a href="cadastro-adocao?idAnimal=${animal.id}" class="botao">Adoção</a>
        </div>
    </c:if>

    <h3>Eventos</h3>
    <table>
        <tr>
            <th>Foto</th>
            <th>Data</th>
            <th>Tipo de Evento</th>
            <th>Comentário</th>
            <th>Usuário</th>
        </tr>

        <c:forEach var="item" items="${eventosComUsuario}">
            <c:set var="evento" value="${item[0]}" />
            <c:set var="nomeCompleto" value="${item[1]}" />
            <c:set var="fotoBase64" value="${item[2]}" />
            <c:set var="extensao" value="${item[3]}" />

            <tr>
                <td>
                    <c:if test="${not empty fotoBase64}">
                        <img src="data:image/${extensao};base64,${fotoBase64}"
                             class="img-quadrada"
                             style="cursor:pointer"
                             onclick="abrirModal(this.src)" />
                    </c:if>
                </td>
                <td>
                    <fmt:parseDate value="${evento.dataEvento}" pattern="yyyy-MM-dd" var="dataParsed" type="date"/>
                    <fmt:formatDate value="${dataParsed}" pattern="dd/MM/yyyy"/>
                </td>
                <td>${evento.tipoEvento}</td>
                <td>${evento.comentario}</td>
                <td>
				  <a href="perfil-usuario?id=${evento.idUsuario}" class="link-usuario">${nomeCompleto}</a>
	   		    </td>

            </tr>
        </c:forEach>
    </table>

    <!-- Modal para ampliar foto -->
    <div id="modalFoto" class="modal">
        <span class="modal-close" onclick="fecharModal()">&times;</span>
        <img id="modalImg" src="" alt="Foto ampliada" />
    </div>

    <script>
        function abrirModal(src) {
          let modal = document.getElementById("modalFoto");
          let modalImg = document.getElementById("modalImg");
          modal.style.display = "flex";
          modalImg.src = src;
        }

        function fecharModal() {
          document.getElementById("modalFoto").style.display = "none";
        }

        window.onclick = function(event) {
          let modal = document.getElementById("modalFoto");
          if (event.target === modal) {
            fecharModal();
          }
        }
    </script>

</body>
</html>
