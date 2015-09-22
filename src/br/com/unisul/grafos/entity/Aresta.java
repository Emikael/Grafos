package br.com.unisul.grafos.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
 * Classe Aresta
 * Controla as informações sobre cada aresta
 * e a desenha na tela.
 */
public class Aresta {

	private Vertice _inicio;
    private Vertice _fim;
    private Line2D _desenhoAresta;
    private Integer _peso;
    
    /*
     * Constante com um angulo padrão de cada vertice
     */
    public static final double ANGULO = Math.PI / 25d;

    /*
     * Construtor da classe.
     * Recebe um vertice inicial e um final para criação da aresta.
     * Calcula o angulo de cada vertice para o desenho da aresta.
     */
    public Aresta(Vertice inicio, Vertice fim, Integer peso) {
        this._inicio = inicio;
        this._fim = fim;
        this._peso = peso;
        
        double anguloInicio = anguloDosVertice(inicio.getCentroVertice(), fim.getCentroVertice()) - ANGULO;
        double anguloFim = anguloDosVertice(fim.getCentroVertice(), inicio.getCentroVertice()) - ANGULO;
        
        this._desenhoAresta = new Line2D.Double(inicio.getPontoDaCircunferenciaDoVertice(anguloInicio), fim.getPontoDaCircunferenciaDoVertice(anguloFim));
    }
    
    /*
     * Metodo que desenha a aresta na tela.
     */
    public void desenharAresta(Graphics2D graphics2D) {
    	graphics2D.fill(_desenhoAresta);
    	graphics2D.setStroke(new BasicStroke());
    	
    	graphics2D.setPaint(Color.BLACK);
    	graphics2D.draw(_desenhoAresta);
}

	public Vertice getInicio() {
		return _inicio;
	}

	public Vertice getFim() {
		return _fim;
	}
	
	/*
	 * Metodo que calcula o angula de um vertice.
	 * Para a aresta ser desenhada em cima da linha do desenho do vertice.
	 */
	public double anguloDosVertice(Point2D inicio, Point2D fim) {
		final double posicaoX = (double) (fim.getX() - inicio.getX());
		final double posicaoY = (double) (fim.getY() - inicio.getY());
		
		return Math.atan2(posicaoY, posicaoX);
	}

	public Integer getPeso() {
		return _peso;
	}

	public void setPeso(Integer peso) {
		this._peso = peso;
	}

}
