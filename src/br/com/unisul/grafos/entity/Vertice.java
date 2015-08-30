package br.com.unisul.grafos.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private int _id;
    private List<Aresta> _adjacente;
    private double _posicaoX;
	private double _posicaoY;
	private Shape _desenhoVertice;
	private Point2D _centroVertice;
    
    public Vertice() {
    	super();
    }

    public Vertice(int id, double posicaoX, double posicaoY) {
        this._id = id;
        this._adjacente = new ArrayList<Aresta>();
        this._posicaoX = posicaoX;
        this._posicaoY = posicaoY;
        this._desenhoVertice = new Ellipse2D.Double(_posicaoX, _posicaoY, 40, 40);
        this._centroVertice = new Point2D.Double();
		this._centroVertice.setLocation(_posicaoX + (40 / 2), _posicaoY + (40 / 2));
    }
    
    public List<Aresta> getListaAdjacentes() {
    	return this._adjacente;
    }

    public void adicionaAdj(Aresta aresta) {
        _adjacente.add(aresta);
    }
    
    public int getId() {
    	return this._id;
    }
    
    public boolean temLigacao(Vertice vertice) {
    	for (Aresta aresta : _adjacente) {
    		
    		if (this._posicaoX == vertice.get_posicaoX() && this._posicaoY == vertice.get_posicaoY()) {
				continue;
			}
    		for (Aresta arestaFim : vertice.getListaAdjacentes()) {
				if (aresta.getInicio().equals(arestaFim.getFim()) || aresta.getFim().equals(aresta.getInicio())) {
					return true;
				}
			}
    	}
    	return false;
    }
    
    public void desenharVertice(Graphics2D graphics2D) {
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fill(_desenhoVertice);

		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.setPaint(Color.BLACK);
		graphics2D.draw(_desenhoVertice);
		
		desenharNome(graphics2D);
	}
	
	public void desenharNome(Graphics2D graphics2D) {
		final FontMetrics metrics = graphics2D.getFontMetrics();
		
		graphics2D.drawString(String.valueOf(_id), 
				(float) (_centroVertice.getX() - (metrics.stringWidth(String.valueOf(_id)) / 2) + 1), 
				(float) (_centroVertice.getY() + metrics.getHeight() - 10));
		
	}
	
	public boolean isExisteVerticeNo(Point2D ponto) {
		return _desenhoVertice.contains(ponto);
	}
	
	public Point2D getPontoCircunferenciaDoVertice(double angulo) {
		final Point2D pontoDaCircunferencia = new Point2D.Double(_centroVertice.getX(), _centroVertice.getY());
		
		final double posicaoX = Math.cos(angulo) * (40 / 2);
		final double posicaoY = Math.sin(angulo) * (40 / 2); 
		pontoDaCircunferencia.setLocation(pontoDaCircunferencia.getX() + posicaoX, pontoDaCircunferencia.getY() + posicaoY);
		
		return pontoDaCircunferencia;
	}

	public double get_posicaoX() {
		return _posicaoX;
	}

	public void set_posicaoX(double _posicaoX) {
		this._posicaoX = _posicaoX;
	}

	public double get_posicaoY() {
		return _posicaoY;
	}

	public void set_posicaoY(double _posicaoY) {
		this._posicaoY = _posicaoY;
	}
	
	public Point2D getCentroVertice() {
		return _centroVertice;
	}
    
}
