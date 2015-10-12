package br.com.unisul.grafos.impl;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
 * Classe que desenha a seta na aresta.
 */
public class Seta {
	
	public static double ANGULO_SETA = Math.PI / 15d;
	public static double COMPRIMENTO_SETA = 10d;

	private Line2D _linha1;
	private Line2D _linha2;

	public Seta(){
		_linha1 = new Line2D.Float();
		_linha2 = new Line2D.Float();
	}

	public void desenhar(Graphics2D graphics2D, boolean isMesmoVertice){
		if (isMesmoVertice) { return; }

		graphics2D.draw(_linha1);
		graphics2D.draw(_linha2);
	}

	/*
	 * Calcula o formato e a posição da Seta
	 * parametro 'controle' o ponto de controle da Aresta (ponto onde ela faz a curva) 
	 * @param fim
	 */
	public void calcular(Point2D controleDeCurva, Point2D pontoFinal, boolean isMesmoVertice) {
		if (isMesmoVertice) { return; }
		
		double angle = Math.atan2((controleDeCurva.getX() - pontoFinal.getX()), (controleDeCurva.getY() - pontoFinal.getY()));
		angle += ANGULO_SETA;
		double endX = Math.sin(angle) * COMPRIMENTO_SETA + pontoFinal.getX();
		double endY = Math.cos(angle) * COMPRIMENTO_SETA + pontoFinal.getY();

		_linha1.setLine(pontoFinal.getX(), pontoFinal.getY(), endX, endY);

		angle -= 2 * ANGULO_SETA;
		endX = Math.sin(angle) * COMPRIMENTO_SETA + pontoFinal.getX();
		endY = Math.cos(angle) * COMPRIMENTO_SETA + pontoFinal.getY();

		_linha2.setLine(pontoFinal.getX(), pontoFinal.getY(), endX, endY);
	}


}
