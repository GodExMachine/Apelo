<div>
			<div style="text-align: center; margin: 20px 0;">
						<p><strong>Pesquisar por:</strong>	</p>

						<!-- Menu opcaoes-->
						<button type="button" class="botao"
							onclick="mostrarFiltro('filtroEspecie')">Espécie</button>
						<button type="button" class="botao"
							onclick="mostrarFiltro('filtroEvento')">Evento</button>
						<button type="button" class="botao"
							onclick="mostrarFiltro('filtroCidade')">Cidade</button>
						<button type="button" class="botao"
							onclick="mostrarFiltro('filtroData')">Data</button>
					</div>

					<!--filtros -->
					<div id="areaFiltros" style="text-align: center; margin-top: 20px;">

						<!-- specie -->
						<div id="filtroEspecie" class="filtro" style="display: none;">
							<form action="buscar-por-especie" method="get">
								<select name="especie" required>
									<option value="">Selecione a espécie...</option>
									<option value="Cachorro">Cachorro</option>
									<option value="Gato">Gato</option>
									<option value="Calopsita">Calopsita</option>
									<option value="Coelho">Coelho</option>
									<option value="Hamster">Hamster</option>
									<option value="Papagaio">Papagaio</option>
									<option value="Tartaruga">Tartaruga</option>
								</select>
								<button type="submit" class="botao">Pesquisar</button>
							</form>
						</div>

						<!--evento -->
						<div id="filtroEvento" class="filtro" style="display: none;">
							<form action="buscar-por-evento" method="get">
								<select name="tipo_evento" required>
									<option value="">Selecione o evento...</option>
									<option value="Avistamento">Avistamento</option>
									<option value="Resgate">Resgate</option>
									<option value="Adoção">Adoção</option>
								</select>
								<button type="submit" class="botao">Pesquisar</button>
							</form>
						</div>

						<!-- cidade -->
						<div id="filtroCidade" class="filtro" style="display: none;">
							<form action="buscar-por-cidade" method="get">
								<input type="text" name="cidade"
									placeholder="Digite a cidade..." required />
								<button type="submit" class="botao">Pesquisar</button>
							</form>
						</div>

						<!--  data -->
						<div id="filtroData" class="filtro" style="display: none;">
							<form action="buscar-por-data" method="get">
								<input type="date" name="data_evento" required />
								<button type="submit" class="botao">Pesquisar</button>
							</form>
						</div>
					</div>

				</div>
				
				
				
				
				<script>
	function mostrarFiltro(filtroId) {
	    document.querySelectorAll('.filtro').forEach(f => f.style.display = 'none');
	    document.getElementById(filtroId).style.display = 'block';
	}
</script>