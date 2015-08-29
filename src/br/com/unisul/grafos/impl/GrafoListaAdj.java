package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoListaAdj extends Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
	
    public GrafoListaAdj(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }

	public String exibiGrafo() {
		final StringBuilder grafo = new StringBuilder();
    	Vertice verticeFim = new Vertice();
    	
    	grafo.append("#### GRAFO LISTA DE ADJACENTE ####\n");
    	for (Vertice verticeInicio : _vertices) {
    		if (verticeInicio.getListaAdjacentes().isEmpty()) {
				continue;
			}
    		
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
    	
    	grafo.append("-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}

}
