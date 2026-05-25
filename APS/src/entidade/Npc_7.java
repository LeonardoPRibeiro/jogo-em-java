package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_7 extends Npc_1 {
	
	
	public Npc_7(Painel painel) {

        super(painel); 
        
        this.dica = "Embalagens de entrega (delivery) geram toneladas de lixo \ndescartável todos os dias.Peça para enviarem sem \ntalheres ou canudos plásticos se for comer em casa."; 
        
        getImagemNpc7();
       }
        
        public void getImagemNpc7() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc7_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc7_esquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc7_direita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

