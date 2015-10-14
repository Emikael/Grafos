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
    private int _valorTabelaAscii = 64;
    
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
    public void adicionarAresta(Vertice inicio, Vertice fim, boolean direcionado, Double peso, boolean valorado) {
    	/*
    	 * Cria e adiciona a aresta no grafo.
    	 */
    	final Aresta aresta = new Aresta(inicio, fim, direcionado, peso, valorado);
        inicio.adicionaAdj(aresta);
        _arestas.add(aresta);
        
        /*
         * Case a aresta não seja direcionada cria outra aresta
         * ligando o vertice final com o inicial.
         */
        if (!direcionado) {
        	final Aresta arestaAdjacente = new Aresta(fim, inicio, direcionado, peso, valorado);
            fim.adicionaAdj(arestaAdjacente);
            _arestas.add(arestaAdjacente);
            return;
		} 
        
        /*
         * Se a aresta for direcionada verifica se existe outra aresta
         * ligando o mesmo vertice, caso existe seta a curvatura das arestas.
         */
		Aresta arestaAdjcente = existeArestaComOsVerticfes(fim, inicio);
		
		if (arestaAdjcente != null) {
			aresta.setCurvatura(1);
			arestaAdjcente.setCurvatura(1);
		}
    }
    
    /*
     * Verifica se existe alguma aresta ligando os vertices final e inicial.
     */
    private Aresta existeArestaComOsVerticfes(Vertice fim, Vertice inicio) {
    	for (Aresta aresta : _arestas) {
			if (aresta.getInicio().getId() == fim.getId() && aresta.getFim().getId() == inicio.getId()) {
				return aresta;
			}
		}
    	
    	return null;
	}

	/*
     * Metodo que adiciona uma aresta a partir de posições selecionadas na tela.
     * Para cada posição passada como parametro
     * irá busca o vertice daquela posição.
     */
    public void adicionarAresta(Point2D ponto1, Point2D ponto2, boolean direcionado, Double peso, boolean valorado) {
    	final Vertice verticePonto1 = buscaVerticePelo(ponto1);
    	final Vertice verticePonto2 = buscaVerticePelo(ponto2);
    	
    	if (verticePonto1 == null && verticePonto2 == null) {
    		return;
    	}
    	
    	adicionarAresta(verticePonto1, verticePonto2, direcionado, peso, valorado);
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
    	/*
    	 * Desenhas os vertices na tela.
    	 */
    	for (Vertice vertice : _vertices) {
			vertice.desenharVertice(graphics2D);
		}

    	/*
    	 * Desenha as arestas na tela.
    	 */
    	for (Aresta aresta : _arestas) {
    		aresta.desenhaAresta(graphics2D);
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
    private String getId() {
    	/*
    	 * Transforma um valor inteiro em caracter com base na tabela ASCII.
    	 */
		_valorTabelaAscii++;
		final String id = new Character((char)_valorTabelaAscii).toString();
		
		return id;
    }
    
    public List<Vertice> getVertices() {
    	return _vertices;
    }
    
    public List<Aresta> getArestas() {
    	return _arestas;
    }
    
}
