<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Header Fixo</title>
  <style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	padding-top: 80px; /* espaço para o header fixo */
}

header {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	z-index: 1000;
	background-color: #640176;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.navBar {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 10px 20px;
	height: 80px;
}

.logo-section {
	display: flex;
	align-items: center;
}

.logo-section img {
	height: 90px;
}

.logo-section a {
	margin-left: 10px;
	font-weight: bold;
	font-size: 40px;
	text-decoration: none;
	color: #FFD000;
}

.conteudo {
	padding: 20px;
}

.perfil-btn {
	background: none;
	border: none;
	cursor: pointer;
	padding: 0;
}

.perfil-img {
	width: 60px;
	height: 60px;
	border-radius: 50%; 
	object-fit: cover;
}
</style>
</head>


  <header>
    <nav>
      <div class="navBar">
        <div></div>

        <div class="logo-section">
          <img src="assets/imgs/logo.png" alt="Logo">
          <a  href="index.jsp">APELO</a>
        </div>

        <div>
		  <form action="/testeprojeto/perfil-usuario" method="get">
		    <button class="perfil-btn" type="submit">
		      <img src="assets/imgs/botao-perfil.png" alt="Perfil" class="perfil-img">
		    </button>
		  </form>
		</div>

      </div>
    </nav>
  </header>




</html>
