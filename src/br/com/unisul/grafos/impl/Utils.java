package br.com.unisul.grafos.impl;

import java.awt.geom.Point2D;

import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe responsavel por realizar calculos para o grafo.
 */
public class Utils {

	/*
	 * Retorna o angulo no vertice final que esta mais praximo do
	 * vertice inicial.
	 */
	public static double getAngulo(Point2D centroInicial, Point2D centroFinal) {
		final double posicaoX = (double) (centroFinal.getX() - centroInicial.getX());
		final double posicaoY = (double) (centroFinal.getY() - centroInicial.getY());
		
		return Math.atan2(posicaoY, posicaoX);
	}

	/*   
	 * Retorna o ponto da circunferencia de um vertice que é correspondente
	 * a um determinado angulo dessa circunferencia
	 */
	public static Point2D getPontoNoVertice(Point2D centrovertice, double angulo) {
		final Point2D ponto = new Point2D.Double(centrovertice.getX(), centrovertice.getY());
		final double posicaoX = Math.cos(angulo) * (Vertice.LARGURA / 2);
		final double posicaoY = Math.sin(angulo) * (Vertice.LARGURA / 2);
		
		ponto.setLocation(ponto.getX() + posicaoX, ponto.getY() + posicaoY);
		return ponto;
	}

	/*
	 * Calcula a posição do texto de uma Aresta com base nos vertices conectados e no ponto de conexão Em Curca 
	 */
	public static Point2D getPontoTextoAresta(Point2D pontoInicial, Point2D pontoFinal, Point2D pontoConexaoEmCurva){
		double posicaoX = Math.min(pontoInicial.getX(), pontoFinal.getX()) + Math.abs((pontoInicial.getX() - pontoFinal.getX()) / 2);
		double posicaoY = Math.min(pontoInicial.getY(), pontoFinal.getY()) + Math.abs((pontoInicial.getY() - pontoFinal.getY()) / 2);

		posicaoX = Math.min(posicaoX, pontoConexaoEmCurva.getX()) + Math.abs((posicaoX - pontoConexaoEmCurva.getX()) / 2);
		posicaoY = Math.min(posicaoY, pontoConexaoEmCurva.getY()) + Math.abs((posicaoY - pontoConexaoEmCurva.getY()) / 2);

		return new Point2D.Double(posicaoX, posicaoY);
	}
	
}