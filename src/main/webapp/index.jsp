<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Página Inicial</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background: #fff;
    }

    .hero {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 60px;
    }

    .hero-text {
      max-width: 50%;
    }

    .hero-text h1 {
      font-size: 2.4rem;
      font-weight: bold;
      color: #4b0082;
      margin-bottom: 15px;
    }

    .hero-text p {
      color: #555;
      margin-bottom: 30px;
      line-height: 1.5;
    }

    .btn-home {
      display: block;
      background: #FFD000;
      color:#111;
      padding: 15px 25px;
      margin-bottom: 15px;
      font-size: 1rem;
      font-weight: bold;
      cursor: pointer;
      border-radius: 8px;
      text-align: center;
      width: 220px;
      border: 1px solid #FFD000;

    }

    .btn-home:hover {
     	background: #ffffff;
		border: 1px solid #FFD000;
    }

    .hero img {
      max-width: 320px;
    }

    .stats {
      display: flex;
      justify-content: center;
      gap: 120px;
      padding: 50px 0;
      text-align: center;
    }

    .stats div {
      font-size: 2.2rem;
      font-weight: bold;
      color: #4b0082;
    }

    .stats div span {
      display: block;
      font-size: 1.1rem;
      color: #333;
      margin-top: 8px;
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

  <!-- Seção principal -->
  <section class="hero">
    <div class="hero-text">
      <h1>Abandono de Animais Doméstico<br>em Ambientes Urbanos</h1>
      <p>
        Encontrou algum animal abandonado?<br>
        Registre o animal em nossa plataforma para que<br>
        ele possa encontrar um novo lar.
      </p>

      <!-- Botão "Achei um animal" -->
      <c:choose>
        <c:when test="${empty sessionScope.usuarioLogado}">
          <form action="/testeprojeto/login-usuario" method="get">
            <button type="submit" class="btn-home">Achei um animal</button>
          </form>
        </c:when>
        <c:otherwise>
          <form action="/testeprojeto/cadastro-animal" method="get">
            <button type="submit" class="btn-home">Achei um animal</button>
          </form>
        </c:otherwise>
      </c:choose>

    
      <form action="/testeprojeto/homepage" method="get">
        <button type="submit" class="btn-home">Pesquisar animais</button>
      </form>
    </div>

    <div>
      <img src="${pageContext.request.contextPath}/assets/imgs/dog.png" alt="Cachorro">
    </div>
  </section>

 
  <section class="stats">
    <div>556 <span>Animais Cadastrados</span></div>
    <div>87<span>Adoções Concluídas</span></div>
    <div>215<span>Animais Encontrados</span></div>
  </section>

</body>
</html>
