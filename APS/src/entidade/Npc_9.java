package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_9 extends Npc_1 {
	
	public Npc_9(Painel painel) {

        super(painel); 
        
        this.dica = "Caminhar ou ir de bicicleta em trajetos de até 3 km reduz\n drasticamente a emissão de poluentes e melhora\n a saúde. Deixe o carro na garagem para distâncias curtas.";
        
        getImagemNpc9();
       }
        
        public void getImagemNpc9() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc9_frente.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc9_esquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc9_direita.png"));
                
                cima1 = baixo1; // 
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

