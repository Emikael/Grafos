package br.com.unisul.grafos.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import br.com.unisul.grafos.impl.Grafo;

public class PainelGrafo extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Grafo _grafo;
	private Tela _tela;
	private EstadoDaAresta _situacao;
	private Point2D _ponto1;
	
	public enum EstadoDaAresta {
		NENHUM, PONTO1_SELECIONADO
	}
	
	
	public PainelGrafo(Tela tela, Grafo grafo) {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder("Grafo"));
		this.setSize(new Dimension(900, 200));
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.requestFocusInWindow();
		
		this._tela = tela;
		this._grafo = grafo;
		this._situacao = EstadoDaAresta.NENHUM;
		
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D) g.create();

		AffineTransform sat = graphics2D.getTransform();

		AffineTransform at = new AffineTransform();
		at.scale(100 / 100.0, 100 / 100.0);
		graphics2D.transform(at);
        
		setBackground(Color.WHITE);
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		_grafo.desenharGrafo(graphics2D);
		
		graphics2D.setTransform(sat);

		graphics2D.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent evento) {
		if (evento.getButton() == MouseEvent.BUTTON1 && evento.getClickCount() == 1) {
			if (_tela.getRadioVertice().isSelected()) {
				_grafo.adicionarVertice(evento.getPoint());
			
			} else if (_tela.getRadioAresta().isSelected()){
				
				if (_situacao.equals(EstadoDaAresta.NENHUM)) {
					if (_grafo.isExisteVerticeNo(evento.getPoint())) {
						_ponto1 = new Point2D.Double(evento.getX(), evento.getY());
						_situacao = EstadoDaAresta.PONTO1_SELECIONADO;
					}
				} else {
					if (_grafo.isExisteVerticeNo(evento.getPoint())) {
						_grafo.adicionarAresta(_ponto1, evento.getPoint());
						_situacao = EstadoDaAresta.NENHUM;
					
					} else {
						_situacao = EstadoDaAresta.NENHUM;
					}
				}
			}
		}
		
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public void limpaPainel() {
		this.removeAll();
	}

}
