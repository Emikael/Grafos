package br.com.unisul.grafos.impl;

import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe Grafo Lista de Arestas.
 */
public class GrafoListaArestas extends Grafo {

	List<Vertice> _vertices;
    List<Aresta> _arestas;
	
    /*
     * Construtor da classe.
     * Inicializa as listas de vertices e arestas.
     */
    public GrafoListaArestas(Grafo grafo) {
    	_vertices = grafo._vertices;
        _arestas = grafo._arestas;
    }
    
    /*
     * Metodo que monta e exibi a representação do grafo
     * no painel de saida.
     */
    public String exibiGrafo() {
		final StringBuilder arestasIniciais = new StringBuilder("g = (");
		final StringBuilder arestasFinais = new StringBuilder("h = (");
    	
		/*
		 * Pega todas os vertices iniciais e final das arestas.
		 */
		for (Aresta aresta : _arestas) {
			arestasIniciais.append(aresta.getInicio().getId()).append(", ");
			arestasFinais.append(aresta.getFim().getId()).append(", ");
		}
    	
    	if (arestasIniciais.toString().endsWith(", ")) {
			arestasIniciais.delete(arestasIniciais.length() - 2, arestasIniciais.length());
		}
    	
    	if (arestasFinais.toString().endsWith(", ")) {
    		arestasFinais.delete(arestasFinais.length() - 2, arestasFinais.length());
    	}
    	
    	arestasIniciais.append(");\n");
    	arestasFinais.append(");");
    	
    	final StringBuilder grafo = new StringBuilder();
    	grafo.append("#### GRAFO LISTA DE ARESTAS ####\n");
    	grafo.append(arestasIniciais.toString());
    	grafo.append(arestasFinais.toString());
    	
    	grafo.append("\n-------------------------------------------------------------------\n");
    	
        return grafo.toString();
	}

}
