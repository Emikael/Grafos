package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

public class GrafoArvoreGeradora extends Grafo {
	
	private List<Vertice> _vertices;
	private List<Aresta> _arestas;
	private Map<Vertice, Vertice> _principal;
	private Map<Vertice, Integer> _profundidade; /* usada para armazenar as profundidades */

	public GrafoArvoreGeradora(Grafo grafo) {
		this._vertices = grafo.getVertices();
		this._arestas = grafo.getArestas();
	}
	
	private void inicializar() {
		_principal = new HashMap<>();
		_profundidade = new HashMap<>();
		
		for (Vertice vertice : _vertices) {
			_principal.put(vertice, vertice);
			_profundidade.put(vertice, 1);
		}
	}
	
	private Vertice encontrarConjunto(Vertice item) {
		final Vertice principal = _principal.get(item);
		if (principal == item) {
			return item;
		}

		return encontrarConjunto(principal);
	}
	
	private void uniao(Vertice verticeInicial, Vertice verticeFinal) {
		Vertice verticePrincipalInicio, verticePrincipalFinal;
		
		while ((verticePrincipalInicio = _principal.get(verticeInicial)) != verticeInicial) {
			verticeInicial = verticePrincipalInicio;
		}
		
		while ((verticePrincipalFinal = _principal.get(verticeFinal)) != verticeFinal) {
			verticeFinal = verticePrincipalFinal;
		}

		final int primeiraProfundidade = _profundidade.get(verticeInicial);
		final int segundoSegunda = _profundidade.get(verticeFinal);
		
		if (primeiraProfundidade > segundoSegunda) {
			_principal.put(verticeFinal, verticeInicial);
			atualizaProfundidadeParaCima(verticeFinal);
			return;
		}
		
		if (segundoSegunda > primeiraProfundidade) {
			_principal.put(verticeInicial, verticeFinal);
			atualizaProfundidadeParaCima(verticeInicial);
			return;
		} 
		
		_principal.put(verticeFinal, verticeInicial);
		atualizaProfundidadeParaCima(verticeFinal);
	}
	
	/*
	 * 
	 */
	private void atualizaProfundidadeParaCima(Vertice atual) {
		final int profundidadeAtual = _profundidade.get(atual);
		final Vertice paiAtual = _principal.get(atual);
		final int profuncidadePai = _profundidade.get(paiAtual);
		
		if (!(profundidadeAtual < profuncidadePai || paiAtual == atual)) {
			_profundidade.put(paiAtual, profundidadeAtual + 1);
			atualizaProfundidadeParaCima(paiAtual);
		}
	}
	
	public void geraArvoreDeKruskal() {
		/*
		 *  Inicializa arvore com um conjunto vazio.
		 *  A = ∅
		 */
		final ArrayList<Aresta> arvore = new ArrayList<Aresta>();

		/*
		 *  Para cada vertice v V[G] é montado um conjunto de vertices.
		 *  do Make-Set(v)
		 */
		inicializar();

		/*
		 * Ordena as arestas (ordem crescente pelo peso w).
		 */
		Collections.sort(_arestas);

		/*
		 *  Para cada aresta (u,v) pertencente ao Grafo 'E' buscado em ordem crescente.
		 *  ∀ edge (u,v) ∈ E, (considerando a ordem)
		 */
		for (Aresta aresta : _arestas) {
			/*
			 *  Verifica se não existe nenhum laço entre o vertice inicial(u) e o vertice final(v).
			 *  Caso não exista laço entre os vertices, adiciona a aresta a arvore.
			 *  If (Find-Set(u) != Find-Set(v)
			 */
			if (encontrarConjunto(aresta.getInicio()) != encontrarConjunto(aresta.getFim())) {
				/*
				 *  Adiciona a aresta a arvore geradora.
				 *  A = A {(u,v)} 
				 */
				arvore.add(aresta);
				/*
				 *  Realiza a união do vertice inicial com o vertice final.
				 *  Union(u,v)
				 */
				uniao(aresta.getInicio(), aresta.getFim());
			}
		}

		/*
		 * Limpa as arestas criadas na tela, e adiciona as novas arestas com base na arvore gerada.
		 */
		_arestas.clear();
		_arestas.addAll(arvore);
	}
}
