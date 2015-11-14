package br.com.unisul.grafos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unisul.grafos.entity.Aresta;
import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe do Grafo de Arvore Geradora.
 */
public class GrafoArvoreGeradora extends Grafo {
	
	private List<Vertice> _vertices;
	private List<Aresta> _arestas;
	private Map<Vertice, Vertice> _principal;
	private Map<Vertice, Integer> _profundidade; /* usada para armazenar as profundidades */

	/*
	 * Construtor da classe.
	 * Inicializa as listas de vertices e arestas.
	 */
	public GrafoArvoreGeradora(Grafo grafo) {
		this._vertices = grafo.getVertices();
		this._arestas = grafo.getArestas();
	}
	
	/*
	 * Método que as listas para calcular a arvore e a profundidade da arvore.
	 */
	private void inicializaArvore() {
		_principal = new HashMap<>();
		_profundidade = new HashMap<>();
		
		for (Vertice vertice : _vertices) {
			_principal.put(vertice, vertice);
			_profundidade.put(vertice, 1);
		}
	}
	
	/*
	 * Encontra conjunto de vertices a partir da lista principal.
	 */
	private Vertice encontrarConjunto(Vertice item) {
		final Vertice principal = _principal.get(item);
		if (principal == item) {
			return item;
		}

		return encontrarConjunto(principal);
	}
	
	/*
	 * Método que realiza a união entre os vertices inicial e final.
	 */
	private void uniao(Vertice verticeInicial, Vertice verticeFinal) {
		Vertice verticePrincipalInicio, verticePrincipalFinal;
		
		/*
		 * Enquanto o vertice inicial da lista principal for diferente do vertice inicial passado como
		 * parâmetro, o vertice inical recebe o vertice inicial da lista principal.
		 */
		while ((verticePrincipalInicio = _principal.get(verticeInicial)) != verticeInicial) {
			verticeInicial = verticePrincipalInicio;
		}
		
		/*
		 * Enquanto o vertice final da lista principal for diferente do vertice final passado como
		 * parâmetro, o vertice final recebe o vertice final da lista principal.
		 */
		while ((verticePrincipalFinal = _principal.get(verticeFinal)) != verticeFinal) {
			verticeFinal = verticePrincipalFinal;
		}

		/*
		 * Busca a profundidade na arvore do vertice inicial e final.
		 */
		final int profundidadeVerticeInicial = _profundidade.get(verticeInicial);
		final int profundidadeVerticeFinal = _profundidade.get(verticeFinal);
		
		/*
		 * Se o vertice inicial estiver a baixo do vertice final na arvore (com uma maior profundidade).
		 * É criada uma ligação entre os dois vertices tento o vertice final
		 * como pai do vertice inicial na arvore. 
		 */
		if (profundidadeVerticeInicial > profundidadeVerticeFinal) {
			_principal.put(verticeFinal, verticeInicial);
			aumentaAProfundidadeDo(verticeFinal);
			return;
		}
		
		/*
		 * Se o vertice final estiver a baixo do vertice inicial na arvore (com uma maior profundidade).
		 * É criada uma ligação entre os dois vertices tento o vertice inicial
		 * como pai do vertice final na arvore. 
		 */
		if (profundidadeVerticeFinal > profundidadeVerticeInicial) {
			_principal.put(verticeInicial, verticeFinal);
			aumentaAProfundidadeDo(verticeInicial);
			return;
		} 
		
		/*
		 * Cria uma ligação na arvore entre o vertice inicial e final, tendo
		 * o vertice final como pai do vertice inicial na arvore.
		 */
		_principal.put(verticeFinal, verticeInicial);
		aumentaAProfundidadeDo(verticeFinal);
	}
	
	/*
	 * Método recursivo que aumenta a profundidade do vertice na arvore.
	 */
	private void aumentaAProfundidadeDo(Vertice verticeAtual) {
		final int profundidadeDoVerticeAtual = _profundidade.get(verticeAtual);
		final Vertice verticePai = _principal.get(verticeAtual);
		final int profuncidadeDoVerticePai = _profundidade.get(verticePai);
		
		if (!(profundidadeDoVerticeAtual < profuncidadeDoVerticePai || verticePai == verticeAtual)) {
			_profundidade.put(verticePai, profundidadeDoVerticeAtual + 1);
			aumentaAProfundidadeDo(verticePai);
		}
	}
	
	/*
	 * Método que gera a arvore.
	 * Baseado no algoritmo de Kruskal.
	 */
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
		inicializaArvore();

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
