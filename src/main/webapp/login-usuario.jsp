<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Login do Usuário</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>
  <main class="page">
    <div class="form-container" role="main" aria-labelledby="loginTitle">
      <form action="login" method="post" novalidate>
        <h1 id="loginTitle">Login</h1>

        <c:if test="${not empty erro}">
          <div class="error-message" role="alert">${erro}</div>
        </c:if>

        <label for="email">E-mail</label>
        <input type="email" id="email" name="email" autocomplete="email" required />

        <label for="senha">Senha</label>
        <input type="password" id="senha" name="senha" autocomplete="current-password" required />

        <div class="form-actions">
          <button type="submit" class="btn-primary">Entrar</button>
          <a href="index.jsp" class="btn-secondary">Voltar</a>
        </div>
      </form>
    </div>
  </main>
</body>
</html>
