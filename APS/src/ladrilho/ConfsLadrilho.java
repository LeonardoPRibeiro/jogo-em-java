package ladrilho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.Painel;
import java.awt.Graphics;

public class ConfsLadrilho {
    
    Painel painel;
    public Ladrilho[] ladrilho;
    public int mapNumeroLadrilho[][];
    
    public ConfsLadrilho(Painel painel) {
        this.painel = painel;
        
        //array de sprites de ambiente
        ladrilho = new Ladrilho[30]; 
        
        // Inicializa a matriz do mapa 50x50
        mapNumeroLadrilho = new int[painel.maxColMundo][painel.maxLinMundo];
        
        getImagemLadrilho();
        loadMap();
    }
    
    public void getImagemLadrilho() {
        try {
            ladrilho[0] = new Ladrilho();
            ladrilho[0].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/asfaltoPadrao_0.png"));
            
            ladrilho[1] = new Ladrilho();
            ladrilho[1].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/asfaltoLinhaHorizontal_1.png"));
            
            ladrilho[2] = new Ladrilho();
            ladrilho[2].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/predio_02.png"));
            ladrilho[2].colisao = true;
            
            ladrilho[3] = new Ladrilho();
            ladrilho[3].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/calcadaPadrao_3.png"));
            
            ladrilho[4] = new Ladrilho();
            ladrilho[4].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/calcadaCima_4.png"));
            
            ladrilho[5] = new Ladrilho();
            ladrilho[5].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/calcadaBaixo_5.png"));
            
            ladrilho[6] = new Ladrilho();
            ladrilho[6].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ruaCalcadaDireita_6.png"));
            
            ladrilho[7] = new Ladrilho();
            ladrilho[7].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ruaCalcadaEsquerda_7.png"));

            ladrilho[8] = new Ladrilho();
            ladrilho[8].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_esquerdaPlanta_8.png"));
            
            ladrilho[9] = new Ladrilho();
            ladrilho[9].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/lojinha1_9.png"));
            ladrilho[9].colisao = true;
           
            ladrilho[10] = new Ladrilho();
            ladrilho[10].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/lojinha2_10.png"));
            ladrilho[10].colisao = true;
            
            ladrilho[11] = new Ladrilho();
            ladrilho[11].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/lojinha3_11.png"));
            ladrilho[11].colisao = true;
            
            ladrilho[12] = new Ladrilho();
            ladrilho[12].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/lojinha4_12.png"));
            ladrilho[12].colisao = true;
            
            ladrilho[13] = new Ladrilho();
            ladrilho[13].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/lojinha5_13.png"));
            ladrilho[13].colisao = true;
            
            ladrilho[14] = new Ladrilho();
            ladrilho[14].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/telhado_meio_14.png"));
            ladrilho[14].colisao = true;
            
            ladrilho[15] = new Ladrilho();
            ladrilho[15].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/telhado_15.png"));
            ladrilho[15].colisao = true;
            
            ladrilho[16] = new Ladrilho();
            ladrilho[16].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/asfaltoLinhaVertical_16.png"));
            
            ladrilho[17] = new Ladrilho();
            ladrilho[17].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/grama_17.png"));
            
            ladrilho[18] = new Ladrilho();
            ladrilho[18].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/arvore_18.png"));
            ladrilho[18].colisao = true;
            
            ladrilho[19] = new Ladrilho();
            ladrilho[19].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/predio_19.png"));
            ladrilho[19].colisao = true;
            
            ladrilho[20] = new Ladrilho();
            ladrilho[20].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/predio1_20.png"));
            ladrilho[20].colisao = true;
            
            ladrilho[21] = new Ladrilho();
            ladrilho[21].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/predio1_21.png"));
            ladrilho[21].colisao = false;
           
            
            
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/mapas/mapa.txt");
            if (is == null) {
                System.out.println("Erro: Arquivo de mapa não encontrado!");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int lin = 0;
            
            // Loop para percorrer todas as linhas do mundo
            while (lin < painel.maxLinMundo) {
                String linhaLida = br.readLine();
                
                if (linhaLida == null) break; 

                // trim() para evitar espaços vazios no início/fim
                String numeros[] = linhaLida.trim().split("\\s+"); 
                
                // Preenche as colunas daquela linha
                for (col = 0; col < painel.maxColMundo; col++) {
                    int num = Integer.parseInt(numeros[col]);
                    mapNumeroLadrilho[col][lin] = num;
                }
                lin++;
            }
            br.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar o mapa. Verifique se o mapa tem 50 números por linha.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) {
        int colMundo = 0;
        int linMundo = 0;
        
        while(colMundo < painel.maxColMundo && linMundo < painel.maxLinMundo) {
            
            int numLadrilho = mapNumeroLadrilho[colMundo][linMundo];
            
            // Posição real no mapa
            int mundoX = colMundo * painel.quadradoTam;
            int mundoY = linMundo * painel.quadradoTam;
            
            // Onde o bloco deve ser desenhado em relação ao jogador
            int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
            int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;
            
            //  Só desenha o que está visível na tela pra performance
            if (mundoX + painel.quadradoTam > painel.jogador.mundoX - painel.jogador.telaX &&
                mundoX - painel.quadradoTam < painel.jogador.mundoX + painel.jogador.telaX &&
                mundoY + painel.quadradoTam > painel.jogador.mundoY - painel.jogador.telaY &&
                mundoY - painel.quadradoTam < painel.jogador.mundoY + painel.jogador.telaY) {
                
                g2.drawImage(ladrilho[numLadrilho].image, telaX, telaY, painel.quadradoTam, painel.quadradoTam, null);
            }
            
            colMundo++;
            
            // Quando chega no final da largura do MUNDO, pula para a próxima linha
            if(colMundo == painel.maxColMundo) {
                colMundo = 0;
                linMundo++;
            }
        }
    }
}