package br.com.unisul.emikaelsilveira.grafos.impl;

import br.com.unisul.emikaelsilveira.grafos.entity.Aresta;

public class GrafoListaArestas extends GrafoService {

    public GrafoListaArestas() {
        super();
    }
    
    @Override
	String exibirGrafo() {
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
