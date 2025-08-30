<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Perfil do Usuário</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />

   <style>
.layout {
	display: grid;
	grid-template-columns: 1fr 2fr;
	gap: 2rem;
	align-items: flex-start;
}

.perfil-card {
	background: #fff;
	padding: 1.5rem;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.perfil-foto img {
	width: 180px;
	height: 180px;
	object-fit: cover;
	border-radius: 8px;
	border: 2px solid #ccc;
	margin-bottom: 1rem;
}

.info-grid {
	display: grid;
	grid-template-columns: 120px 1fr;
	gap: 8px 16px;
}

.label {
	font-weight: bold;
	color: #444;
}

.actions {
	margin-top: 20px;
	display: flex;
	gap: 10px;
	flex-wrap: wrap;
}

.eventos-lista {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-top: 20px;
}

.evento-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  padding: 1rem;
}

.evento-foto img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 6px;
  margin-right: 1rem;
}

.evento-info {
  flex: 1;
  font-size: 0.95rem;
  color: #333;
}

.evento-info p {
  margin: 4px 0;
}

.evento-actions {
  margin-left: 1rem;
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

  <main class="page">
    <div class="layout">

      <!-- Perfil do Usuário -->
      <div class="perfil-card">

        <!-- Foto -->
        <div class="perfil-foto">
          <c:if test="${not empty fotoBase64}">
            <img src="data:image/${extensao};base64,${fotoBase64}" alt="Foto do Usuário" />
          </c:if>
          <c:if test="${empty fotoBase64}">
            <img src="${pageContext.request.contextPath}/assets/img/default-user.png" alt="Sem foto" />
          </c:if>
        </div>

        <!-- Dados pessoais -->
        <section class="section">
       
          <div class="info-grid">
            <div class="label">Nome</div>
            <div class="value">
              <c:out value="${usuario.nome}" default="-" /> 
              <c:out value="${usuario.sobrenome}" default="-" />
            </div>

            <div class="label">E-mail</div>
            <div class="value"><c:out value="${usuario.email}" default="-" /></div>
          </div>
        </section>

		 
        <!-- Ações -->
        <div class="actions">
          <a href="${pageContext.request.contextPath}/logout" class="botao">Sair</a>
          <form action="/testeprojeto/cadastro-animal" method="get">
          </form>
        </div>
      </div>

			      <!-- Espaço para listar eventos -->
			   <div class="events-card">
				  <h2>Eventos do Usuário</h2>
				
				
						<c:if test="${empty eventosUsuario}">
  <p>Nenhum evento encontrado.</p>
</c:if>

<c:if test="${not empty eventosUsuario}">
  <div class="eventos-lista">
    <c:forEach var="item" items="${eventosUsuario}">
      <c:set var="evento" value="${item[0]}" />
      <c:set var="animal" value="${item[1]}" />
      <c:set var="endereco" value="${item[2]}" />
      <c:set var="fotoBase64Evento" value="${item[3]}" />
      <c:set var="extensaoEvento" value="${item[4]}" />

      <div class="evento-card">
        <div class="evento-foto">
          <c:if test="${not empty fotoBase64Evento}">
            <img src="data:image/${extensaoEvento};base64,${fotoBase64Evento}" alt="Foto do animal"/>
          </c:if>
        </div>

        <div class="evento-info">
          <p><strong>Espécie:</strong> ${animal.especie}</p>
          <p><strong>Tipo:</strong> ${evento.tipoEvento}</p>
          <p><strong>Data:</strong> ${evento.dataEvento}</p>
          <p><strong>Cidade:</strong> ${endereco.cidade}</p>
          <p><strong>Comentário:</strong> ${evento.comentario}</p>
        </div>

        <div class="evento-actions">
          <a class="botao" href="detalhes-animal?idAnimal=${evento.idAnimal}">Detalhes</a>
        </div>
      </div>
    </c:forEach>
  </div>
</c:if>

										
		</div>
  </main>
</body>
</html>