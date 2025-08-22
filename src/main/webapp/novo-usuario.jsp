<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Cadastrar Usuário</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilo.css?v=1" />
 </head>
<body>
  <main class="form-container" role="main" aria-labelledby="formTitle">
    <form action="inserir-usuario" method="post" enctype="multipart/form-data" novalidate>
      <h1 id="formTitle" class="title">Cadastrar Usuário</h1>

     
			<!-- COLUNA 1 -->
		  <section class="form-column" aria-labelledby="enderecoTitle">
			 <h2 id="enderecoTitle" class="subtitle">Endereço</h2>	
			 <label for="logradouro">Logradouro (Rua, Av...) <span class="required">*</span></label>
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
	      <section class="form-column" aria-labelledby="dadosUsuarioTitle">
	        <h2 id="dadosUsuarioTitle" class="subtitle">Dados do Usuário</h2>
	        <label for="foto">Foto do Usuário</label>
	  		<input type="file" id="foto" name="foto" accept="image/*" />
	        
	        <label for="nome">Nome <span class="required">*</span></label>
	        <input type="text" id="nome" name="nome" required />
	
	        <label for="sobrenome">Sobrenome <span class="required">*</span></label>
	        <input type="text" id="sobrenome" name="sobrenome" required />
	
	        <label for="email">E-mail <span class="required">*</span></label>
	        <input type="email" id="email" name="email" required />
	
	        <label for="senha">Senha <span class="required">*</span></label>
	        <input type="password" id="senha" name="senha" required />
	
	        <label for="confirmarSenha">Confirmar Senha <span class="required">*</span></label>
	        <input type="password" id="confirmarSenha" name="confirmarSenha" required />
	
	        <button type="submit" class="btn-submit">Cadastrar</button>
	      </section>
    </form>
  </main>

  <script>
    document.querySelector("form").addEventListener("submit", function(e) {
      const senha = document.getElementById("senha").value.trim();
      const confirmar = document.getElementById("confirmarSenha").value.trim();

      if (senha.length < 6) {
        e.preventDefault();
        alert("A senha deve ter pelo menos 6 caracteres.");
        return;
      }

      if (senha !== confirmar) {
        e.preventDefault();
        alert("As senhas não coincidem!");
      }
    });
  </script>
</body>
</html>
