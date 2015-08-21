package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;

    public Grafo() {
        _vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }

    private Vertice addVertice(String nome) {
        final Vertice vertice = new Vertice(nome);
        _vertices.add(vertice);
        
        return vertice;
    }

    private Aresta addAresta(Vertice inicio, Vertice fim) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.addAdj(aresta);
        _arestas.add(aresta);
        
        return aresta;
    }

    @Override
    public String toString() {
    	final StringBuilder grafo = new StringBuilder();
    	Vertice verticeFim = new Vertice();
    	
    	for (Vertice verticeInicio : _vertices) {
            grafo.append(verticeInicio.getNome()).append(" --> ");
            
            for (Aresta aresta : verticeInicio.getListaAdjacentes()) {
                verticeFim = aresta._fim;
                grafo.append(verticeFim.getNome()).append("-> ");
            }
            
            if (grafo.toString().endsWith("-> ")) {
            	grafo.delete(grafo.length() - 3, grafo.length());
            }

            grafo.append("\n");
        }
    	
        return grafo.toString();
    }

    public static void main(String[] args) {
        final Grafo g = new Grafo();
        Vertice s = g.addVertice("s");
        Vertice t = g.addVertice("t");
        Vertice y = g.addVertice("y");
        Vertice u = g.addVertice("u");
        Aresta st = g.addAresta(s, t);
        Aresta sy = g.addAresta(s, y);
        Aresta ty = g.addAresta(t, y);
        Aresta yt = g.addAresta(y, t);
        Aresta su = g.addAresta(s, u);
        Aresta ut = g.addAresta(u, t);
        
        System.out.println(g);
    }

}
