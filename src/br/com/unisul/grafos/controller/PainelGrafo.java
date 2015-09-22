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

/*
 * Classe do painel onde o grafo irá ser desenhado.
 */
public class PainelGrafo extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Grafo _grafo;
	private Tela _tela;
	private EstadoDaAresta _situacao;
	private Point2D _ponto1;
	
	/*
	 * Enum para indicar o estado da aresta
	 * NENHUM -> nenhuma vertice foi selecionada
	 * PONTO1_SELECIONADO -> a primeira vertice foi selecionada.
	 */
	public enum EstadoDaAresta {
		NENHUM, PONTO1_SELECIONADO
	}
	
	/*
	 * Contrutor da classe.
	 */
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
	
	/*
	 * Metodo que desenha o grafo na tela.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D) g.create();

		AffineTransform sat = graphics2D.getTransform();
        
		setBackground(Color.WHITE);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		/*
		 * Buscar as posições dos vertices e arestas para desenhar na tela
		 */
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

	/*
	 * Metodo que monitora o clique do mouse no painel
	 * Se o radioButton 'Vertice' estiver selecionado adiciona um vertice a tela.
	 * Se o radioButton 'Aresta' estiver selecionado adiciona uma aresta a tela.
	 */
	@Override
	public void mousePressed(MouseEvent evento) {
		if (evento.getButton() == MouseEvent.BUTTON1 && evento.getClickCount() == 1) {
			boolean isArestaDirecionada = _tela.getRadioDirecionado().isSelected();
			
			if (_tela.getRadioVertice().isSelected()) {
				_grafo.adicionarVertice(evento.getPoint());
				
			} else if (_tela.getRadioAresta().isSelected()){
				adicionarAresta(evento, isArestaDirecionada);
				_tela.getRadioDirecionado().setEnabled(false);
				_tela.getRadioNaoDirecionado().setEnabled(false);
			}
		}
		
		/*
		 * Chama o metodo paintComponent para redesenhar a tela
		 */
		this.repaint();
	}

	/*
	 * Metodo que verifica se um vertice já está selecionado e adiciona uma aresta
	 * ligando dois vertices na tela.
	 */
	private void adicionarAresta(MouseEvent evento, boolean isArestaDirecionada) {
		if (_situacao.equals(EstadoDaAresta.NENHUM)) {
			if (_grafo.isExisteVerticeNo(evento.getPoint())) {
				_ponto1 = new Point2D.Double(evento.getX(), evento.getY());
				_situacao = EstadoDaAresta.PONTO1_SELECIONADO;
			}
		} else {
			if (_grafo.isExisteVerticeNo(evento.getPoint())) {
				_grafo.adicionarAresta(_ponto1, evento.getPoint(), isArestaDirecionada);
				_situacao = EstadoDaAresta.NENHUM;
			
			} else {
				_situacao = EstadoDaAresta.NENHUM;
			}
		}
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
	
	/*
	 * Seta um novo objeto grafo ao painel.
	 * Usado para limpar a tela;
	 */
	public void setGrafo(Grafo grafo) {
		_grafo = grafo;
	}
	
}
