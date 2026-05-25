package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_3 extends Npc_1 {
	
	public Npc_3(Painel painel) {

        super(painel); 
        
    	this.dica = "Mais de 30% dos alimentos produzidos no mundo vão direto para\n o lixo.Planeje suas compras e evite o desperdício.";
        
        getImagemNpc3();
       }
        
        public void getImagemNpc3() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc3_encanador_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc3_encanadorEsquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc3_encanadorDireita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

