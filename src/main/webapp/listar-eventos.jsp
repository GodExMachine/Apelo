<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Todos os Eventos</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>
  <h2>Todos os Eventos</h2>

  <c:if test="${empty eventos}">
    <p>Nenhum evento cadastrado.</p>
</c:if>

<c:if test="${not empty eventos}">
    <table>
        <thead>
            <tr>
                <th class="data-evento">Data</th>
                <th>Endereço</th>
                <th class="col-animal">Animal</th>
                <th>Comentário</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="evento" items="${eventos}" varStatus="status">
                <tr>
                  
                    <td class="data-evento">${evento.dataEvento}</td>

                 
                    <td>
                        Logradouro: ${enderecos[status.index].logradouro}<br/>
                        Número: ${enderecos[status.index].numero}<br/>
                        Bairro: ${enderecos[status.index].bairro}<br/>
                        Cidade: ${enderecos[status.index].cidade} - ${enderecos[status.index].estado}<br/>
                        CEP: ${enderecos[status.index].cep}<br/>
                        Complemento:
                        <c:out value="${empty enderecos[status.index].complemento ? '-' : enderecos[status.index].complemento}"/>
                    </td>

                   
                    <td class="col-animal">
                        <c:choose>
                            <c:when test="${not empty animais[status.index]}">
                                Espécie: ${animais[status.index].especie}<br/>
                                Raça: <c:out value="${empty animais[status.index].raca ? '-' : animais[status.index].raca}"/><br/>
                                Cor: <c:out value="${empty animais[status.index].cor ? '-' : animais[status.index].cor}"/><br/>
                                Porte: <c:out value="${empty animais[status.index].porte ? '-' : animais[status.index].porte}"/>
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>

                
                    <td class="comentario">${evento.comentario}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>


  <div class="btn-container">
 
    <button onclick="location.href='${pageContext.request.contextPath}/index.jsp'">
      Voltar
    </button>
  </div>
</body>
</html>
