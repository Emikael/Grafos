package br.com.unisul.grafos.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.unisul.grafos.impl.GrafoDeConexidade;
import br.com.unisul.grafos.impl.Grafo;
import br.com.unisul.grafos.impl.GrafoListaAdj;
import br.com.unisul.grafos.impl.GrafoListaArestas;
import br.com.unisul.grafos.impl.GrafoMatrizAdj;
import br.com.unisul.grafos.impl.GrafoMatrizIncidencia;

/*
 *	TELA DA APLICAÇÂO 
 *
 */
public class Tela extends JFrame {

	private static final String GRAFOS = "### GRAFOS ###\n";
	private static final long serialVersionUID = 1L;
	private JPanel _painelInfo;
	private JPanel _painelGrafo;
	private JPanel _painelBotoes;
	private JPanel _painelInfoGrafo;
	private JPanel _painelSaidaDoGrafo;
	
	private JRadioButton _radioDirecionado;
	private JRadioButton _radioNaoDirecionado;
	private JRadioButton _radioVertice;
	private JRadioButton _radioAresta;
	
	private JButton _botaoListaAdj;
	private JButton _botaoMatrizAdj;
	private JButton _botaoMatrizIncidencia;
	private JButton _botaoListaArestas;
	private JButton _botaoNovoGrafo;
	private JButton _botaoConexidade;
	
	private JTextArea _saidaDoGrafo;
	
	private Grafo _grafo;
	
	public Tela() {
		
	}
	
