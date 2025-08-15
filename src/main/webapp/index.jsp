<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>Página Inicial</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />


</head>
<body>
	<div class="container">
		<h2>APELO</h2>


		<form action="${pageContext.request.contextPath}/homepage"
			method="get">
			<button type="submit">
				Ir para Homepage</button>
		</form>


		<form action="/testeprojeto/listar-eventos" method="get">
			<button type="submit">Listar Eventos (tela provisoria)</button>
		</form>
		
		<c:if test="${empty sessionScope.usuarioLogado}">
		<form action="/testeprojeto/novo-usuario" method="get">
			<button type="submit">Cadastrar novo usuário</button>
		</form>

		
			<form action="/testeprojeto/login-usuario" method="get">
				<button type="submit">Fazer Login</button>
			</form>
		</c:if>



		<c:if test="${not empty sessionScope.usuarioLogado}">
			<form action="/testeprojeto/cadastro-animal" method="get">
				<button type="submit">Novo Animal</button>
			</form>

			<form action="/testeprojeto/perfil-usuario" method="get">
				<button type="submit">Meu Perfil</button>
			</form>

			
		</c:if>
		
		  
		

	</div>
</body>
</html>
