<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Últimos Eventos</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

		




		<c:if test="${empty sessionScope.usuarioLogado}">
			<%@ include file="/assets/paginas/menuDeslogado.jsp"%>
		</c:if>

		<c:if test="${not empty sessionScope.usuarioLogado}">
			<%@ include file="/assets/paginas/menuLogado.jsp"%>
		</c:if>
		
		  
	
	<%@ include file="/assets/paginas/barraDePesquisa.jsp"%>

	<c:if test="${empty eventos}">
		<p style="text-align: center;">Nenhum evento encontrado.</p>
	</c:if>

	<c:if test="${not empty eventos}">
		<table>
			<tr>
				<th>ID Animal</th>
				<th>Espécie</th>
				<th>Tipo de Evento</th>
				<th>Data</th>
				<th>Rua</th>
				<th>Cidade</th>
				<th>Comentário</th>
				<th>Ação</th>
			</tr>
			
			<c:forEach var="item" items="${eventos}">
				<c:set var="evento" value="${item[0]}" />
				<c:set var="animal" value="${item[1]}" />
				<c:set var="endereco" value="${item[2]}" />

				<tr>
					<td>${evento.idAnimal}</td>
					<td>${animal.especie}</td>

					<td>${evento.tipoEvento}</td>
					<td>${evento.dataEvento}</td>
					<td>${endereco.logradouro}</td>
					<td>${endereco.cidade}</td>
					<td>${evento.comentario}</td>
					<td><a class="botao" href="detalhes-animal?idAnimal=${evento.idAnimal}"> Selecionar</a></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

<button class="botao" id="botaoTopo"
  style="position:fixed; bottom:10px; right:50%; 
         width:40px; height:40px; 
         background:#640176; color:#fff; 
         font-size:26px; font-weight:bold; 
         border:none;"
  onclick="voltarAoTopo()">⬆</button>



</body>

<script>

    window.onscroll = function() {
      let botao = document.getElementById("botaoTopo");
      if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
        botao.style.display = "block";
      } else {
        botao.style.display = "none";
      }
    };

    function voltarAoTopo() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  </script>



</html>




