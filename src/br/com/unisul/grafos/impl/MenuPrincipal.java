package br.com.unisul.grafos.impl;

public class MenuPrincipal {

	public static void main(String[] args) {
//		mostraGrafoAdjacente();
		mostraGrafoMatrizAdjacente();
//		mostraGrafoMatrizIncidencia();
		mostraGrafoMatrizTeste();
	}
	
	@SuppressWarnings("unused")
	private static void mostraGrafoMatrizTeste() {
		System.out.println("### Grafo Matriz de Incidencia ###");
		
		final GrafoService grafo = new GrafoMatrizAdj();
        Vertice a = grafo.addVertice("a");
        Vertice b = grafo.addVertice("b");
        Vertice c = grafo.addVertice("c");
        Vertice d = grafo.addVertice("d");
        Vertice e = grafo.addVertice("e");
        
        Aresta ac = grafo.addAresta(a, c);
        
        Aresta bc = grafo.addAresta(b, c);
        
        Aresta ca = grafo.addAresta(c, a);
        Aresta cb = grafo.addAresta(c, b);
        Aresta cd = grafo.addAresta(c, d);
        Aresta ce = grafo.addAresta(c, e);
        
        Aresta dc = grafo.addAresta(d, c);
        Aresta de = grafo.addAresta(d, e);
        
        Aresta ec = grafo.addAresta(e, c);
        Aresta ed = grafo.addAresta(e, d);
        
        System.out.println(grafo.exibirGrafo());
        
        System.out.println("---------------------------------------");
	}
	
	@SuppressWarnings("unused")
	private static void mostraGrafoMatrizIncidencia() {
		System.out.println("### Grafo Matriz de Incidencia ###");
		
		final GrafoService grafo = new GrafoMatrizIncidencia();
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
	
	@SuppressWarnings("unused")
	private static void mostraGrafoMatrizAdjacente() {
		System.out.println("### Grafo Matriz Adjacente ###");
		
		final GrafoMatrizAdj_old grafo = new GrafoMatrizAdj_old(5);
		grafo.addLigacao(0, 2);
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
		
		System.out.println("---------------------------------------");
	}

	@SuppressWarnings("unused")
	private static void mostraGrafoAdjacente() {
		System.out.println("### Grafo Lista de Adjacencia ###");
		
		final GrafoService grafo = new GrafoListaAdj();
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
