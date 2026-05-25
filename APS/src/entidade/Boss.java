package entidade;

import javax.imageio.ImageIO;
import main.Painel;

public class Boss extends Npc_1 {

    // Arrays para guardar as perguntas, opções e as respostas corretas
    public String[] perguntas = new String[10];
    public String[][] opcoes = new String[10][3];
    public int[] respostasCorretas = new int[10]; // Guarda o índice da resposta certa (0, 1 ou 2)

    public Boss(Painel painel) {
        super(painel);
        
        velocidade = 0;
        direcao = "baixo";
        
        carregarConfiguracoesQuiz();
        getImagemBoss();
    }
    
    public void getImagemBoss() {
        try {
            
            baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/boss.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/boss_esquerda.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/boss_direita.png"));
            cima1 = baixo1;
        } catch(Exception e) {
            System.out.println("Erro ao carregar sprite do Boss, usando fallback do NPC 1");
        }
    }

    private void carregarConfiguracoesQuiz() {
    	
        //npc1
        perguntas[0] = "Quanto tempo o plastico leva para se decompor?";
        opcoes[0][0] = "1) Cerca de 10 anos";
        opcoes[0][1] = "2) Mais de 400 anos"; // Certa
        opcoes[0][2] = "3) Ele nao se decompoe";
        respostasCorretas[0] = 1; 

        //npc 2
        perguntas[1] = "Apagar as luzes ao sair reduz o consumo de que?";
        opcoes[1][0] = "1) Carvao e Gas Natural"; // Certa
        opcoes[1][1] = "2) Energia Solar";
        opcoes[1][2] = "3) Agua potavel";
        respostasCorretas[1] = 0; 

        //npc 3
        perguntas[2] = "Qual a porcentagem de alimentos que são descartados?";
        opcoes[2][0] = "1) 10%";
        opcoes[2][1] = "2) 20%";
        opcoes[2][2] = "3) 30%"; // Correta
        respostasCorretas[2] = 2; 
        
        //npc4
        perguntas[3] = "A lâmpada LED dura quantas vezes mais comparada a incandescente?";
        opcoes[3][0] = "1) 20";
        opcoes[3][1] = "2) 40";
        opcoes[3][2] = "3) 25"; // certa
        respostasCorretas[3] = 2; 
        
      //npc5
        perguntas[4] = "Qual a melhor alternativa em relação a sacola plástica?";
        opcoes[4][0] = "1) Ecobag"; //certa
        opcoes[4][1] = "2) Bolsa de couro";
        opcoes[4][2] = "3) Mochila"; 
        respostasCorretas[4] = 0; 
        
      //npc6
        perguntas[5] = "Restos de comida jogados no lixo comum gera que tipo de gás?";
        opcoes[5][0] = "1) Gás carbono";
        opcoes[5][1] = "2) Gás metano"; //certa
        opcoes[5][2] = "3) Gás hélio"; // 
        respostasCorretas[5] = 1; 
        
      //npc7
        perguntas[6] = "Como reduzir o lixo do delivery ao comer em casa?";
        opcoes[6][0] = "1) Recusar talheres"; //certa
        opcoes[6][1] = "2) Pedir mais embalagens";
        opcoes[6][2] = "3) Comer no restaurante";
        respostasCorretas[6] = 0; 
        
      //npc8
        perguntas[7] = "Até quantos litros são gastos ao deixar a torneira aberta por 15m?";
        opcoes[7][0] = "1) 115L";
        opcoes[7][1] = "2) 100L";
        opcoes[7][2] = "3) 93L"; // certs
        respostasCorretas[7] = 2; 
        
      //npc9
        perguntas[8] = "Qual a recomendação de transporte se moramos perto do nosso trabalho?";
        opcoes[8][0] = "1) De carro, por causa do conforto";
        opcoes[8][1] = "2) De moto, pela praticidade";
        opcoes[8][2] = "3) De bicicleta ou a pé, reduzindo emissões poluentes."; // Correta
        respostasCorretas[8] = 2; 
        
      //npc10
        perguntas[9] = "Até quantos litros a descarga do banheiro pode gastar a cada uso?";
        opcoes[9][0] = "1) 12";//certa
        opcoes[9][1] = "2) 10";
        opcoes[9][2] = "3) 11"; 
        respostasCorretas[9] = 0;
    }
}