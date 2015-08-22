package br.com.unisul.grafos.impl;

public class MenuPrincipal {

	public static void main(String[] args) {
		mostraGrafoAdjacente();
	}

	private static void mostraGrafoAdjacente() {
		System.out.println("### Grafo Lista de Adjacencia ###");
		
		final GrafoService g = new GrafoListaAdj();
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
        
        System.out.println(g.exibirGrafo());
        
        System.out.println("---------------------------------------");
	}
}
