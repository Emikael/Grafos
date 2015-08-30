package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoMatrizAdj extends Grafo {

	List<Vertice> _vertices;
    List<Aresta> _arestas;
    int[][] _matriz;
	
    public GrafoMatrizAdj(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }
    
    public String exibiGrafo() {
		final StringBuilder grafo = new StringBuilder();
		
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE ADJACENCIA ####\n");
    	montaCabecalhoGrafo(grafo);

    	for (int i = 0; i < _matriz.length; i++) {
			grafo.append(_vertices.get(i).getId()).append("");
			for (int j = 0; j < _matriz.length; j++) {
				if (_matriz[i][j] == 1) {
					grafo.append("|1|");
				} else {
					grafo.append("|0|");
				}
			}
			
			grafo.append("\n");
		}
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}
	
	private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 0; i < _vertices.size(); i++) {
    		if (i == 0) {
    			grafo.append("  |");
    		} else {
    			grafo.append("|");
    		}
    		grafo.append(_vertices.get(i).getId()).append("|");
		}
    	grafo.append("\n");
    }
	
	private void gerarMatriz() {
		int tamanhoMatriz = _vertices.size();

		_matriz = new int[tamanhoMatriz][tamanhoMatriz];

		for (Aresta aresta : _arestas){
			_matriz[aresta.getInicio().getId() - 1][aresta.getFim().getId() - 1] = 1;
		}
	}

}
