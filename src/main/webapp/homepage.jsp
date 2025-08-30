<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Últimos Eventos</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
<style>
  .cards-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr); 
    gap: 1rem;
    margin: 20px;
  }
  .card-evento {
    display: flex;
    gap: 1rem;
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    padding: 0.8rem;
    align-items: center;
    justify-content: space-between;
  }
  .card-foto {
    width: 120px;
    height: 120px;
    object-fit: cover;
    border-radius: 6px;
    cursor: pointer;
    flex-shrink: 0;
  }
  .card-info {
    flex: 1;
    text-align: left;
    font-size: 0.85rem;
  }
  .card-info p {
    margin: 3px 0;
  }
</style>
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
		<div class="cards-container">
			<c:forEach var="item" items="${eventos}">
				<c:set var="evento" value="${item[0]}" />
				<c:set var="animal" value="${item[1]}" />
				<c:set var="endereco" value="${item[2]}" />
				<c:set var="fotoBase64" value="${item[3]}" />
				<c:set var="extensao" value="${item[4]}" />

				<div class="card-evento">
				  <c:if test="${not empty fotoBase64}">
				    <img src="data:image/${extensao};base64,${fotoBase64}" 
				         class="card-foto"
				         onclick="abrirModal(this.src)" />
				  </c:if>

				  <div class="card-info">
				    <p><strong>Espécie:</strong> ${animal.especie}</p>
				    <p><strong>Tipo de Evento:</strong> ${evento.tipoEvento}</p>
				    <p><strong>Data:</strong> 
				      <fmt:parseDate value="${evento.dataEvento}" pattern="yyyy-MM-dd" var="dataParsed" type="date"/>
				      <fmt:formatDate value="${dataParsed}" pattern="dd/MM/yyyy"/>
				    </p>
				    <p><strong>Bairro:</strong> ${endereco.bairro}</p>
				    <p><strong>Cidade:</strong> ${endereco.cidade}</p>
				    <p><strong>Comentário:</strong> ${evento.comentario}</p>
				  </div>

				  <a class="botao" href="detalhes-animal?idAnimal=${evento.idAnimal}">Detalhes</a>
				</div>
			</c:forEach>
		</div>
	</c:if>

	<button class="botao" id="botaoTopo"
	  style="position:fixed; bottom:10px; right:50%; 
	         width:40px; height:40px; 
	         background:#640176; color:#fff; 
	         font-size:26px; font-weight:bold; 
	         border:none;"
	  onclick="voltarAoTopo()">⬆</button>

	<div id="modalFoto" class="modal">
	  <span class="modal-close" onclick="fecharModal()">&times;</span>
	  <img id="modalImg" src="" alt="Foto ampliada" />
	</div>

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
