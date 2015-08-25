package br.com.unisul.grafos.impl;

public class MenuPrincipal {

	public static void main(String[] args) {
//		mostraGrafoAdjacente();
		mostraGrafoMatrizAdjacente();
	}
	
	private static void mostraGrafoMatrizAdjacente() {
		System.out.println("### Grafo Matriz Adjacente ###");
		
		final GrafoMatrizAdj grafo = new GrafoMatrizAdj(5);
		grafo.addLigacao(0,2);
		grafo.addLigacao(1, 2);
		grafo.addLigacao(2, 0);
		grafo.addLigacao(2, 1);
		grafo.addLigacao(2, 3);
		grafo.addLigacao(2, 4);
		grafo.addLigacao(3, 2);
		grafo.addLigacao(3, 4);
		grafo.addLigacao(4, 2);
		grafo.addLigacao(4, 3);
		
		System.out.println(grafo.exibirGrafo());
	}

	private static void mostraGrafoAdjacente() {
		System.out.println("### Grafo Lista de Adjacencia ###");
		
		final GrafoListaAdj grafo = new GrafoListaAdj();
        Vertice u = grafo.addVertice("u");
        Vertice v = grafo.addVertice("v");
        Vertice y = grafo.addVertice("y");
        Vertice x = grafo.addVertice("x");
        Vertice w = grafo.addVertice("w");
        
        Aresta uy = grafo.addAresta(u, y);
        Aresta uv = grafo.addAresta(u, v);
        
        Aresta vy = grafo.addAresta(v, y);
        Aresta vu = grafo.addAresta(v, u);
        Aresta vw = grafo.addAresta(v, w);

        Aresta yu = grafo.addAresta(y, u);
        Aresta yv = grafo.addAresta(y, v);
        Aresta yw = grafo.addAresta(y, w);
        Aresta yx = grafo.addAresta(y, x);
        
        Aresta xy = grafo.addAresta(x, y);
        Aresta xw = grafo.addAresta(x, w);
        
        Aresta wv = grafo.addAresta(w, v);
        Aresta wy = grafo.addAresta(w, y);
        Aresta wx = grafo.addAresta(w, x);
        
        System.out.println(grafo.exibirGrafo());
        
        System.out.println("---------------------------------------");
	}
}
