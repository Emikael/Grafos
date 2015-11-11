package br.com.unisul.grafos.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoArvoreGeradora extends Grafo {
	
	private List<Vertice> _vertices;
	private List<Aresta> _arestas;
	private int[][] _matriz;
	private TreeSet arvore;

	public GrafoArvoreGeradora(Grafo grafo) {
		this._vertices = grafo.getVertices();
		this._arestas = grafo.getArestas();
	}
	
	public void geraArvoreGeradora() {
		List<Aresta> arestas = _arestas;
		
		// Ordena pelo menor caminho....
		Collections.sort(arestas);
		arvore = new TreeSet<Aresta>();
		
		Iterator<Aresta> i = arestas.iterator();
		
		while(i.hasNext()) {
			
		}
		
	}
	
}
