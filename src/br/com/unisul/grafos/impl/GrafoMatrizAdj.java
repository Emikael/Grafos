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
    boolean _isValorado;

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
		
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE ADJACENCIA ####\n");
    	montaCabecalhoGrafo(grafo);

    	for (int i = 0; i < _matriz.length; i++) {
			grafo.append(_vertices.get(i).getId()).append("");
			for (int j = 0; j < _matriz.length; j++) {
				if (_isValorado) {
					grafo.append("|").append(_matriz[i][j]).append("|");
				} else {
					preencheValoresGrafoNaoValorado(grafo, i, j);
				}
			}
			
			grafo.append("\n");
		}
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}

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
		int tamanhoMatriz = _vertices.size();

		_matriz = new double[tamanhoMatriz][tamanhoMatriz];

		_isValorado = _arestas.get(0).isValorado();
		
		for (Aresta aresta : _arestas){
			double valor = _isValorado ? aresta.getPeso() : 1D;
			_matriz[aresta.getInicio().getId() - 1][aresta.getFim().getId() - 1] = valor;
		}
	}

}
