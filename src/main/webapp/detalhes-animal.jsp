<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Detalhes do Animal</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />

</head>
<body>

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
  

    <!-- Opçcao de evento -->
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
        <th>Data</th>
        <th>Tipo de Evento</th>
        <th>Comentário</th>
        <th>Usuário</th>
    </tr>
<c:forEach var="item" items="${eventosComUsuario}">
    <c:set var="evento" value="${item[0]}" />
    <c:set var="nomeCompleto" value="${item[1]}" />
    <tr>
        <td>${evento.dataEvento}</td>
        <td>${evento.tipoEvento}</td>
        <td>${evento.comentario}</td>
        <td>${nomeCompleto}</td>
    </tr>
</c:forEach>

    
</table>



</body>
</html>
