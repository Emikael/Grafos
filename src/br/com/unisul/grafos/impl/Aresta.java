package br.com.unisul.grafos.impl;

public class Aresta {

	private Vertice _inicio;
    private Vertice _fim;

    Aresta(Vertice inicio, Vertice fim) {
        this._inicio = inicio;
        this._fim = fim;
    }

	public Vertice getInicio() {
		return _inicio;
	}

	public Vertice getFim() {
		return _fim;
	}

}
