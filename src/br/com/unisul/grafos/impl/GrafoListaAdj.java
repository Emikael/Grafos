package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

public class GrafoListaAdj {
	
	private List<Vertice> _vertices;
    private List<Aresta> _arestas;

    public GrafoListaAdj() {
        _vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }

    public Vertice addVertice(String nome) {
        final Vertice vertice = new Vertice(nome);
        _vertices.add(vertice);
        
        return vertice;
    }

    public Aresta addAresta(Vertice inicio, Vertice fim) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.addAdj(aresta);
        _arestas.add(aresta);
        
        return aresta;
    }

	public String exibirGrafo() {
		final StringBuilder grafo = new StringBuilder();
    	Vertice verticeFim = new Vertice();
    	
    	for (Vertice verticeInicio : _vertices) {
            grafo.append(verticeInicio.getNome()).append(" ---> ");
            
            for (Aresta aresta : verticeInicio.getListaAdjacentes()) {
                verticeFim = aresta.getFim();
                grafo.append(verticeFim.getNome()).append("-> ");
            }
            
            if (grafo.toString().endsWith("-> ")) {
            	grafo.delete(grafo.length() - 3, grafo.length());
            }

            grafo.append("\n");
        }
    	
        return grafo.toString();
	}

}
