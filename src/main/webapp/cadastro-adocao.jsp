<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Cadastrar Adoção</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />

</head>
<body>
  <main class="form-container" role="main" aria-labelledby="formTitle">
    <form action="inserir-adocao" method="post">

      <h1 id="formTitle" class="title">Cadastrar Adoção</h1>

      <c:if test="${not empty sessionScope.usuarioLogado}">
        <input type="hidden" name="idUsuario" value="${sessionScope.usuarioLogado.id}" />
      </c:if>
		
	  <!-- aqui define a descricao do tipo de evento -->
        <input type="hidden" name="tipoEvento" value="Adocao">	
      <!-- id do Animal pela URL -->
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
        <h2 id="dadosAdocaoTitle" class="subtitle">Dados da Adoção</h2>

        <label for="dataEvento">Data da Adoção <span class="required">*</span></label>
        <input type="date" id="dataEvento" name="dataEvento" required />

        <label for="comentario">Comentário</label>
        <textarea id="comentario" name="comentario" rows="4"></textarea>

        <h2 class="subtitle">Contato</h2>

        <label for="telefone">Telefone</label>
        <input type="text" id="telefone" name="telefone" placeholder="(00) 00000-0000" />

        <label for="instagram">Instagram</label>
        <input type="text" id="instagram" name="instagram" placeholder="@usuario" />

        <h2 class="subtitle">Animal</h2>

        <!--  info do animal  -->
        <p><strong>Espécie:</strong> ${animal.especie}</p>
        <p><strong>Raça:</strong> ${animal.raca}</p>
        <p><strong>Cor:</strong> ${animal.cor}</p>
        <p><strong>Porte:</strong> ${animal.porte}</p>

        <button type="submit" class="btn-submit">Cadastrar</button>
      </section>
    </form>
  </main>
</body>
</html>
