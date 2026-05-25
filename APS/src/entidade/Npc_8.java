package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_8 extends Npc_1 {
	
	public Npc_8(Painel painel) {

        super(painel); 
        
    	this.dica = "Lavar a louça com a torneira aberta por 15 minutos gasta até 117\n litros de água. Ensaboe tudo primeiro e abra a torneira\n apenas para enxaguar.";
        
        getImagemNpc8();
       }
        
        public void getImagemNpc8() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc8_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc8_esquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc8_direita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

