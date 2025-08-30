<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Login Usuário</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

  <c:if test="${empty sessionScope.usuarioLogado}">
    <%@ include file="/assets/paginas/menuDeslogado.jsp"%>
  </c:if>

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
		  <button type="submit" class="botao">Entrar</button>
		</div>

        <div class="register-link">
          Não possui conta? 
          <a href="${pageContext.request.contextPath}/novo-usuario">Cadastre-se</a>
        </div>
      </form>
    </div>
  </main>

</body>

<style>
.page {
  display: flex;
  justify-content: center;
  align-items: center;     
  height: 100vh;       
  background-color: #f0f0f0; 
}

.form-container {
  background-color: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

label {
  display: block;
  margin-top: 1rem;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 0.75rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}


.register-link {
  margin-top: 15px;
  text-align: center;
  font-size: 0.9rem;
  color: #333;
}

.register-link a {
  color: #640176;
  font-weight: bold;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}

.form-actions {
  text-align: center;
  margin-top: 15px;
}




</style>
</html>
