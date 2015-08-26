package br.com.unisul.emikaelsilveira.grafos.impl;

import br.com.unisul.emikaelsilveira.grafos.entity.Aresta;
import br.com.unisul.emikaelsilveira.grafos.entity.Vertice;

public class GrafoListaAdj extends GrafoService {
	
    public GrafoListaAdj() {
        super();
    }

    @Override
	String exibirGrafo() {
		final StringBuilder grafo = new StringBuilder();
    	Vertice verticeFim = new Vertice();
    	
    	for (Vertice verticeInicio : _vertices) {
            grafo.append(verticeInicio.getNome()).append(" --> ");
            
            for (Aresta aresta : verticeInicio.getListaAdjacentes()) {
                verticeFim = aresta.getFim();
                grafo.append(verticeFim.getNome()).append(", ");
            }
            
            if (grafo.toString().endsWith(", ")) {
            	grafo.delete(grafo.length() - 2, grafo.length());
            }

            grafo.append("\n");
        }
    	
        return grafo.toString();
	}

}
