package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

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
		/*
		 * Verifica se o grafo é conexo.
		 */
		if (!isGrafoConexo()) {
			return "Grafo não é conexo!\n";
		}

		final StringBuilder grafo = new StringBuilder();
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE CONEXIDADE ####\n");
		/*
		 * Monta cabeçalho para mostrar na saida do grafo.
		 */
		montaCabecalhoGrafo(grafo);

		for (int indicePai = 0; indicePai < _matriz.length; indicePai++) {
			grafo.append(_vertices.get(indicePai).getId()).append("");
			for (int indiceFilho = 0; indiceFilho <= _matriz.length; indiceFilho++) {
				
				if (indiceFilho == _matriz.length) {
					grafo.append("|  ").append(_matriz[indicePai][indiceFilho]).append("  |");
					continue;
				}
				
				grafo.append("|").append(_matriz[indicePai][indiceFilho]).append("|");
			}

			grafo.append("\n");
		}
		
		grafo.append("\n");
		/*
		 * Busca os centros do Grafo.
		 */
		buscaOsCentrosDoGrafo(grafo);

		grafo.append("-------------------------------------------------------------------\n");

		return grafo.toString();
	}
	
	/*
	 * Método que faz a busca dos centros do grafo a partir da matriz gerada.
	 */
	private void buscaOsCentrosDoGrafo(StringBuilder grafo) {
		/*
		 * Pega o menor centro do grafo.
		 */
		int menorCentro = buscaMenorCentro();
		int centro;
		StringBuilder centros = new StringBuilder("centro = {");
		
		for (int indicePai = 0; indicePai < _matriz.length; indicePai++) {
			centro = 0;
			for (int inidiceFilho = 0; inidiceFilho <= _matriz.length; inidiceFilho++) {
				if (inidiceFilho != _matriz.length) { continue; }
				
				centro = _matriz[indicePai][inidiceFilho];
				if (centro != menorCentro) { continue; }

				centros.append(_vertices.get(indicePai).getId()).append(", ");
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
		
		for (int indicePai = 0; indicePai < _matriz.length; indicePai++) {
			centro = 0;
			for (int indiceFilho = 0; indiceFilho <= _matriz.length; indiceFilho++) {
				if (indiceFilho != _matriz.length) { continue; }
				
				centro = _matriz[indicePai][indiceFilho];
				/*
				 * Verifica se o centro encontrado é 
				 * menor ou igual ao menor centro do grafo.
				 */
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
			/*
			 * Verifica se o vertice tem ligação com outro vertice.
			 */
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
				if (aresta.getInicio().getId().equals(vertice.getId()) || aresta.getFim().getId().equals(vertice.getId())) {
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
		/*
		 * Pega o tamanho da lista de vertices para gerar a matriz.
		 */
		int tamanhoMatriz = _vertices.size();
		int maiorCentro = 0;
		int grauDeConexidade = 0;

		/*
		 * Cria a matriz de conexidade.
		 */
		_matriz = new int[tamanhoMatriz][tamanhoMatriz + 1];

		for (Vertice verticeInicio : _vertices) {
			maiorCentro = 0;
			final int indiceVerticeInicial = getIndiceDoVertice(verticeInicio);

			for (Vertice verticeFim : _vertices) {
				/*
				 * Pega o grau de conexidade entre os vertices.
				 */
				grauDeConexidade = getGrauDeConexidadeEntreOsVertices(verticeInicio, verticeFim);
				
				final int indiceVerticeFinal = getIndiceDoVertice(verticeFim);
				/*
				 * Adiciona o grau de conexidade na matriz.
				 */
				_matriz[indiceVerticeInicial][indiceVerticeFinal] = grauDeConexidade;
				
				/*
				 * Verifica se o grau de conexidade é maior
				 * que o maior centro encontrado.
				 */
				if (grauDeConexidade > maiorCentro) {
					maiorCentro = grauDeConexidade;
				}
			}
			
			/*
			 * Adiciona o maior centro na matriz.
			 */
			_matriz[indiceVerticeInicial][tamanhoMatriz] = maiorCentro;
		}
	}

	/*
	 * Método que retorna o grau de conexidade entre os vertices.
	 */
	private int getGrauDeConexidadeEntreOsVertices(Vertice verticeInicial, Vertice verticeFinal) {
		if (verticeInicial.getId().equals(verticeFinal.getId())) {
			return 0;
		}
		
		/*
		 * Zera os vertices para ser rodado o algoritmo de Dijkstra.
		 */
		zeraVertices();
		
		/*
		 * Pega o menor caminho entre o vertice Inicial e o vertice final.
		 */
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
		/*
		 * Lista de vertices que não foram visitados
		 */
		List<Vertice> verticesNaoVisitados = new ArrayList<Vertice>();
		
		/*
		 * Lista de vertices que representão o menor caminho.
		 */
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		
		Vertice verticeAtual = verticeInicio;

		/*
		 * Adiciona o vertice atual para a lista dos vertices não visitados.
		 */
		verticesNaoVisitados.add(verticeInicio);
		
		/*
		 * Adiciona o vertice atual para a lista de menor caminho.
		 */
		menorCaminho.add(verticeAtual);

		/*
		 * Preenche as distancias iniciais. 
		 * Vertice atual recebe a distancia 0
		 * e os demais vertices receberam distancia infinita.
		 */
		setaDistanciasDosVertices(verticeAtual);
		
		/*
		 * Adiciona todos os vertices como não visitados 
		 * e ordena pela menor distancia.
		 */
		verticesNaoVisitados.addAll(_vertices);
		Collections.sort(verticesNaoVisitados);
		
		/*
		 * Até que todos os vertices sejam visitados o algoritmo irá continuar.
		 */
		while (!verticesNaoVisitados.isEmpty()) {

			/*
			 * Pega sempre o vertice com a menor distancia, que será o primeiro da lista.
			 */
			verticeAtual = verticesNaoVisitados.get(0);
			
			/*
             * Para cada vizinho (aresta), calcula-se a sua possível
             * distancia, somando a distancia do vertice atual com a da aresta
             * correspondente. Se essa distancia for menor que a distancia do
             * vizinho, ela é atualizada.
             */

			for (int i=0; i < verticeAtual.getListaAdjacentes().size(); i++) {

				final Vertice verticeVizinho = verticeAtual.getListaAdjacentes().get(i).getFim();                               
				
				/*
				 * Se o vertice vizinho já foi visitado, passa para o próximo vertice.
				 */
				if (verticeVizinho.isVisitado()) { continue; }
				
				/*
				 * Verifica se a distancia do vertice vizinho é maior que 
				 * a soma da distancia + o peso da aresta do vertice atual.
				 */
				if (verticeVizinho.getDistancia() > (verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso())) {

					/*
					 * Seta a nova distancia para o vertice vizinho.
					 */
					verticeVizinho.setDistancia(verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso());
					verticeVizinho.setPai(verticeAtual);
					
					/*
                     * Se o vertice vizinho é o vertice procurado, e foi feita uma
                     * alteração na distancia, a lista com o menor caminho
                     * anterior é apagada, pois existe um caminho menor de
                     * vertices pais, até o vertice inicial.
                     */
					if (verticeVizinho.equals(verticeFim)) {
						menorCaminho.clear();
						Vertice verticeDoCaminho = verticeVizinho;
						menorCaminho.add(verticeVizinho);
						
						while (!verticeInicio.equals(verticeDoCaminho.getPai())) {
							menorCaminho.add(verticeDoCaminho.getPai());
							verticeDoCaminho = verticeDoCaminho.getPai();
						}
						
						/*
						 * Ordena a lista com o menor caminho.
						 */
						Collections.sort(menorCaminho);
					}
				}
			}

			/*
			 * Marca o vertice atual como visitado.
			 */
			verticeAtual.setVisitar(true);
			
			/*
			 * Remove o vertice atual da lista dos vertices não visitados.
			 */
			verticesNaoVisitados.remove(verticeAtual);
			
			/*
             * Ordena a lista, para que o vertice com menor distancia fique na
             * primeira posição.
             */
			Collections.sort(verticesNaoVisitados);
		}
		
		/*
		 * Retorna o tamanho da lista do menor caminho.
		 * Mostrando quantos vertices o vertice inicial teria que passar
		 * para chegar ao vertice final.
		 */
		JOptionPane.showMessageDialog(null, "Menor distancia é: " + menorCaminho.size());
		return menorCaminho.size();
	}

	/*
	 * Método que seta as distancias iniciais dos vertices
	 */
	private void setaDistanciasDosVertices(Vertice verticeAtual) {
		for (Vertice vertice : _vertices) {
			/*
			 * Se o vertice que está sendo verificado é o atual
			 * recebe a distancia 0, caso contrario receberá a distancia infinita.
			 */
			if (vertice.getId().equals(verticeAtual.getId())) {
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
	
	/*
	 * Retorna o indice do vertice na lista de vertices.
	 */
	public int getIndiceDoVertice(Vertice vertice) {
		return _vertices.indexOf(vertice);
	}

}		
