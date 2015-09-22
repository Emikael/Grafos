package br.com.unisul.grafos.impl;

/**
 * Algoritmo de busca em largura (Não usado nesse trabalho)
 * @author emikael
 *
 */

public class AlgoritmoDeBuscaEmLargura {
	
	static char[] vListaNos = new char[8];
	
	public static int noEsquerdo(Integer pNoAtual) {
		return (2 * pNoAtual);
	}
	
	public static int noDireito(Integer pNoAtual) {
		return (2 * pNoAtual) + 1;
	}
		
	public static int busca_Largura(Integer inicio, char alvo) {
		boolean vAchou = false;
		Integer vLoop = inicio;
		
		Integer resultado = -1;
		
		if (vListaNos[inicio] == alvo) {
			vAchou = true;
			resultado = inicio;
		}
		
		while (!vAchou && vLoop < 8) {
			if (vListaNos[noEsquerdo(vLoop)] == alvo) {
				vAchou = true;
				resultado = noEsquerdo(vLoop);
			} else if (vListaNos[noDireito(vLoop)] == alvo) {
				vAchou = true;
				resultado = noDireito(vLoop);
			}
			vLoop++;
		}
		
		return resultado;
	}
	
	public static void main(String[] args) {
	  vListaNos[0] = 'R';      
	  vListaNos[1] = 'G';      
	  vListaNos[2] = 'Q';      
	  vListaNos[3] = 'Y';      
	  vListaNos[4] = 'J';      
	  vListaNos[5] = 'B';      
	  vListaNos[6] = 'E';      
	  vListaNos[7] = 'P';
	  
	  System.out.println("A letra 'J' esta no no numero: " + busca_Largura(2, 'J'));
	  System.out.println("A letra 'B' esta no no numero: " + busca_Largura(0, 'B'));
	  System.out.println("A letra 'R' esta no no numero: " + busca_Largura(0, 'R'));
	  System.out.println("A letra 'P' esta no no numero: " + busca_Largura(3, 'P'));
	  System.out.println("A letra 'Y' esta no no numero: " + busca_Largura(0, 'Y'));
	  System.out.println("A letra 'E' esta no no numero: " + busca_Largura(0, 'E'));
	  System.out.println("A letra 'Q' esta no no numero: " + busca_Largura(0, 'Q'));
	}

}
