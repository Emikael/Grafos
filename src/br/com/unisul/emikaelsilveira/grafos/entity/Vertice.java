package br.com.unisul.emikaelsilveira.grafos.entity;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private String _nome;
    private List<Aresta> _adjacente;
    
    public Vertice() {
    	super();
    }

    public Vertice(String nome) {
        this._nome = nome;
        this._adjacente = new ArrayList<Aresta>();
    }
    
    public List<Aresta> getListaAdjacentes() {
    	return this._adjacente;
    }

    public void addAdj(Aresta aresta) {
        _adjacente.add(aresta);
    }
    
    public String getNome() {
    	return this._nome.toUpperCase();
    }
    
    public boolean temLigacao(Vertice vertice) {
    	for (Aresta aresta : _adjacente) {
    		for (Aresta arestaFim : vertice.getListaAdjacentes()) {
				if (aresta.getInicio().equals(arestaFim.getFim()) || aresta.getFim().equals(aresta.getInicio())) {
					return true;
				}
			}
    	}
    	return false;
    }
    
}
