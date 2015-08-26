package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizIncidencia implements GrafoService {
	
	private List<Vertice> _vertices;
    private List<Aresta> _arestas;

    public GrafoMatrizIncidencia() {
        _vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }

    @Override
    public Vertice addVertice(String nome) {
        final Vertice vertice = new Vertice(nome);
        _vertices.add(vertice);
        
        return vertice;
    }

    @Override
    public Aresta addAresta(Vertice inicio, Vertice fim) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.addAdj(aresta);
        _arestas.add(aresta);
        
        return aresta;
    }

    @Override
	public String exibirGrafo() {
		final StringBuilder grafo = new StringBuilder();
    	
		montaCabecalhoGrafo(grafo);
    	for (Vertice vertice : _vertices) {
    		grafo.append(vertice.getNome());
    		for (Aresta aresta : _arestas) {
				if (vertice.getNome().equals(aresta.getInicio().getNome())) {
					grafo.append("| 1   |");
					continue;
				}
				
				if (vertice.getNome().equals(aresta.getFim().getNome())) {
					grafo.append("| -1  |");
					continue;
				}
				
				grafo.append("| 0   |");
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
