package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe do Grafo de Matriz de conexidade.
 */
public class GrafoDeConexidade extends Grafo {

	private List<Vertice> _vertices;
	private int[][] _matriz;

	/*
	 * Construtor da classe.
	 * Inicializa as listas de vertices.
	 */
	public GrafoDeConexidade(Grafo grafo) {
		this._vertices = grafo.getVertices();
	}

	/*
	 * Metodo que monta e exibi a representação do grafo
	 * no painel de saida.
	 */
	public String exibiGrafo() {
		if (!isGrafoConexo()) {
			return "Grafo não é conexo!\n";
		}

		final StringBuilder grafo = new StringBuilder();
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE CONEXIDADE ####\n");
		montaCabecalhoGrafo(grafo);

		for (int i = 0; i < _matriz.length; i++) {
			grafo.append(_vertices.get(i).getId()).append("");
			for (int j = 0; j <= _matriz.length; j++) {
				
				if (j == _matriz.length) {
					grafo.append("|  ").append(_matriz[i][j]).append("  |");
					continue;
				}
				
				grafo.append("|").append(_matriz[i][j]).append("|");
			}

			grafo.append("\n");
		}
		
		grafo.append("\n");
		buscaOsCentrosDoGrafo(grafo);

		grafo.append("-------------------------------------------------------------------\n");

		return grafo.toString();
	}
	
	/*
	 * Método que faz a busca dos centros do grafo a partir da matriz gerada.
	 */
	private void buscaOsCentrosDoGrafo(StringBuilder grafo) {
		int menorCentro = buscaMenorCentro();
		int centro;
		StringBuilder centros = new StringBuilder("centro = {");
		
		for (int i = 0; i < _matriz.length; i++) {
			centro = 0;
			for (int j = 0; j <= _matriz.length; j++) {
				if (j != _matriz.length) { continue; }
				
				centro = _matriz[i][j];
				if (centro != menorCentro) { continue; }

				centros.append(_vertices.get(i).getId()).append(", ");
			}
		}
		
		if (centros.toString().endsWith(", ")) {
			centros.delete(centros.length() - 2, centros.length());
		}
		
		grafo.append("r(G) = ").append(menorCentro).append("\n");
		grafo.append(centros).append("}\n");
		
	}
	
	/*
	 * Método que retorna o menor centro da matriz gerada.
	 */
	private int buscaMenorCentro() {
		int menorCentro = _vertices.size();
		int centro;
		
		for (int i = 0; i < _matriz.length; i++) {
			centro = 0;
			for (int j = 0; j <= _matriz.length; j++) {
				if (j != _matriz.length) { continue; }
				
				centro = _matriz[i][j];
				if (centro <= menorCentro) {
					menorCentro = centro;
				}
			}
		}
		
		return menorCentro;
	}

	/*
	 * Método que verifica se o grafo é conexo.
	 */
	private boolean isGrafoConexo() {
		for (Vertice vertice : _vertices) {
			if (vertice.getListaAdjacentes().isEmpty() && !isVerticeTemConexao(vertice)) {
				return false;
			}
		}

		return true;
	}

