package br.com.unisul.grafos.controller;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MostraResultadoDoGrafo extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel _painelFundo; 
	JTable _tabela; 
	JScrollPane _barraDeRolagem; 
	Object [][] _dados; 
	String [] _colunas; 
	String _titulo;
	public static JFrame OWNER;
	
	public MostraResultadoDoGrafo(final String titulo, final String[] colunas, final Object[][] dados) { 
		super (titulo); 
		_titulo = titulo;
		_colunas = colunas;
		_dados = dados;
	} 
	
	public void mostraResultado() {
		_painelFundo = new JPanel(); 
		_painelFundo.setLayout(new GridLayout(1, 1)); 
		_tabela = new JTable(_dados, _colunas); 
		_barraDeRolagem = new JScrollPane(_tabela); 
		_painelFundo.add(_barraDeRolagem); 
		
		final JDialog frame = new JDialog(OWNER, _titulo, true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(400,200));
		frame.setPreferredSize(getSize());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
    	
		frame.getContentPane().add(_painelFundo);
		frame.setVisible(true);
	}
}