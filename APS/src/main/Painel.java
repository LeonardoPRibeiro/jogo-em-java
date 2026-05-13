package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import entidade.Jogador;
import ladrilho.ConfsLadrilho;

public class Painel extends JPanel implements Runnable {
	
	// Configs de tamanho: tile base é 16px, x3 pra não ficar minúsculo
	final int tamOriginalQuadrado = 16;
	final int escala = 3;
	
	public final int quadradoTam = tamOriginalQuadrado * escala; // Resulta em 48px
	public final int colunasMax = 16;
	public final int linhasMax = 12;
	public final int larguraTela = quadradoTam * colunasMax;
	public final int tamanhoTela = quadradoTam * linhasMax;
	
	int QPS = 60; // FPS do jogo	
	
	ConfsLadrilho cLadrilho = new ConfsLadrilho(this);
	Tecla tecla = new Tecla();
	Thread jogoThread;
	Jogador jogador = new Jogador(this, tecla);
	
	public Painel() {
		this.setPreferredSize(new Dimension(larguraTela, tamanhoTela));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Otimiza a renderização (evita flickering)
		this.addKeyListener(tecla);   // Ativa o listener do teclado
		this.setFocusable(true);      // Garante que o painel receba o input
	}
	
	public void startGameThread() {
		jogoThread = new Thread(this);
		jogoThread.start();
	}
	
	@Override
	public void run() {
		// Calc do intervalo de tempo baseado no QPS (nanossegundos)
		double intervaloPreencher = 1000000000 / QPS;
		double delta = 0;
		long ultimoTempo = System.nanoTime();
		long tempoAtual;
		
		while(jogoThread != null) {
			tempoAtual = System.nanoTime();
			delta += (tempoAtual - ultimoTempo) / intervaloPreencher;
			ultimoTempo = tempoAtual;
			
			// Ciclo de Update e Draw só quando delta >= 1 (sync com FPS)
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
		
	public void update() {
		// Atualiza lógica do player (pos, status, etc)
		jogador.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		// Camadas: desenha o mapa primeiro, depois o player por cima
		cLadrilho.draw(g2);
		jogador.draw(g2);
		
		g2.dispose(); // Libera recursos de vídeo/memória
	}
}