package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoDeConexidade extends Grafo {

	private List<Vertice> _vertices;
	private int[][] _matriz;

	public GrafoDeConexidade(Grafo grafo) {
		this._vertices = grafo.getVertices();
	}

	public String exibiGrafo() {
		if (!isGrafoConexo()) {
			return "Grafo não é conexo!\n";
		}

		final StringBuilder grafo = new StringBuilder();
		gerarMatriz();
		grafo.append("#### GRAFO MATRIZ DE CONEXIDADE ####\n");
		montaCabecalhoGrafo(grafo);

		for (int i = 0; i < _matriz.length; i++) {
			grafo.append(_vertices.get(i).getId()).append("");
			for (int j = 0; j < _matriz.length; j++) {
				grafo.append("|").append(_matriz[i][j]).append("|");
			}

			grafo.append("\n");
		}

		grafo.append("-------------------------------------------------------------------\n");

		return grafo.toString();
	}

	private boolean isGrafoConexo() {
		for (Vertice vertice : _vertices) {
			if (vertice.getListaAdjacentes().isEmpty() && !isVerticeTemConexao(vertice)) {
				return false;
			}
		}

		return true;
	}

	private boolean isVerticeTemConexao(Vertice vertice) {
		for (Vertice verticeInicio : _vertices) {
			for (Aresta aresta : verticeInicio.getListaAdjacentes()) {
				if (aresta.getInicio().getId() == vertice.getId() || aresta.getFim().getId() == vertice.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	private void gerarMatriz() {
		int tamanhoMatriz = _vertices.size();

		_matriz = new int[tamanhoMatriz][tamanhoMatriz];

		_vertices.forEach(verticeInicio -> {
			_vertices.forEach(verticeFim -> {
				_matriz[verticeInicio.getId() - 1][verticeFim.getId() - 1] = getGrauDeConexidadeEntreOsVertices(verticeInicio, verticeFim);
			});
		});
	}

	private int getGrauDeConexidadeEntreOsVertices(Vertice verticeInicial, Vertice verticeFinal) {
		if (verticeInicial.getId() == verticeFinal.getId()) {
			return 0;
		}

		zeraVertices();
		List<Vertice> menorCaminho = encontrarMenorCaminhoParaO(verticeInicial, verticeFinal);
		return menorCaminho.size() - 1;
	}

	private void zeraVertices() {
		_vertices.forEach(vertice -> {
			vertice.setVisitar(false);
			vertice.setPai(null);
		});
	}

	public List<Vertice> encontrarMenorCaminhoParaO(Vertice verticeInicio, Vertice verticeFim) {
		List<Vertice> verticesVisitados = new ArrayList<Vertice>();
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		
		int verticesNaoVisitados = _vertices.size();
		Vertice verticeAtual = verticeInicio;
		verticesVisitados.add(verticeAtual);
		menorCaminho.add(verticeAtual);

		setaDistanciasDosVertices(verticeAtual);

		while (verticesNaoVisitados != 0) {

			verticeAtual = verticesVisitados.get(0);
			for (int i=0; i < verticeAtual.getListaAdjacentes().size(); i++) {

				final Vertice verticeVizinho = verticeAtual.getListaAdjacentes().get(i).getFim();                               
				
				if (verticeVizinho.isVisitado()) { continue; }
				
				verticeVizinho.setPai(verticeAtual);
				if (verticeVizinho.getDistancia() > (verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso())) {

					verticeVizinho.setDistancia(verticeAtual.getDistancia() + verticeAtual.getListaAdjacentes().get(i).getPeso());

					if (verticeVizinho == verticeFim) {
						menorCaminho.clear();
						Vertice verticeDoCaminho = verticeVizinho;
						menorCaminho.add(verticeVizinho);
						while (verticeDoCaminho.getPai() != null){
							menorCaminho.add(verticeDoCaminho.getPai());
							verticeDoCaminho = verticeDoCaminho.getPai();
						}
					}
				}

				verticesVisitados.add(verticeVizinho);
			}

			verticeAtual.setVisitar(true);
			verticesNaoVisitados--;
			verticesVisitados.remove(verticeAtual);
		}

		return menorCaminho;
	}

	private void setaDistanciasDosVertices(Vertice verticeAtual) {
		_vertices.forEach(vertice -> {
			if (vertice.getId() == verticeAtual.getId()) {
				vertice.setDistancia(0);
			} else {
				vertice.setDistancia(9999);
			}
		});
	}
	
	private void montaCabecalhoGrafo(StringBuilder grafo) {
    	for (int i = 0; i < _vertices.size(); i++) {
    		if (i == 0) {
    			grafo.append("  |");
    		} else {
    			grafo.append("|");
    		}
    		grafo.append(_vertices.get(i).getId()).append("|");
		}
    	grafo.append("\n");
    }

}		
