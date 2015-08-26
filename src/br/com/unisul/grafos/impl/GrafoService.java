package br.com.unisul.grafos.impl;

public interface GrafoService {
	
	Vertice addVertice(String nome);
	Aresta addAresta(Vertice inicio, Vertice fim);
	String exibirGrafo();

}
