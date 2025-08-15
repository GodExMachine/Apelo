<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Perfil do Usuário</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>
  <main class="page">
    <div class="card">
      <header class="card-header">     
          <h1>Perfil do Usuário</h1>
      </header>

      <section class="section">
        <h2>Dados Pessoais</h2>
        <div class="info-grid">
          <div class="label">Nome</div>
          <div class="value"><c:out value="${usuario.nome}" default="-" /> <c:out value="${usuario.sobrenome}" default="-" /></div>

          <div class="label">E-mail</div>
          <div class="value"><c:out value="${usuario.email}" default="-" /></div>
        </div>
      </section>

      <section class="section">
        <h2>Endereço</h2>
        <div class="info-grid">
          <div class="label">Logradouro</div>
          <div class="value"><c:out value="${endereco.logradouro}" default="-" /></div>

          <div class="label">Número</div>
          <div class="value"><c:out value="${endereco.numero}" default="-" /></div>

          <div class="label">Complemento</div>
          <div class="value">
            <c:choose>
              <c:when test="${empty endereco.complemento}">-</c:when>
              <c:otherwise><c:out value="${endereco.complemento}" /></c:otherwise>
            </c:choose>
          </div>

          <div class="label">Bairro</div>
          <div class="value"><c:out value="${endereco.bairro}" default="-" /></div>

          <div class="label">Cidade</div>
          <div class="value"><c:out value="${endereco.cidade}" default="-" /></div>

          <div class="label">Estado</div>
          <div class="value"><c:out value="${endereco.estado}" default="-" /></div>

          <div class="label">CEP</div>
          <div class="value"><c:out value="${endereco.cep}" default="-" /></div>
        </div>
      </section>

      <div class="actions">
        <a href="${pageContext.request.contextPath}/listar-eventos-usuario" class="btn btn-primary">Meus Eventos</a>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Início</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Sair</a>
        <form action="/testeprojeto/cadastro-animal" method="get">
          <button type="submit" class="btn btn-primary">Novo Evento</button>
        </form>
      </div>
    </div>
  </main>
</body>
</html>
