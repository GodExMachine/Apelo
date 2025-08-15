<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Últimos Eventos</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

<h2>Últimos Eventos</h2>

<c:if test="${empty eventos}">
    <p style="text-align:center;">Nenhum evento encontrado.</p>
</c:if>
<c:if test="${not empty eventos}">
    <table>
        <tr>
            <th>ID Animal</th>
            <th>Tipo de Evento</th>
            <th>Data</th>
            <th>Comentário</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="evento" items="${eventos}">
            <tr>
                <td>${evento.idAnimal}</td>
                <td>${evento.tipoEvento}</td>
                <td>${evento.dataEvento}</td>
                <td>${evento.comentario}</td>
                <td>
                    <a class="botao" href="detalhes-animal?idAnimal=${evento.idAnimal}">
                        Selecionar
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>





</body>
</html>
