package br.com.unisul.grafos.impl;

public class GrafoMatrizAdj_old {

	private boolean[][] _grafo;
	
    public GrafoMatrizAdj_old(int tamanho) {
    	_grafo = new boolean[tamanho][tamanho];
    }
    
    public void addLigacao(int pai, int filho) {
    	_grafo[pai][filho] = true;
    }
    
    private boolean isTemLigacao(int pai, int filho) {
    	return _grafo[pai][filho];
    }

	public String exibirGrafo() {
		StringBuilder grafo = new StringBuilder();

		for (int i = 0; i < _grafo.length; i++) {
			for (int j = 0; j < _grafo.length; j++) {
				if (isTemLigacao(i, j)) {
					grafo.append("|1|");
				} else {
					grafo.append("|0|");
				}
			}
			
			grafo.append("\n");
		}
		
		return grafo.toString();
	}

}
