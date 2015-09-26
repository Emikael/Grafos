package br.com.unisul.grafos.impl;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe Grafo
 * Controla todas as ações do grafo.
 */
public class Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
    
    /*
     * Construtor da classe.
     * Inicializa as listas de vertices e arestas.
     */
    public Grafo() {
    	_vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }
    
    /*
     * Metodo que adiciona um vertice no grafo.
     */
    public void adicionarVertice(Point2D ponto) {
        final Vertice vertice = new Vertice(getId(), ponto.getX(), ponto.getY());
        _vertices.add(vertice);
    }
	
    /*
     * Metodo que adiciona uma arestas no grafo.
     */
    public void adicionarAresta(Vertice inicio, Vertice fim, boolean direcionado) {
    	final Aresta aresta = new Aresta(inicio, fim, 1);
        inicio.adicionaAdj(aresta);
        _arestas.add(aresta);
        
        /*
         * Metodo recursivo para adicionar uma aresta 
         * quando o grafo não for direcionado.
         * Assim terá duas arestas
         * Inicial -> Final
         * Final -> Inicial
         */
        if (!direcionado) {
			adicionarAresta(fim, inicio, true);
		}
    }
    
    /*
     * Metodo que adiciona uma aresta apartir de posições selecionadas na telayj
     * Para cada posição passada como parametro
     * irá busca o vertice daquela posição.
     */
    public void adicionarAresta(Point2D ponto1, Point2D ponto2, boolean direcionado) {
    	final Vertice verticePonto1 = buscaVerticePelo(ponto1);
    	final Vertice verticePonto2 = buscaVerticePelo(ponto2);
    	
    	if (verticePonto1 == null && verticePonto2 == null) {
    		return;
    	}
    	
    	adicionarAresta(verticePonto1, verticePonto2, direcionado);
    }
    
    /*
     * Metodo que busca o vertice que está na posição
     * passada como parametro.
     */
    private Vertice buscaVerticePelo(Point2D ponto) {
    	for (Vertice vertice : _vertices) {
			if (vertice.isExisteVerticeNo(ponto)) {
				return vertice;
			}
		}
    	
    	return null;
    }
    
    /*
     * Metodo que desenha o grafo na tela.
     */
    public void desenharGrafo(Graphics2D graphics2D) {
    	for (Vertice vertice : _vertices) {
			vertice.desenharVertice(graphics2D);
		}

    	for (Aresta aresta : _arestas) {
    		aresta.desenharAresta(graphics2D);
    	}
    }
    
    /*
     * Metodo que verifica se no ponto passado como parametro
     * existe algum vertice.
     */
    public boolean isExisteVerticeNo(Point2D ponto) {
    	for (Vertice vertice : _vertices) {
			if (vertice.isExisteVerticeNo(ponto)) {
				return true;
			}
		}
    	
    	return false;
    }
    
    /*
     * Metodo que retorna um identificador para o vertice.
     */
    private int getId() {
    	return _vertices.size() + 1;
    }
    
    public List<Vertice> getVertices() {
    	return _vertices;
    }
    
    public List<Aresta> getArestas() {
    	return _arestas;
    }
    
}
