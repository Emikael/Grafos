package br.com.unisul.grafos.impl;

public class GrafoMatrizAdj {

	private boolean[][] _grafo;
    private int _tamanho;
	
    public GrafoMatrizAdj(int tamanho) {
    	_grafo = new boolean[tamanho][tamanho];
    	_tamanho = tamanho;
    }
    
    public void addLigacao(int pai, int filho) {
    	_grafo[pai][filho] = true;
    }
    
    public boolean isTemLigacao(int pai, int filho) {
    	return _grafo[pai][filho];
    }

	public String exibirGrafo() {
		StringBuilder grafo = new StringBuilder();

		for (int i = 0; i < _tamanho; i++) {
			for (int j = 0; j < _tamanho; j++) {
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
