package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe Grafo Matriz de Adjacencia.
 */
public class GrafoMatrizAdj extends Grafo {

	List<Vertice> _vertices;
    List<Aresta> _arestas;
    double[][] _matriz;

    /*
     * Construtor da Classe.
     * Inicializa as listas de vertices e arestas.
     */
    public GrafoMatrizAdj(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }
    
    /*
     * Metodo que monta e exibi a representação do grafo
     * no painel de saida.
     */
    public String exibiGrafo() {
		final StringBuilder grafo = new StringBuilder();
		
		/*
		 * Gera a matriz de adjacencia.
		 */
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE ADJACENCIA ####\n");
		/*
		 * Monta o cabeçalho para mostrar na saida do grafo.
		 */
    	montaCabecalhoGrafo(grafo);

    	for (int indicePai = 0; indicePai < _matriz.length; indicePai++) {
			grafo.append(_vertices.get(indicePai).getId()).append("");
			for (int indiceFilho = 0; indiceFilho < _matriz.length; indiceFilho++) {
				/*
				 * Se o grafo for usa o valor que foi passado na aresta.
				 * Caso contrario usa 0 ou 1 para representar as vertices com ligações entre si.
				 */
				if (isValorado()) {
					grafo.append("|").append(_matriz[indicePai][indiceFilho]).append("|");
				} else {
					preencheValoresGrafoNaoValorado(grafo, indicePai, indiceFilho);
				}
			}
			
			grafo.append("\n");
		}
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}

    /*
     * Preenche a saida do grafo com 0 ou 1
     * para representar se os vertices tem ligações entre si.
     */
	private void preencheValoresGrafoNaoValorado(final StringBuilder grafo, int posicaoPai, int posicaoFilho) {
		if (_matriz[posicaoPai][posicaoFilho] == 1D) {
			grafo.append("|1|");
		} else {
			grafo.append("|0|");
		}
	}
	
    /*
     * Metodo que monta a cabelho da representação do grafo
     * com o identificador de cada vertice.
     */
	private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 0; i < _vertices.size(); i++) {
    		if (i == 0) {
    			grafo.append("  |");
    		} else {
    			grafo.append("| ");
    		}
    		grafo.append(_vertices.get(i).getId()).append("   |");
		}
    	grafo.append("\n");
    }
	
	/*
	 * Metodo que gera uma matriz apartir da lista de arestas.
	 */
	private void gerarMatriz() {
		/*
		 * Pega o tamanho da lista dos vertices para criar a matriz.
		 */
		final int tamanhoMatriz = _vertices.size();

		/*
		 * Cria a matriz adjacencia.
		 */
		_matriz = new double[tamanhoMatriz][tamanhoMatriz];

		for (Aresta aresta : _arestas){
			/*
			 * Pega os indices dos vertices inicial e final.
			 */
			final int indiceVerticeInicial = getIndiceDoVertice(aresta.getInicio());
			final int indiceVerticeFinal = getIndiceDoVertice(aresta.getFim());
			
			/*
			 * Pega o valor da aresta e adiciona na matriz.
			 */
			final double valor = isValorado() ? aresta.getPeso() : 1D;
			_matriz[indiceVerticeInicial][indiceVerticeFinal] = valor;
		}
	}

}
