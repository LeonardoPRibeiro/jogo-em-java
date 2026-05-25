package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_2 extends Npc_1 {
	
	
	public Npc_2(Painel painel) {

        super(painel); 
        
        this.dica = "O óleo de cozinha usado pode contaminar até 25 mil litros de água\n se jogado na pia.Guarde em garrafas PET e doe \npara a produção de sabão ou biodiesel.";
         
        getImagemNpc2();
       }
        
        public void getImagemNpc2() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_loirinhoBaixo.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_loirinhoEsquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_loirinhoDireita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

