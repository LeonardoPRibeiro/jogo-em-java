package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import entidade.Jogador;
import entidade.Npc_1;
import ladrilho.ConfsLadrilho;

public class Painel extends JPanel implements Runnable {
	
	final int tamOriginalQuadrado = 16;
	final int escala = 3;
	
	public final int quadradoTam = tamOriginalQuadrado * escala; 
	public final int colunasMax = 16;
	public final int linhasMax = 12;
	public final int larguraTela = quadradoTam * colunasMax;
	public final int tamanhoTela = quadradoTam * linhasMax;
	
	public final int maxColMundo = 50;
	public final int maxLinMundo = 50;
	
	int QPS = 60; 
	
	ConfsLadrilho cLadrilho = new ConfsLadrilho(this);
	public Tecla tecla = new Tecla(); // Mudado para public para o jogador acessar
	Thread jogoThread;
	public VerificarColisao VF = new VerificarColisao(this);
	public Jogador jogador = new Jogador(this, tecla);
	public Npc_1 meuNpc = new Npc_1(this);
	
	// ---- SISTEMA DE ESTADOS E INTERFACE ----
	public InterfaceUsuario iu = new InterfaceUsuario(this);
	public int estadoJogo;
	public final int estadoNormal = 0;
	public final int estadoDialogo = 1;
	// ----------------------------------------

	public Painel() {
		this.setPreferredSize(new Dimension(larguraTela, tamanhoTela));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(tecla);   
		this.setFocusable(true);      
		
		// O jogo começa no estado normal (andando)
		estadoJogo = estadoNormal;
	}
	
	public void setupJogo() {
		// Define a posição do NPC no mapa (Ex: Coluna 15, Linha 15)
		meuNpc.mundoX = quadradoTam * 32;
		meuNpc.mundoY = quadradoTam * 6;
	}

	public void startGameThread() {
		jogoThread = new Thread(this);
		jogoThread.start();
	}
	
	@Override
	public void run() {
		double intervaloPreencher = 1000000000 / QPS;
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
		if(estadoJogo == estadoNormal) {
			jogador.update();
			meuNpc.update();
			checarProximidadeNpc();
		}
		else if(estadoJogo == estadoDialogo) {
			// Se estiver no diálogo e apertar "E" de novo, fecha o diálogo
			if(tecla.acaoPres == true) {
				// Pequeno truque para não fechar instantaneamente: desativa a tecla
				tecla.acaoPres = false; 
				estadoJogo = estadoNormal;
			}
		}
	}

	// Método que calcula a distância entre o jogador e o NPC
	public void checarProximidadeNpc() {
		int distanciaX = Math.abs(jogador.mundoX - meuNpc.mundoX);
		int distanciaY = Math.abs(jogador.mundoY - meuNpc.mundoY);

		// Se o jogador estiver a menos de 1 bloco e meio de distância 
		if(distanciaX < 72 && distanciaY < 72) {
			// E se ele apertar a tecla E
			if(tecla.acaoPres == true) {
				tecla.acaoPres = false; // Reseta a tecla
				iu.mensagemAtual = meuNpc.dica; // Passa a dica do NPC para a tela
				estadoJogo = estadoDialogo; // Muda o estado do jogo para exibir o texto
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		// 1. Desenha o Cenário
		cLadrilho.draw(g2);
		
		// 2. Desenha o NPC
		meuNpc.draw(g2);
		
		// 3. Desenha o Jogador
		jogador.draw(g2);
		
		// 4. Desenha a Interface (Dicas/Diálogos) por cima de tudo
		iu.draw(g2);
		
		g2.dispose(); 
	}
}