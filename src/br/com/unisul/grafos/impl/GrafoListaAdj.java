package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.controller.MostraResultadoDoGrafo;
import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe Grafo Lista de Adjacencia.
 */
public class GrafoListaAdj extends Grafo {
	
	List<Vertice> _vertices;
    List<Aresta> _arestas;
	
    /*
     * Construtor da classe.
     * Inicializa as listas de vertices e arestas.
     */
    public GrafoListaAdj(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }

    /*
     * Metodo que monta e exibi a representação do grafo
     * no painel de saida.
     */
	public void exibiGrafo() {
    	Vertice verticeFim = new Vertice();
    	final Object[][] dados = new Object[_vertices.size()][_vertices.size() + 1];
    	
    	for (int indiceInicial = 0; indiceInicial < _vertices.size(); indiceInicial++) {
    		final Vertice verticeInicio = _vertices.get(indiceInicial);
    		
    		if (verticeInicio.getListaAdjacentes().isEmpty()) {
				continue;
			}
    		
    		dados[indiceInicial][0] = verticeInicio.getId();
    		
            /*
             * Preenche a saido do grafo com as vertices adjacentes.
             */
    		for (int indiceFinal = 0; indiceFinal < verticeInicio.getListaAdjacentes().size(); indiceFinal++) {
                final Aresta aresta = verticeInicio.getListaAdjacentes().get(indiceFinal);
    			verticeFim = aresta.getFim();
                dados[indiceInicial][indiceFinal + 1] = verticeFim.getId();
            }
        }
    	
    	/*
    	 * Gera as colunas para mostrar no resultado do Grafo
    	 */
    	final String[] colunas = getColunas();
    	
    	/*
    	 * Mostra o resultado do Grafo.
    	 */
    	MostraResultadoDoGrafo resultado = new MostraResultadoDoGrafo("Grafo Lista de Adjacencia", colunas, dados);
    	resultado.mostraResultado();
	}

	/*
	 * Gera as colunas para mostrar no resultado do grafo apartir do numero de vertices.
	 */
	private String[] getColunas() {
		String[] colunas = new String[_vertices.size()];
		for (int i = 0; i < _vertices.size(); i++) {
			colunas[i] = "->";
		}
		
		return colunas;
	}

}
