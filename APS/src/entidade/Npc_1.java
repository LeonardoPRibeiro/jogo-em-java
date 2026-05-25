package entidade;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.Painel;

public class Npc_1 extends Entidade {
	
	public String dica = "O plástico leva mais de 400 anos para se decompor!\nEvite sacolas descartáveis.";
    
    public Npc_1(Painel painel) {

        super(painel); 
        
        direcao = "baixo";
        velocidade = 0; // ele vai ficar parado
        
        getImagem();
    }
    
    public void getImagem() {
        try {
            
            baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_1_olhandoFrente.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_1_olhandoEsquerda.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_1_olhandoDireita.png"));
            
            // repetindo baixo pois n tem sprite cima e não bugar tudo
            cima1 = baixo1; 
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
    	
        // Lógica do olhar do npoc, acalcula distancia npc e jogador
        int distanciaX = painel.jogador.mundoX - this.mundoX;
        int distanciaY = painel.jogador.mundoY - this.mundoY;
        
        
        // Vemos se o jogador está mais longe na horizontal (X) ou na vertical (Y)
        if (Math.abs(distanciaX) > Math.abs(distanciaY)) { //abs pra tirar o sinal -
            // Se a distância X for maior, o NPC olha pro lado
            if (distanciaX > 0) {
                direcao = "direita"; // Jogador está à direita
            } else {
                direcao = "esquerda"; // Jogador está à esquerda
            }
        } else {
            // Se a distância Y for maior, o NPC olha pra cima
            if (distanciaY > 0) {
                direcao = "baixo"; // Jogador está abaixo
            } else {
                direcao = "cima"; // Jogador está acima, vai usar a sprite q olha pra baixo
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;
        
        switch(direcao) {
            case "cima":
                imagem = cima1;
                break;
            case "baixo":
                imagem = baixo1;
                break;
            case "esquerda":
                imagem = esquerda1;
                break;
            case "direita":
                imagem = direita1;
                break;
        }
        
        // onde desenhar o NPC na tela baseado na posição do jogador
        int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
        int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;
        
        g2.drawImage(imagem, telaX, telaY, painel.quadradoTam, painel.quadradoTam, null);
    }
}