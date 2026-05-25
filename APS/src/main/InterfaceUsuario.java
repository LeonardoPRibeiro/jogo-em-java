package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class InterfaceUsuario {

    Painel painel;
    Font fonteDialogo;
    Font fonteTitulo;
    public String mensagemAtual = ""; 
    
    // Variáveis de controle do Quiz
    public int perguntaAtualIndex = 0;
    public int vidaJogador = 3;
    public int vidaBoss = 10;
    public String feedbackResultado = ""; // Mostra se errou ou acertou

    public InterfaceUsuario(Painel painel) {
        this.painel = painel;
        this.fonteDialogo = new Font("Arial", Font.PLAIN, 18);
        this.fonteTitulo = new Font("Arial", Font.BOLD, 22);
    }

    public void draw(Graphics2D g2) {
        if(painel.estadoJogo == painel.estadoDialogo) {
            desenharCaixaDialogo(g2);
        }
        else if(painel.estadoJogo == painel.estadoQuiz) {
            desenharTelaQuiz(g2);
        }
    }

    public void desenharCaixaDialogo(Graphics2D g2) {
        int x = painel.quadradoTam * 2;
        int y = painel.quadradoTam * 8;
        int largura = painel.larguraTela - (painel.quadradoTam * 4);
        int altura = painel.quadradoTam * 3;

        g2.setColor(new Color(0, 0, 0, 210));
        g2.fillRoundRect(x, y, largura, altura, 15, 15);

        g2.setColor(Color.white);
        g2.setStroke(new java.awt.BasicStroke(3));
        g2.drawRoundRect(x + 5, y + 5, largura - 10, altura - 10, 15, 15);

        g2.setFont(fonteDialogo);
        g2.setColor(Color.white);

        int textoX = x + 20;
        int textoY = y + 40;

        for(String linha : mensagemAtual.split("\n")) {
            g2.drawString(linha, textoX, textoY);
            textoY += 30; 
        }
    }

    public void desenharTelaQuiz(Graphics2D g2) {
        // Fundo escuro do Combate/Quiz
        g2.setColor(new Color(20, 30, 40, 240));
        g2.fillRect(50, 50, painel.larguraTela - 100, painel.tamanhoTela - 100);
        
        g2.setColor(Color.WHITE);
        g2.setStroke(new java.awt.BasicStroke(4));
        g2.drawRect(50, 50, painel.larguraTela - 100, painel.tamanhoTela - 100);

        // placar de Vidas
        g2.setFont(fonteTitulo);
        g2.setColor(Color.GREEN);
        g2.drawString("Sua Vida: " + vidaJogador, 80, 90);
        g2.setColor(Color.RED);
        g2.drawString("Vida do ECO-BOSS: " + vidaBoss, painel.larguraTela - 300, 90);
        
        g2.setColor(Color.WHITE);
        g2.drawLine(50, 110, painel.larguraTela - 50, 110);

        // se o jogo acabou
        if(vidaBoss <= 0) {
            g2.setFont(fonteTitulo);
            g2.setColor(Color.GREEN);
            g2.drawString("PARABENS! VOCE VENCEU O ECO-BOSS!", 150, 250);
            g2.setFont(fonteDialogo);
            g2.drawString("Pressione ENTER para fechar o jogo.", 200, 300);
            return;
        }
        if(vidaJogador <= 0) {
            g2.setFont(fonteTitulo);
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER! O Planeta precisa de mais estudos.", 100, 250);
            g2.setFont(fonteDialogo);
            g2.drawString("Pressione ENTER para fechar o jogo.", 200, 300);
            return;
        }

        // Desenhar Pergunta Atual
        g2.setFont(fonteTitulo);
        g2.setColor(Color.YELLOW);
        g2.drawString(painel.boss.perguntas[perguntaAtualIndex], 80, 160);

        // Desenhar Opções
        g2.setFont(fonteDialogo);
        g2.setColor(Color.WHITE);
        g2.drawString(painel.boss.opcoes[perguntaAtualIndex][0], 100, 230);
        g2.drawString(painel.boss.opcoes[perguntaAtualIndex][1], 100, 280);
        g2.drawString(painel.boss.opcoes[perguntaAtualIndex][2], 100, 330);

        // instrução de Input
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("Pressione [1], [2] ou [3] para responder.", 80, 400);

        // exibe se acertou ou errou a anterior
        if(!feedbackResultado.isEmpty()) {
            if(feedbackResultado.contains("CORRETO")) g2.setColor(Color.GREEN);
            else g2.setColor(Color.RED);
            g2.drawString(feedbackResultado, 80, 440);
        }
    }
}