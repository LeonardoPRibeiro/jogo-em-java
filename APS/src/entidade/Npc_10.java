package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_10 extends Npc_1 {
	
	public Npc_10(Painel painel) {

        super(painel); 
        
    	this.dica = "A descarga do banheiro gasta de 6 a 12 litros de água a cada clique.\nNão use o vaso sanitário como lixeira para jogar papel, \nlenços ou cabelos.";

        
        getImagemNpc10();
       }
        
        public void getImagemNpc10() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc10_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc10_esquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc10_direita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

