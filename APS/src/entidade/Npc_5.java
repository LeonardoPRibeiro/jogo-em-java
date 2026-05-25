package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_5 extends Npc_1 {
	
	public Npc_5(Painel painel) {

        super(painel); 
        

    	this.dica = "Sacolas plásticas comuns levam até 20 anos para se decompor nos \noceanos, sufocando animais marinhos. Leve sempre uma ecobag.";
        
        getImagemNpc5();
       }
        
        public void getImagemNpc5() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc5_loira.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc5_loiraEsquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc5_loiraDireita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

