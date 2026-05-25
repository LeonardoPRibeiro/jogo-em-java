package entidade;

import javax.imageio.ImageIO;

import main.Painel;

public class Npc_4 extends Npc_1 {
		
	public Npc_4(Painel painel) {

        super(painel); 
        
        this.dica = "Uma lâmpada LED consome até 80% menos energia e dura 25 \nvezes mais que uma incandescente antiga.\n Troque as lâmpadas da sua casa.";
        
        getImagemNpc4();
       }
        
        public void getImagemNpc4() {
            try {
                baixo1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc4_menina_no_parque.png"));
                esquerda1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc4_menina_no_parqueEsquerda.png"));
                direita1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc4_menina_no_parqueDireita.png"));
                
                cima1 = baixo1; // nao tem sprite olhando pra cima
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}
 

