package br.com.unisul.grafos.impl;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import br.com.unisul.grafos.entity.Vertice;

/*
 * Classe responsavel por realizar calculos para o grafo.
 */
public class Utils {

	/*
	 * Retorna o angulo no vertice final que esta mais proximo do
	 * vertice inicial.
	 */
	public static double getAngulo(Point2D centroInicial, Point2D centroFinal) {
		/*
		 * Pega as coordenadas X e Y dos centros dos vertices.
		 */
		final double coordenadaX = (double) (centroFinal.getX() - centroInicial.getX());
		final double coordenadaY = (double) (centroFinal.getY() - centroInicial.getY());
		
		/*
		 * Retorna o angulo da conversão das coordenadas X e Y.
		 */
		return Math.atan2(coordenadaY, coordenadaX);
	}

	/*   
	 * Retorna o ponto da circunferencia de um vertice que é correspondente
	 * a um determinado angulo da circunferencia
	 */
	public static Point2D getPontoNoVertice(Point2D centrovertice, double angulo) {
		/*
		 * Cria um ponto com as coordenadas X e Y do centro do vertice.
		 */
		final Point2D ponto = new Point2D.Double(centrovertice.getX(), centrovertice.getY());
		
		/*
		 * Pega as coordenadas X e Y multiplicando com o centro do vertice.
		 */
		final double coordenadaX = Math.cos(angulo) * (Vertice.LARGURA / 2);
		final double coordenadaY = Math.sin(angulo) * (Vertice.LARGURA / 2);
		
		/*
		 * Seta a nova posição do ponto em que o vertice está localizado.
		 */
		ponto.setLocation(ponto.getX() + coordenadaX, ponto.getY() + coordenadaY);
		return ponto;
	}

	/*
	 * Calcula a posição do texto de uma Aresta com base nos vertices conectados e no ponto de conexão Em Curca 
	 */
	public static Point2D getPontoTextoAresta(Point2D pontoInicial, Point2D pontoFinal, Point2D pontoConexaoEmCurva){
		/*
		 * Pega as coordenadas X e Y com base nos pontos iniciais e finais dos vertices. 
		 */
		double coordenadaX = Math.min(pontoInicial.getX(), pontoFinal.getX()) + Math.abs((pontoInicial.getX() - pontoFinal.getX()) / 2);
		double coordenadaY = Math.min(pontoInicial.getY(), pontoFinal.getY()) + Math.abs((pontoInicial.getY() - pontoFinal.getY()) / 2);

		/*
		 * Seta as novas coordenadas X e Y.
		 */
		coordenadaX = Math.min(coordenadaX, pontoConexaoEmCurva.getX()) + Math.abs((coordenadaX - pontoConexaoEmCurva.getX()) / 2);
		coordenadaY = Math.min(coordenadaY, pontoConexaoEmCurva.getY()) + Math.abs((coordenadaY - pontoConexaoEmCurva.getY()) / 2);

		/*
		 * Retorna um ponto2D com base as coordenadas X e Y
		 * onde ficará a area que o valor da aresta será desenhado.
		 */
		return new Point2D.Double(coordenadaX, coordenadaY);
	}
	
	/*
	 * Cria um separador na vertical "|" na tela.
	 */
	public static JComponent criaSeparadorVertical() {
        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(3,25));
        return separador;
    }
	
}