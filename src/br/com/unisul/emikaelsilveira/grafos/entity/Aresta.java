package br.com.unisul.emikaelsilveira.grafos.entity;

public class Aresta {

	private Vertice _inicio;
    private Vertice _fim;

    public Aresta(Vertice inicio, Vertice fim) {
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
