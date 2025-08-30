<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Cadastrar Resgate</title>
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
	
	 <form action="inserir-evento" method="post" enctype="multipart/form-data">
	
	      <c:if test="${not empty sessionScope.usuarioLogado}">
	        <input type="hidden" name="idUsuario" value="${sessionScope.usuarioLogado.id}" />
	      </c:if>
			
		  <!-- aqui define a descricao do tipo de evento -->
	        <input type="hidden" name="tipoEvento" value="Resgate">
			 <input type="hidden" name="idAnimal" value="${animal.id}" />
	      <!-- COLUNA 1 -->
	      <section class="form-column" aria-labelledby="enderecoTitle">
	        <h2 id="enderecoTitle" class="subtitle">Endereço</h2>
	
	        <label for="logradouro">Logradouro <span class="required">*</span></label>
	        <input type="text" id="logradouro" name="logradouro" required />
	
	        <label for="numero">Número <span class="required">*</span></label>
	        <input type="text" id="numero" name="numero" required />
	
	        <label for="complemento">Complemento</label>
	        <input type="text" id="complemento" name="complemento" />
	
	        <label for="bairro">Bairro <span class="required">*</span></label>
	        <input type="text" id="bairro" name="bairro" required />
	
	        <label for="cidade">Cidade <span class="required">*</span></label>
	        <input type="text" id="cidade" name="cidade" required />
	
	        <label for="estado">Estado <span class="required">*</span></label>
	        <input type="text" id="estado" name="estado" required />
	
	        <label for="cep">CEP <span class="required">*</span></label>
	        <input type="text" id="cep" name="cep" required />
	      </section>
	
	      <!-- COLUNA 2 -->
	      <section class="form-column" aria-labelledby="dadosAdocaoTitle">
	        <h2 id="dadosAdocaoTitle" class="subtitle">Dados ddo avistamento</h2>
	
	        <label for="dataEvento">Data do resgate <span class="required">*</span></label>
	        <input type="date" id="dataEvento" name="dataEvento" required />
	
	        <label for="comentario">Comentário</label>
	        <textarea id="comentario" name="comentario" rows="4"></textarea>
	        
	        
	                <h2 class="subtitle">Contato</h2>
	
	        <label for="telefone">Telefone</label>
	        <input type="text" id="telefone" name="telefone" placeholder="(00) 00000-0000" />
	
	        <label for="instagram">Instagram</label>
	        <input type="text" id="instagram" name="instagram" placeholder="@usuario" />
	        
	        
	        
	 
	        	
	        <h2 class="subtitle">Animal</h2>
			
			<label for="foto">Foto do Animal</label>
			<input type="file" id="foto" name="foto" accept="image/*" />  
			
			<p><strong>Espécie:</strong> ${animal.especie}</p>
			<p><strong>Raça:</strong> ${animal.raca}</p>
			<p><strong>Cor:</strong> ${animal.cor}</p>
			<p><strong>Porte:</strong> ${animal.porte}</p>
			
			<!-- Hidden fields para garantir que vão no POST -->
			
			
			<input type="hidden" name="especie" value="${animal.especie}" />
			<input type="hidden" name="raca" value="${animal.raca}" />
			<input type="hidden" name="cor" value="${animal.cor}" />
			<input type="hidden" name="porte" value="${animal.porte}" />
			
			
	        <button type="submit" class="botao">Cadastrar</button>
	      </section>
	      
	   </form>
	  </main>
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
