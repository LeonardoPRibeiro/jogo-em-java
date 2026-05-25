package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_6 extends Npc_1 {
	
	
	public Npc_6(Painel painel) {

        super(painel); 
        
        this.dica = "Restos de comida jogados no lixo comum geram gás metano, um dos \nprincipais causadores do efeito estufa. Se puder, faça compostagem.";
        
        getImagemNpc6();
       }
        
        public void getImagemNpc6() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc6_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc6_Esquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc6_direita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

