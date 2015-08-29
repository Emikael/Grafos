package br.com.unisul.grafos.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Aresta {

	private Vertice _inicio;
    private Vertice _fim;
    private Line2D _desenhoAresta;
    
    public static final double ANGULO = Math.PI / 25d;

    public Aresta(Vertice inicio, Vertice fim) {
        this._inicio = inicio;
        this._fim = fim;
        
        double anguloInicio = anguloDosVertices(inicio.getCentroVertice(), fim.getCentroVertice()) - ANGULO;
        double anguloFim = anguloDosVertices(fim.getCentroVertice(), inicio.getCentroVertice()) - ANGULO;
        
        this._desenhoAresta = new Line2D.Double(inicio.getPontoCircunferenciaDoVertice(anguloInicio), fim.getPontoCircunferenciaDoVertice(anguloFim));
    }
    
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
	
	public double anguloDosVertices(Point2D inicio, Point2D fim) {
		final double posicaoX = (double) (fim.getX() - inicio.getX());
		final double posicaoY = (double) (fim.getY() - inicio.getY());
		
		return Math.atan2(posicaoY, posicaoX);
	}

}
