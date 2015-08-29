package br.com.unisul.grafos.impl;

import br.com.unisul.grafos.entity.Vertice;

public class GrafoMatrizAdj extends Grafo {

    public GrafoMatrizAdj() {
        super();
    }
    
    @Override
	public String toString() {
		final StringBuilder grafo = new StringBuilder();
    	montaCabecalhoGrafo(grafo);
    	for (Vertice vertice : _vertices) {
    		grafo.append(vertice.getNome()).append("");
    		buscaLigacao(vertice, grafo);
        }
    	
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
