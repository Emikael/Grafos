package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

public class GrafoListaArestas implements GrafoService {
	
	private List<Vertice> _vertices;
    private List<Aresta> _arestas;

    public GrafoListaArestas() {
        _vertices = new ArrayList<Vertice>();
        _arestas = new ArrayList<Aresta>();
    }

    @Override
    public Vertice addVertice(String nome) {
        final Vertice vertice = new Vertice(nome);
        _vertices.add(vertice);
        
        return vertice;
    }

    @Override
    public Aresta addAresta(Vertice inicio, Vertice fim) {
        final Aresta aresta = new Aresta(inicio, fim);
        inicio.addAdj(aresta);
        _arestas.add(aresta);
        
        return aresta;
    }

    @Override
	public String exibirGrafo() {
		final StringBuilder arestasIniciais = new StringBuilder("g = (");
		final StringBuilder arestasFinais = new StringBuilder("h = (");
    	
		for (Aresta aresta : _arestas) {
			arestasIniciais.append(aresta.getInicio().getNome()).append(", ");
			arestasFinais.append(aresta.getFim().getNome()).append(", ");
		}
    	
    	if (arestasIniciais.toString().endsWith(", ")) {
			arestasIniciais.delete(arestasIniciais.length() - 2, arestasIniciais.length());
		}
    	
    	if (arestasFinais.toString().endsWith(", ")) {
    		arestasFinais.delete(arestasFinais.length() - 2, arestasFinais.length());
    	}
    	
    	arestasIniciais.append(");\n");
    	arestasFinais.append(");");
    	
    	final String grafo = arestasIniciais.toString() + arestasFinais.toString();
    	
        return grafo;
	}

}
