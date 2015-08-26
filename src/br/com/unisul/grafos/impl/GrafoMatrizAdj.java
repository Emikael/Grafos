package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizAdj implements GrafoService {
	
	private List<Vertice> _vertices;
    private List<Aresta> _arestas;

    public GrafoMatrizAdj() {
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
    		grafo.append(vertice.getNome()).append("");
    		buscaLigacao(vertice, grafo);
        }
    	
        return grafo.toString();
	}

	private void buscaLigacao(Vertice vertice, StringBuilder grafo) {
		for (Vertice verticeFim : _vertices) {
			if (verticeFim.temLigacao(vertice)) {
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
