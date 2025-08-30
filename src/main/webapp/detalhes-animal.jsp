<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Detalhes do Animal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

    <c:if test="${empty sessionScope.usuarioLogado}">
        <%@ include file="/assets/paginas/menuDeslogado.jsp"%>
    </c:if>

    <c:if test="${not empty sessionScope.usuarioLogado}">
        <%@ include file="/assets/paginas/menuLogado.jsp"%>
    </c:if>

    <main class="container">

 
        <section class="coluna animal-detalhes">
            <h2>Detalhes do Animal</h2>
        
            <div class="info-box">
                <p><strong>ID:</strong> ${animal.id}</p>
                <p><strong>Espécie:</strong> ${animal.especie}</p>
                <p><strong>Raça:</strong> ${animal.raca}</p>
                <p><strong>Cor:</strong> ${animal.cor}</p>
                <p><strong>Porte:</strong> ${animal.porte}</p>
            </div>


            <c:if test="${not empty sessionScope.usuarioLogado}">
                <div class="evento-menu">
                    <button class="botao" id="novoEventoBtn">Novo Evento</button>
                    <div id="opcoesEvento" class="opcoes hidden">
                        <a class="botao" href="cadastro-evento?tipo=avistamento&idAnimal=${animal.id}">Avistamento</a>
                        <a class="botao" href="cadastro-evento?tipo=resgate&idAnimal=${animal.id}">Resgate</a>
                        <a class="botao" href="cadastro-evento?tipo=cadastro&idAnimal=${animal.id}">Cadastro</a>
                    </div>
                </div>
            </c:if>
        </section>

        <!-- Coluna direita -->
        <section class="coluna eventos">
            <h3>Eventos</h3>
            <div class="cards-container">
                <c:forEach var="item" items="${eventosComUsuario}">
                    <c:set var="evento" value="${item[0]}" />
                    <c:set var="nomeCompleto" value="${item[1]}" />
                    <c:set var="fotoBase64" value="${item[2]}" />
                    <c:set var="extensao" value="${item[3]}" />

                    <div class="card-evento">
                        <c:if test="${not empty fotoBase64}">
                            <img src="data:image/${extensao};base64,${fotoBase64}" 
                                 class="card-foto"
                                 onclick="abrirModal(this.src)" />
                        </c:if>
                        <div class="card-info">
                            <p><strong>Data:</strong> 
                                <fmt:parseDate value="${evento.dataEvento}" pattern="yyyy-MM-dd" var="dataParsed" type="date"/>
                                <fmt:formatDate value="${dataParsed}" pattern="dd/MM/yyyy"/>
                            </p>
                            <p><strong>Tipo:</strong> ${evento.tipoEvento}</p>
                            <p><strong>Comentário:</strong> ${evento.comentario}</p>
                            <p><strong>Usuário:</strong> 
                                <a href="perfil-usuario?id=${evento.idUsuario}" class="link-usuario">${nomeCompleto}</a>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>


    <div id="modalFoto" class="modal">
        <span class="modal-close" onclick="fecharModal()">&times;</span>
        <img id="modalImg" src="" alt="Foto ampliada" />
    </div>

    <script>
       
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
          if (event.target === modal) fecharModal();
        }

        document.addEventListener("DOMContentLoaded", () => {
          const btn = document.getElementById("novoEventoBtn");
          const opcoes = document.getElementById("opcoesEvento");
          if(btn){
            btn.addEventListener("click", () => {
              opcoes.classList.toggle("hidden");
            });
          }
        });
    </script>

<style>	
.container {
	display: flex;
	gap: 2rem;
	margin: 20px;
}

.coluna {
	flex: 1;
	background: #fff;
	padding: 1rem;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.animal-detalhes {
	max-width: 320px;
}

.foto-principal {
	width: 100%;
	max-height: 250px;
	object-fit: cover;
	border-radius: 8px;
	margin-bottom: 1rem;
	cursor: pointer;
}

.info-box p {
	margin: 6px 0;
}

.evento-menu {
	text-align: center;
	position: relative;
	margin-top: 1rem;
}

.evento-menu .opcoes {
	display: flex;
	flex-direction: column;
	gap: 0.5rem;
	margin-top: 0.5rem;
	background: #f5f5f5;
	padding: 0.5rem;
	border-radius: 6px;
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	z-index: 10;
}

.evento-menu .hidden {
	display: none;
}


.cards-container {
	display: flex;
	flex-direction: column;
	gap: 1rem;
	margin-top: 1rem;
}

.card-evento {
	display: flex;
	gap: 1rem;
	background: #fafafa;
	padding: 1rem;
	border-radius: 8px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	align-items: flex-start;
}

.card-foto {
	width: 100px;
	height: 100px;
	object-fit: cover;
	border-radius: 6px;
	cursor: pointer;
}

.card-info p {
	margin: 4px 0;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.8);
	justify-content: center;
	align-items: center;
}

.modal img {
	max-width: 90%;
	max-height: 80%;
	border-radius: 8px;
}

.modal-close {
	position: absolute;
	top: 20px;
	right: 30px;
	color: white;
	font-size: 2rem;
	cursor: pointer;
}
</style>

</body>
</html>