	/*
	 * Contrutor da classe
	 */
	public Tela(String titulo) throws HeadlessException {
		
		setLayout(new BorderLayout());
		setTitle(titulo);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000,670));
		setPreferredSize(getSize());
		setLocationRelativeTo(null);
		setResizable(true);
		
		/*
		 * Cria uma nova instancia do Grafo
		 */
		_grafo = new Grafo();
		
		getContentPane().add(getPainelInfo(), BorderLayout.NORTH);
		getContentPane().add(getPainelGrafo(), BorderLayout.CENTER);
		getContentPane().add(getPainelInfoGrafo(), BorderLayout.WEST);
		getContentPane().add(getPainelSaidaDoGrafo(), BorderLayout.EAST);
		getContentPane().add(getPainelBotoes(), BorderLayout.SOUTH);
	}
	
	/*
	 * Cria painel para a saida do grafo
	 */
	private JPanel getPainelSaidaDoGrafo() {
		if (_painelSaidaDoGrafo == null) {
			_painelSaidaDoGrafo = new JPanel();
			
			_painelSaidaDoGrafo.setBorder(BorderFactory.createTitledBorder("Resultado do grafo"));
			_painelSaidaDoGrafo.setLayout(new FlowLayout());
			_painelSaidaDoGrafo.setSize(new Dimension(200, 150));
			
			_saidaDoGrafo = new JTextArea(GRAFOS, 45, 30);
			_saidaDoGrafo.setEditable(false);
			JScrollPane scrollPanel = new JScrollPane(_saidaDoGrafo);
			
			_painelSaidaDoGrafo.add(scrollPanel);
		}
		
		return _painelSaidaDoGrafo;
	}
	
	/*
	 * Cria painel com as informações do grafo.
	 * Se é para inserir um vertice ou aresta no grafo
	 */
	private JPanel getPainelInfoGrafo() {
		if (_painelInfoGrafo == null) {
			_painelInfoGrafo = new JPanel();
			
			_painelInfoGrafo.setBorder(BorderFactory.createTitledBorder("Inserir no Grafo"));
			_painelInfoGrafo.setLayout(new FlowLayout());
			_painelInfoGrafo.setSize(new Dimension(50, 200));
			
			_radioVertice = new JRadioButton("Vertice");
			_radioAresta = new JRadioButton("Aresta");
			_radioVertice.setSelected(true);
			
			final ButtonGroup grupoRadio = new ButtonGroup();
			grupoRadio.add(_radioVertice);
			grupoRadio.add(_radioAresta);
			
			_painelInfoGrafo.add(_radioVertice);
			_painelInfoGrafo.add(_radioAresta);
			
		}
		
		return _painelInfoGrafo;
	}
	
	/*
	 * Cria painel de informação da aplicação.
	 * Botão Novo Grafo -> Limpa a tela para ser gerado outro grafo
	 * Opções de grafo 'Direcionado' ou 'Não direcionado'
	 */
	private JPanel getPainelInfo() {
		if (_painelInfo == null) {
			_painelInfo = new JPanel();
			
			_painelInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			_painelInfo.setLayout(new FlowLayout());
			_painelInfo.setSize(new Dimension(100, 50));
			
			_botaoNovoGrafo = new JButton("Novo Grafo");
			_botaoNovoGrafo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int escolha = JOptionPane.showConfirmDialog(null,"Deseja criar um novo grafo? ","Novo Grafo",JOptionPane.YES_NO_OPTION); 
					if (escolha == JOptionPane.YES_OPTION) {
						limpaTela();
						_radioDirecionado.setEnabled(true);
						_radioNaoDirecionado.setEnabled(true);
					}
				}
			});
			
			_painelInfo.add(_botaoNovoGrafo);
			
			_radioDirecionado = new JRadioButton("Direcionado");
			_radioNaoDirecionado = new JRadioButton("Não direcionado");
			_radioNaoDirecionado.setSelected(true);
			_radioDirecionado.setEnabled(false);
			_radioNaoDirecionado.setEnabled(false);
			
			final ButtonGroup grupoRadio = new ButtonGroup();
			grupoRadio.add(_radioDirecionado);
			grupoRadio.add(_radioNaoDirecionado);
			
			_painelInfo.add(_radioDirecionado);
			_painelInfo.add(_radioNaoDirecionado);
			
		}
		return _painelInfo;
	}
	
	/*
	 * Cria painel onde o grafo é desenhado.
	 */
	private JPanel getPainelGrafo() {
		if (_painelGrafo == null) {
			_painelGrafo = new PainelGrafo(this, _grafo);
		}
		return _painelGrafo;
	}
	
	/*
	 * Cria painel dos botões.
	 * Botões para gerar:
	 * 			Lista de Adjacencia,
	 * 			Matriz de Adjacencia,
	 * 			Matriz de Incidencia,
	 * 			Lista de Arestas
	 */
	private JPanel getPainelBotoes() {
		if (_painelBotoes == null) {
			_painelBotoes = new JPanel();
			
			_painelBotoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			_painelBotoes.setLayout(new FlowLayout());
			_painelBotoes.setSize(new Dimension(1000, 50));
			
			_botaoListaAdj = new JButton("Gerar Lista de Adjacencia");
			_botaoListaAdj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoListaAdj(_grafo));
				}
			});
			
			_botaoMatrizAdj = new JButton("Gerar Matriz de Adjacencia");
			_botaoMatrizAdj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoMatrizAdj(_grafo));
				}
			});
			
			_botaoMatrizIncidencia = new JButton("Gerar Matriz de Incidencia");
			_botaoMatrizIncidencia.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoMatrizIncidencia(_grafo));
				}
			});
			
			_botaoListaArestas = new JButton("Gerar Lista de Arestas");
			_botaoListaArestas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoListaArestas(_grafo));
				}
			});
			
			_botaoConexidade = new JButton("Gerar Grafo de Conexidade");
			_botaoConexidade.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoDeConexidade(_grafo));
				}
			});
			
			_painelBotoes.add(_botaoListaAdj);
			_painelBotoes.add(_botaoMatrizAdj);
			_painelBotoes.add(_botaoMatrizIncidencia);
			_painelBotoes.add(_botaoListaArestas);
			_painelBotoes.add(_botaoConexidade);
		
		}
		return _painelBotoes;
	}
	
	/*
	 * Metodo que seleciona qual representação do grafo mostrar
	 * no painel de saido do grafo.
	 */
	public void geraGrafoAPartirDo(Grafo grafo) {
		try {
			_saidaDoGrafo.append("Grafo Direcionado: " + _radioDirecionado.isSelected() + "\n");
			
			if (grafo instanceof GrafoListaAdj) {
				_saidaDoGrafo.append(((GrafoListaAdj) grafo).exibiGrafo());
			}
			
			if (grafo instanceof GrafoMatrizAdj) {
				_saidaDoGrafo.append(((GrafoMatrizAdj) grafo).exibiGrafo());
			}
			
			if (grafo instanceof GrafoMatrizIncidencia) {
				_saidaDoGrafo.append(((GrafoMatrizIncidencia) grafo).exibiGrafo());
			}
			
			if (grafo instanceof GrafoListaArestas) {
				_saidaDoGrafo.append(((GrafoListaArestas) grafo).exibiGrafo());
			}
			
			if (grafo instanceof GrafoDeConexidade) {
				if (_radioDirecionado.isSelected()) {
					JOptionPane.showMessageDialog(this, "Esse grafo só pode ser rodado de forma não direcionada!");
					return;
				}
				_saidaDoGrafo.append(((GrafoDeConexidade) grafo).exibiGrafo());
			}
			
			_radioDirecionado.setEnabled(false);
			_radioNaoDirecionado.setEnabled(false);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar o grafo! Erro: " + e.getMessage());
			e.printStackTrace(); 
		}
	}
	
	/*
	 * Metodo para limpar a tela.
	 */
	public void limpaTela() {
		_grafo = new Grafo();
		((PainelGrafo) _painelGrafo).setGrafo(_grafo);
		_painelGrafo.repaint();
		_radioVertice.setSelected(true);
		_radioDirecionado.setSelected(true);
		_saidaDoGrafo.setText(GRAFOS);
	}
	
	public JRadioButton getRadioDirecionado() {
		return _radioDirecionado;
	}
	
	public JRadioButton getRadioNaoDirecionado() {
		return _radioNaoDirecionado;
	}
	
	public JRadioButton getRadioVertice() {
		return _radioVertice;
	}

	public JRadioButton getRadioAresta() {
		return _radioAresta;
	}

	/*
	 * Metodo inicial
	 * Executa a aplicação.
	 */
	public static void main(String[] args) {
		final Tela tela = new Tela("Trabalho representação de grafos");
		tela.setVisible(true);
	}
	
}
