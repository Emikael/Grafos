package br.com.unisul.grafos.impl;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
    
    public Grafo() {
    	_vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }
    
    public Vertice adicionarVertice(Point2D ponto) {
        final Vertice vertice = new Vertice(getId(), ponto.getX(), ponto.getY());
        _vertices.add(vertice);
        
        return vertice;
    }
	
    public Aresta adicionarAresta(Vertice inicio, Vertice fim, boolean direcionado) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.adicionaAdj(aresta);
        _arestas.add(aresta);
        
        if (!direcionado) {
			adicionarAresta(fim, inicio, true);
		}
        
        return aresta;
    }
    
    public void adicionarAresta(Point2D ponto1, Point2D ponto2, boolean direcionado) {
    	final Vertice verticePonto1 = buscaVerticePelo(ponto1);
    	final Vertice verticePonto2 = buscaVerticePelo(ponto2);
    	
    	if (verticePonto1 == null && verticePonto2 == null) {
    		return;
    	}
    	
    	adicionarAresta(verticePonto1, verticePonto2, direcionado);
    }
    
    private Vertice buscaVerticePelo(Point2D ponto) {
    	for (Vertice vertice : _vertices) {
			if (vertice.isExisteVerticeNo(ponto)) {
				return vertice;
			}
		}
    	
    	return null;
    }
    
    public void desenharGrafo(Graphics2D graphics2D) {
    	for (Vertice vertice : _vertices) {
			vertice.desenharVertice(graphics2D);
		}

    	for (Aresta aresta : _arestas) {
    		aresta.desenharAresta(graphics2D);
    	}
    }
    
    public boolean isExisteVerticeNo(Point2D ponto) {
    	for (Vertice vertice : _vertices) {
			if (vertice.isExisteVerticeNo(ponto)) {
				return true;
			}
		}
    	
    	return false;
    }
    
    private int getId() {
    	return _vertices.size() + 1;
    }
    
    public List<Vertice> getVertices() {
    	return _vertices;
    }
    
}
