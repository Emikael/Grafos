package br.com.unisul.grafos.impl;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoMatrizIncidencia extends Grafo {
	
    public GrafoMatrizIncidencia() {
        super();
    }

    @Override
	public String toString() {
		final StringBuilder grafo = new StringBuilder();
    	
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
