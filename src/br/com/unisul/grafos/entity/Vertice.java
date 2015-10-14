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

/*
 * Classe Vertice.
 * Controla todas as ações dos vertices.
 */
public class Vertice implements Comparable<Vertice> {

	public static Double LARGURA = 40D;
	
	private String _id;
    private List<Aresta> _adjacente;
    private double _coordenadaX;
	private double _coordenadaY;
	private Shape _desenhoVertice;
	private Point2D _centroVertice;
	private Double _distancia;
	private boolean _visitado = false;
	private Vertice _pai;
    
    public Vertice() {
    	super();
    }

    /*
     * Construtor da classe.
     * Cria o desenho de um circulo para representar um vertice.
     * Calcula o centro do vertice.
     */
    public Vertice(String id, double coordenadaX, double coordenadaY) {
        this._id = id;
        this._adjacente = new ArrayList<Aresta>();
        this._coordenadaX = coordenadaX;
        this._coordenadaY = coordenadaY;
        this._desenhoVertice = new Ellipse2D.Double(_coordenadaX, _coordenadaY, LARGURA, LARGURA);
        this._centroVertice = new Point2D.Double();
		this._centroVertice.setLocation(_coordenadaX + (LARGURA / 2), _coordenadaY + (LARGURA / 2));
    }
    
    /*
     * Lista de arestas ligadas ao vertice.
     */
    public List<Aresta> getListaAdjacentes() {
    	return this._adjacente;
    }

    public void adicionaAdj(Aresta aresta) {
        _adjacente.add(aresta);
    }
    
    public String getId() {
    	return this._id;
    }
    
    /*
     * Metodo que desenha o vertice na tela.
     */
    public void desenharVertice(Graphics2D graphics2D) {
		graphics2D.setPaint(Color.WHITE);
		/*
		 * Preenche a area do desenha onde ficará o vertice.
		 */
		graphics2D.fill(_desenhoVertice);

		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.setPaint(Color.BLACK);
		
		/*
		 * Desenha o vertice na tela.
		 */
		graphics2D.draw(_desenhoVertice);
		
		/*
		 * Desenha o nome do vertice na tela.
		 */
		desenharNome(graphics2D);
	}
	
    /*
     * Metodo que desenha o nome do vertice no centro do vertice.
     */
	public void desenharNome(Graphics2D graphics2D) {
		final FontMetrics metrics = graphics2D.getFontMetrics();
		
		/*
		 * Desenha o nome do vertice na tela.
		 */
		graphics2D.drawString(_id, 
				(float) (_centroVertice.getX() - (metrics.stringWidth(_id) / 2) + 1), 
				(float) (_centroVertice.getY() + metrics.getHeight() - 10));
		
	}
	
	/*
	 * Metodo que verifica se na posição passada como parametro existe algum vertice.
	 */
	public boolean isExisteVerticeNo(Point2D ponto) {
		return _desenhoVertice.contains(ponto);
	}

	public Double getCoordenadaX() {
		return _coordenadaX;
	}

	public void setCoordenadaX(Double coordenadaX) {
		this._coordenadaX = coordenadaX;
	}

	public Double getCoordenadaY() {
		return _coordenadaY;
	}

	public void setCoordenadaY(Double coordenadaY) {
		this._coordenadaY = coordenadaY;
	}
	
	public Point2D getCentroVertice() {
		return _centroVertice;
	}

	public Double getDistancia() {
		return _distancia;
	}

	public void setDistancia(Double distancia) {
		this._distancia = distancia;
	}
	
	public void setVisitar(boolean visitar) {
		this._visitado = visitar;
	}
	
	public boolean isVisitado() {
		return this._visitado;
	}
	
	public Vertice getPai() {
		return _pai;
	}

	public void setPai(Vertice pai) {
		this._pai = pai;
	}
	
	/*
	 * Retorna um ponto2D com base na posição do vertice na tela.
	 */
	public Point2D getPonto() {
		return new Point2D.Double(getCoordenadaX(), getCoordenadaY());
	}
	
	@Override
	public int compareTo(Vertice vertice) {
		return this.getDistancia().compareTo(vertice.getDistancia());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertice) {
			Vertice vertice = (Vertice) obj;
			if(this.getId() == vertice.getId()){
				return true;
			}
		}
		return false;
	}
	
}
