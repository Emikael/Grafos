package br.com.unisul.emikaelsilveira.grafos.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.unisul.emikaelsilveira.grafos.entity.Aresta;
import br.com.unisul.emikaelsilveira.grafos.entity.Vertice;

public abstract class GrafoService {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
    
    GrafoService() {
    	_vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }
    
    Vertice addVertice(String nome) {
        final Vertice vertice = new Vertice(nome);
        _vertices.add(vertice);
        
        return vertice;
    }
	
    Aresta addAresta(Vertice inicio, Vertice fim) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.addAdj(aresta);
        _arestas.add(aresta);
        
        return aresta;
    }
    
	abstract String exibirGrafo();

}
