package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoMatrizIncidencia extends Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
	
    public GrafoMatrizIncidencia(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }

    public String exibiGrafo() {
		final StringBuilder grafo = new StringBuilder();
    	
		grafo.append("#### GRAFO MATRIZ DE INCIDENCIA ####\n");
		montaCabecalhoGrafo(grafo);
    	for (Vertice vertice : _vertices) {
    		grafo.append(vertice.getNome());
    		for (Aresta aresta : _arestas) {
				if (vertice.getNome().equals(aresta.getInicio().getNome())) {
					grafo.append("|  1  |");
					continue;
				}
				
				if (vertice.getNome().equals(aresta.getFim().getNome())) {
					grafo.append("| -1  |");
					continue;
				}
				
				grafo.append("|  0  |");
			}
    		
    		grafo.append("\n");
        }
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}
	
    private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 1; i <= _arestas.size(); i++) {
    		if (i == 1) {
    			grafo.append(" | ");
    		} else {
    			grafo.append("| ");
    		}
    		grafo.append("E").append(i);
    		
    		if (i > 9) {
    			grafo.append(" |");
    		} else {
    			grafo.append("  |");
    		}
		}
    	grafo.append("\n");
    }

}
