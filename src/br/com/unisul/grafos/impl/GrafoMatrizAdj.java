package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoMatrizAdj extends Grafo {

	List<Vertice> _vertices;
    List<Aresta> _arestas;
	
    public GrafoMatrizAdj(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }
    
    public String exibiGrafo() {
		final StringBuilder grafo = new StringBuilder();
		
		grafo.append("#### GRAFO MATRIZ DE ADJACENCIA ####\n");
    	montaCabecalhoGrafo(grafo);
    	for (Vertice vertice : _vertices) {
    		grafo.append(vertice.getNome()).append("");
    		buscaLigacao(vertice, grafo);
        }
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}

	private void buscaLigacao(Vertice vertice, StringBuilder grafo) {
		for (Vertice verticeFim : _vertices) {
			if (vertice.temLigacao(verticeFim)) {
				grafo.append("|1|");
			} else {
				grafo.append("|0|");
			}
		}
		grafo.append("\n");
	}
	
	private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 0; i < _vertices.size(); i++) {
    		if (i == 0) {
    			grafo.append(" |");
    		} else {
    			grafo.append("|");
    		}
    		grafo.append(_vertices.get(i).getNome()).append("|");
		}
    	grafo.append("\n");
    }

}
