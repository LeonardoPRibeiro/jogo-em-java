package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entidade.Jogador;
import ladrilho.ConfsLadrilho;

public class Painel extends JPanel implements Runnable {
	
	final int tamOriginalQuadrado = 16;
	final int escala = 3;
	
	public final int quadradoTam = tamOriginalQuadrado * escala;
	public final int colunasMax = 16;
	public final int linhasMax = 12;
	public final int larguraTela = quadradoTam *colunasMax;
	public final int tamanhoTela = quadradoTam*linhasMax;
	
	int QPS = 60;	
	
	ConfsLadrilho cLadrilho = new ConfsLadrilho(this);
	Tecla tecla = new Tecla();
	Thread jogoThread;
	Jogador jogador = new Jogador(this,tecla);
	
	
	public Painel() {
		this.setPreferredSize(new Dimension(larguraTela, tamanhoTela));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(tecla);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		jogoThread = new Thread(this);
		jogoThread.start();
	}
	
	@Override
	public void run() {
		double intervaloPreencher = 1000000000/QPS;
		double delta = 0;
		long ultimoTempo = System.nanoTime();
		long tempoAtual;
		
		while(jogoThread != null) {
			
			tempoAtual = System.nanoTime();
			delta += (tempoAtual - ultimoTempo) / intervaloPreencher;
			
			ultimoTempo = tempoAtual;
			
			if(delta >= 1) {
				
				update();
				repaint();
				delta--;
			}
			}
			
		
	}
		
	public void update() {
		
		jogador.update();
		
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D)g;
		
		cLadrilho.draw(g2);
		
		jogador.draw(g2);
		
		g2.dispose();
		
	}
		
		
}
	


