<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Cadastrar Animal</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>

		<c:if test="${empty sessionScope.usuarioLogado}">
			<%@ include file="/assets/paginas/menuDeslogado.jsp"%>
		</c:if>

		<c:if test="${not empty sessionScope.usuarioLogado}">
			<%@ include file="/assets/paginas/menuLogado.jsp"%>
		</c:if>


  <main class="form-container" role="main" aria-labelledby="formTitle">
  <!--  <h1 id="formTitle" class="title">Cadastrar novo Animal</h1> -->
	   <form action="inserir-evento" method="post" enctype="multipart/form-data">


     

      <c:if test="${not empty sessionScope.usuarioLogado}">
        <input type="hidden" name="idUsuario" value="${sessionScope.usuarioLogado.id}" />
      </c:if>

      <!-- COLUNA 1 -->
      <section class="form-column" aria-labelledby="enderecoTitle">
        <h2 id="enderecoTitle" class="subtitle">Endereço</h2>
        
        <!-- aqui define a descricao do tipo de evento -->
        <input type="hidden" name="tipoEvento" value="Cadastro">

        <label for="logradouro">Logradouro <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="logradouro" name="logradouro" placeholder="Rua, avenida, etc." required />

        <label for="numero">Número <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="numero" name="numero" placeholder="Número" required />

        <label for="complemento_endereco">Complemento</label>
        <input type="text" id="complemento_endereco" name="complemento" placeholder="Apartamento, bloco, etc." />

        <label for="bairro">Bairro <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="bairro" name="bairro" placeholder="Bairro" required />

        <label for="cidade">Cidade <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="cidade" name="cidade" placeholder="Cidade" required />

        <label for="estado">Estado <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="estado" name="estado" placeholder="Estado" required />

        <label for="cep">CEP <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="cep" name="cep" placeholder="00000-000" required />

        
      </section>

      <!-- COLUNAA 2 -->
      <section class="form-column" aria-labelledby="animalTitle">
        <h2 id="animalTitle" class="subtitle">Informações do Animal</h2>

        <label for="especie">Espécie <span aria-hidden="true" class="required">*</span></label>
		<select id="especie" name="especie" required>
		  <option value="">Selecione...</option>
		  <option value="Cachorro">Cachorro</option>
		  <option value="Gato">Gato</option>
		</select>

        <label for="raca">Raça <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="raca" name="raca" placeholder="Raça do animal" required />

        <label for="cor">Cor <span aria-hidden="true" class="required">*</span></label>
        <input type="text" id="cor" name="cor" placeholder="Cor do animal" required />

	    <label for="porte">Porte <span aria-hidden="true" class="required">*</span></label>
		<select id="porte" name="porte" required>
		  <option value="">Selecione...</option>
		  <option value="Grande">Grande</option>
		  <option value="Medio">Médio</option>
		  <option value="Pequeno">Pequeno</option>
		</select>


		<label for="dataEvento">Data do Evento <span aria-hidden="true" class="required">*</span></label>
        <input type="date" id="dataEvento" name="dataEvento" required />

        <label for="comentario">Comentário</label>
        <textarea id="comentario" name="comentario" rows="4" placeholder="Digite um comentário sobre o evento"></textarea>

		<label for="foto">Foto do Animal <span aria-hidden="true" class="required">*</span></label>
		<input type="file" id="foto" name="foto" accept="image/*" required />



        <button type="submit" class="botao" aria-label="Cadastrar evento e animal">Cadastrar</button>
      
      
      
      
      </section>

    </form>
  </main>

  <script>
    document.querySelector('form').addEventListener('submit', e => {
      alert('Evento cadastrado com sucesso!');
    });
  </script>
</body>

<style>
h1 {
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
	margin-bottom: 10px;
}

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
	max-width: 900px;
	margin: 0 auto;
}

form {
	display: flex;
	gap: 2rem;
}

.form-column {
	flex: 1;
}

label {
	display: block;
	margin-top: 1rem;
	font-weight: bold;
}

select {
  width: 100%;
  padding: 0.75rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  background-color: #fff;
  appearance: none;
}

input {
	width: 100%;
	padding: 0.75rem;
	margin-top: 0.5rem;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 1rem;
}

.botao {
	width: 100px;
	margin-top: 10px;
}
</style>

</html>
