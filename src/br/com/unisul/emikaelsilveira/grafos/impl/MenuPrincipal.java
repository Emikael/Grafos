package br.com.unisul.emikaelsilveira.grafos.impl;

import br.com.unisul.emikaelsilveira.grafos.entity.Aresta;
import br.com.unisul.emikaelsilveira.grafos.entity.Vertice;

public class MenuPrincipal {

	public static void main(String[] args) {
		geraGrafoAPartirDo(new GrafoListaAdj());
		geraGrafoAPartirDo(new GrafoMatrizAdj());
		geraGrafoAPartirDo(new GrafoMatrizIncidencia());
		geraGrafoAPartirDo(new GrafoListaArestas());
	}
	
	@SuppressWarnings("unused")
	private static void geraGrafoAPartirDo(final GrafoService grafo) {
		
        final Vertice u = grafo.addVertice("u");
        final Vertice v = grafo.addVertice("v");
        final Vertice y = grafo.addVertice("y");
        final Vertice x = grafo.addVertice("x");
        final Vertice w = grafo.addVertice("w");

        final Aresta uy = grafo.addAresta(u, y);
        final Aresta uv = grafo.addAresta(u, v);

        final Aresta vy = grafo.addAresta(v, y);
        final Aresta vu = grafo.addAresta(v, u);
        final Aresta vw = grafo.addAresta(v, w);

        final Aresta yu = grafo.addAresta(y, u);
        final Aresta yv = grafo.addAresta(y, v);
        final Aresta yw = grafo.addAresta(y, w);
        final Aresta yx = grafo.addAresta(y, x);

        final Aresta xy = grafo.addAresta(x, y);
        final Aresta xw = grafo.addAresta(x, w);

        final Aresta wv = grafo.addAresta(w, v);
        final Aresta wy = grafo.addAresta(w, y);
        final Aresta wx = grafo.addAresta(w, x);
        
        System.out.println(grafo.exibirGrafo());
        System.out.println("--------------------------------------------------------------------");
	}
}
