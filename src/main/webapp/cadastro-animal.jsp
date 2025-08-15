<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Cadastrar Evento</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
</head>
<body>
  <main class="form-container" role="main" aria-labelledby="formTitle">
    <form action="inserir-evento" method="post">

      <h1 id="formTitle" class="title">Cadastrar novo Animal</h1>

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
		  <option value="Calopsita">Calopsita</option>
		  <option value="Coelho">Coelho</option>
		  <option value="Hamster">Hamster</option>
		  <option value="Papagaio">Papagaio</option>
		  <option value="Tartaruga">Tartaruga</option>
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



        <button type="submit" class="btn-submit" aria-label="Cadastrar evento e animal">Cadastrar</button>
      
      
      
      
      </section>

    </form>
  </main>

  <script>
    document.querySelector('form').addEventListener('submit', e => {
      alert('Evento cadastrado com sucesso!');
    });
  </script>
</body>
</html>