	/*
	 * Método que verifica se o vertice tem conexão com algum outro vertice.
	 */
	private boolean isVerticeTemConexao(Vertice vertice) {
		for (Vertice verticeInicio : _vertices) {
			for (Aresta aresta : verticeInicio.getListaAdjacentes()) {
				if (aresta.getInicio().getId() == vertice.getId() || aresta.getFim().getId() == vertice.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Método que gera a matriz de conexidade do grafo.
	 */
	private void gerarMatriz() {
		int tamanhoMatriz = _vertices.size();
		int maiorCentro = 0;
		int grauDeConexidade = 0;

		_matriz = new int[tamanhoMatriz][tamanhoMatriz + 1];

		for (Vertice verticeInicio : _vertices) {
			maiorCentro = 0;
			for (Vertice verticeFim : _vertices) {
				grauDeConexidade = getGrauDeConexidadeEntreOsVertices(verticeInicio, verticeFim);
				_matriz[verticeInicio.getId() - 1][verticeFim.getId() - 1] = grauDeConexidade;
				
				if (grauDeConexidade > maiorCentro) {
					maiorCentro = grauDeConexidade;
				}
			}
			
			_matriz[verticeInicio.getId() - 1][tamanhoMatriz] = maiorCentro;
		}
	}

	/*
	 * Método que retorna o grau de conexidade entre os vertices.
	 */
	private int getGrauDeConexidadeEntreOsVertices(Vertice verticeInicial, Vertice verticeFinal) {
		if (verticeInicial.getId() == verticeFinal.getId()) {
			return 0;
		}
		
		zeraVertices();
		return encontrarMenorCaminhoParaO(verticeInicial, verticeFinal);
	}
	
	/*
	 * Método que retira as visitas e os pais dos vertices
	 * para que possa ser rodade novamente o método "encontrarMenorCaminhoParaO(verticeInicio, verticeFim)"
	 */
	private void zeraVertices() {
		for (Vertice vertice : _vertices) {
			vertice.setVisitar(false);
			vertice.setPai(null);
		}
	}

	/*
	 * Método que retorna o menor caminho entre o verticeInicio e o verticeFim.
	 * Método baseado no Algoritmo de Dijkstra para encontrar o menor caminho entre dois vertices.
	 */
	private int encontrarMenorCaminhoParaO(Vertice verticeInicio, Vertice verticeFim) {
		List<Vertice> verticesVisitados = new ArrayList<Vertice>();
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		
		int verticesNaoVisitados = _vertices.size();
		Vertice verticeAtual = verticeInicio;
		verticesVisitados.add(verticeAtual);
		menorCaminho.add(verticeAtual);

		setaDistanciasDosVertices(verticeAtual);
		
		while (verticesNaoVisitados != 0) {

			verticeAtual = verticesVisitados.get(0);
			for (int i=0; i < verticeAtual.getListaAdjacentes().size(); i++) {

				final Vertice verticeVizinho = verticeAtual.getListaAdjacentes().get(i).getFim();                               
				
				if (verticeVizinho.isVisitado()) { continue; }
				
				if (verticeVizinho.getPai() == null) {
					verticeVizinho.setPai(verticeAtual);
				}
				
				if (verticeVizinho.getDistancia() > (verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso())) {

					verticeVizinho.setDistancia(verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso() * 2);

					if (verticeVizinho == verticeFim) {
						menorCaminho.clear();
						Vertice verticeDoCaminho = verticeVizinho;
						menorCaminho.add(verticeVizinho);
						while (!verticeInicio.equals(verticeDoCaminho.getPai())) {
							menorCaminho.add(verticeDoCaminho.getPai());
							verticeDoCaminho = verticeDoCaminho.getPai();
						}
						
						Collections.sort(menorCaminho);
					}
				}

				verticesVisitados.add(verticeVizinho);
			}

			verticeAtual.setVisitar(true);
			verticesNaoVisitados--;
			verticesVisitados.remove(verticeAtual);
			
			Collections.sort(verticesVisitados);
		}
		
		return menorCaminho.size();
	}

	/*
	 * Método que seta as distancias iniciais dos vertices
	 */
	private void setaDistanciasDosVertices(Vertice verticeAtual) {
		for (Vertice vertice : _vertices) {
			if (vertice.getId() == verticeAtual.getId()) {
				vertice.setDistancia(0D);
			} else {
				vertice.setDistancia(Double.POSITIVE_INFINITY);
			}
		}
	}
	
	/*
	 * Método que monta o cabeçalho para apresentação da matriz do grafo
	 * na tela principal.
	 */
	private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 0; i <= _vertices.size(); i++) {
    		if (i == 0) {
    			grafo.append("  |");
    		} else {
    			grafo.append("|");
    		}
    		
    		if (i == _vertices.size()) {
				grafo.append("e(v)|");
				continue;
			}
    		grafo.append(_vertices.get(i).getId()).append("|");
		}
    	grafo.append("\n");
    }

}		
