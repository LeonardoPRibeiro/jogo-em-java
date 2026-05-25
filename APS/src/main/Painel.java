package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import entidade.Boss;
import entidade.Jogador;
import entidade.Npc_1;
import entidade.Npc_10;
import entidade.Npc_2;
import java.awt.event.KeyEvent; // Caso precise de algum mapeamento direto futuro
import entidade.Npc_3;
import entidade.Npc_4;
import entidade.Npc_5;
import entidade.Npc_6;
import entidade.Npc_7;
import entidade.Npc_8;
import entidade.Npc_9;
import ladrilho.ConfsLadrilho;

public class Painel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public Tecla tecla = new Tecla(); 
	Thread jogoThread;
	public VerificarColisao VF = new VerificarColisao(this);
	
	public Jogador jogador = new Jogador(this, tecla);
	
	// Cria uma lista q guarda 10 NPCs
	public Npc_1[] npcs = new Npc_1[10];
	
	public Boss boss = new Boss(this);
	
	public InterfaceUsuario iu = new InterfaceUsuario(this);
	public int estadoJogo;
	public final int estadoNormal = 0;
	public final int estadoDialogo = 1;
	public final int estadoQuiz = 2; // ---> CORRIGIDO: Adicionado o estado do Quiz <---
	

	public Painel() {
		this.setPreferredSize(new Dimension(larguraTela, tamanhoTela));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); 
		this.addKeyListener(tecla);   
		this.setFocusable(true);      
		
		estadoJogo = estadoNormal;
	}
	
	//onde cada npc se posiciona
	public void setupJogo() {
		
		npcs[0] = new Npc_1(this);
		npcs[0].mundoX = quadradoTam * 3; //npc1
		npcs[0].mundoY = quadradoTam * 3;  
		
		npcs[1] = new Npc_2(this);
		npcs[1].mundoX = quadradoTam * 5;  //npc2
		npcs[1].mundoY = quadradoTam * 7; 
		
		npcs[2] = new Npc_3(this);
		npcs[2].mundoX = quadradoTam * 2; 
		npcs[2].mundoY = quadradoTam * 23; 
		
		npcs[3] = new Npc_4(this);
		npcs[3].mundoX = quadradoTam * 4; 
		npcs[3].mundoY = quadradoTam * 38; 
		
		npcs[4] = new Npc_5(this);
		npcs[4].mundoX = quadradoTam * 3;  
		npcs[4].mundoY = quadradoTam * 46; 
		
		npcs[5] = new Npc_6(this);
		npcs[5].mundoX = quadradoTam * 20; 
		npcs[5].mundoY = quadradoTam * 34; 
		
		npcs[6] = new Npc_7(this);
		npcs[6].mundoX = quadradoTam * 30; 
		npcs[6].mundoY = quadradoTam * 34; 
		
		npcs[7] = new Npc_8(this);
		npcs[7].mundoX = quadradoTam * 45; 
		npcs[7].mundoY = quadradoTam * 5; 
		
		npcs[8] = new Npc_9(this);
		npcs[8].mundoX = quadradoTam * 47; 
		npcs[8].mundoY = quadradoTam * 36; 
		
		npcs[9] = new Npc_10(this);
		npcs[9].mundoX = quadradoTam * 44; 
		npcs[9].mundoY = quadradoTam * 47; 
		
		boss.mundoX = quadradoTam * 32;
		boss.mundoY = quadradoTam * 19;
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
			
			// O For faz todos os 10 NPCs atualizarem de uma vez só
			for(int i = 0; i < npcs.length; i++) {
				if(npcs[i] != null) {
					npcs[i].update();
				}
			}
			
		
			boss.update(); 
			
			checarProximidadeNpc();
		}
		else if(estadoJogo == estadoDialogo) {
			if(tecla.acaoPres == true) {
				tecla.acaoPres = false; 
				estadoJogo = estadoNormal;
			}
		}
	
		else if(estadoJogo == estadoQuiz) {
			gerenciarLogicaQuiz();
		}
	}
	// Calcula a distância do jogador pra cada um dos 10 NPCs da lista, e no final o bvoss
	public void checarProximidadeNpc() {
		
		for(int i = 0; i < npcs.length; i++) {
			if(npcs[i] != null) {
				int distanciaX = Math.abs(jogador.mundoX - npcs[i].mundoX);
				int distanciaY = Math.abs(jogador.mundoY - npcs[i].mundoY);

				if(distanciaX < 72 && distanciaY < 72) {
					if(tecla.acaoPres == true) {
						tecla.acaoPres = false; 
						iu.mensagemAtual = npcs[i].dica; 
						estadoJogo = estadoDialogo; 
						return; 
					}
				}
			}
		}
		
		
		int bossDistX = Math.abs(jogador.mundoX - boss.mundoX);
		int bossDistY = Math.abs(jogador.mundoY - boss.mundoY);
		if(bossDistX < 72 && bossDistY < 72 && tecla.acaoPres == true) {
			tecla.acaoPres = false;
			estadoJogo = estadoQuiz;
		}
	}

	
	public void gerenciarLogicaQuiz() {
		
		// enter pra fechar o jogo
		
		if(iu.vidaBoss <= 0 || iu.vidaJogador <= 0) {
			if(tecla.enterPres) {
				System.exit(0);
			}
			return;
		}

		int respostaJogador = -1;
		if(tecla.num1) { respostaJogador = 0; tecla.num1 = false; }
		if(tecla.num2) { respostaJogador = 1; tecla.num2 = false; }
		if(tecla.num3) { respostaJogador = 2; tecla.num3 = false; }

		// Se o jogador escolheu uma alternativa
		
		if(respostaJogador != -1) {
			int certa = boss.respostasCorretas[iu.perguntaAtualIndex];
			
			if(respostaJogador == certa) {
				iu.vidaBoss--;
				iu.feedbackResultado = "CORRETO! O Boss perdeu 1 de vida.";
			} else {
				iu.vidaJogador--;
				iu.feedbackResultado = "ERROU! Voce perdeu 1 de vida.";
			}

			// Passa para a próxima das 10 perguntas
			if(iu.perguntaAtualIndex < boss.perguntas.length - 1) {
				iu.perguntaAtualIndex++;
			} else {
				// Se as perguntas acabaram e ninguém morreu, volta pra primeira pergunta
				if(iu.vidaBoss > 0 && iu.vidaJogador > 0) {
					iu.perguntaAtualIndex = 0;
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		cLadrilho.draw(g2);
		
		//desenha todos os 10 NPCs na tela
		for(int i = 0; i < npcs.length; i++) {
			if(npcs[i] != null) {
				npcs[i].draw(g2);
			}
		}
		
		//desenha o boss
		boss.draw(g2);
		
		jogador.draw(g2);
		
		iu.draw(g2);
		
		g2.dispose();
	}
}