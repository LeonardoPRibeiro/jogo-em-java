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
        
        // Inicializa o array de tipos de blocos (ajuste se tiver mais de 20)
        ladrilho = new Ladrilho[20]; 
        
        // Inicializa a matriz do mapa com o tamanho TOTAL do mundo (50x50)
        mapNumeroLadrilho = new int[painel.maxColMundo][painel.maxLinMundo];
        
        getImagemLadrilho();
        loadMap();
    }
    
    public void getImagemLadrilho() {
        try {
            ladrilho[0] = new Ladrilho();
            ladrilho[0].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/grama00.png"));
            
            ladrilho[1] = new Ladrilho();
            ladrilho[1].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/agua01.png"));
            
            ladrilho[2] = new Ladrilho();
            ladrilho[2].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/arvoreFundoGrama02.png"));
            
            ladrilho[3] = new Ladrilho();
            ladrilho[3].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/terra03.png"));
            
            ladrilho[4] = new Ladrilho();
            ladrilho[4].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ladrilhoPedra04.png"));
            
            ladrilho[5] = new Ladrilho();
            ladrilho[5].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ladrilhoPedraCorDeAreia05.png"));
            
            ladrilho[6] = new Ladrilho();
            ladrilho[6].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ladrilhoPedraDiferente06.png"));
            
            ladrilho[7] = new Ladrilho();
            ladrilho[7].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/ladrilhoTijolos07.png"));
            
            ladrilho[8] = new Ladrilho();
            ladrilho[8].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/tijoloArgila08.png"));
            
            ladrilho[9] = new Ladrilho();
            ladrilho[9].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/caminhoBaixoECima09.png"));
            
            ladrilho[10] = new Ladrilho();
            ladrilho[10].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/caminhoEsquerdaDireita10.png"));
            
            ladrilho[11] = new Ladrilho();
            ladrilho[11].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/caminhoCruzamento11.png"));
            
            ladrilho[12] = new Ladrilho();
            ladrilho[12].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_meio12.png"));
            
            ladrilho[13] = new Ladrilho();
            ladrilho[13].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_teto_direita13.png"));
            
            ladrilho[14] = new Ladrilho();
            ladrilho[14].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_teto_esquerda14.png"));
            
            ladrilho[15] = new Ladrilho();
            ladrilho[15].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_teto_meio15.png"));
            
            ladrilho[16] = new Ladrilho();
            ladrilho[16].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_direita16.png"));
            
            ladrilho[17] = new Ladrilho();
            ladrilho[17].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/casa_parte1_esquerda17.png"));
            
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

                // Importante: usar trim() para evitar espaços vazios no início/fim
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
            
            // Posição real no mapa (Mundo)
            int mundoX = colMundo * painel.quadradoTam;
            int mundoY = linMundo * painel.quadradoTam;
            
            // Onde o bloco deve ser desenhado em relação ao jogador (Tela)
            int telaX = mundoX - painel.jogador.mundoX + painel.jogador.telaX;
            int telaY = mundoY - painel.jogador.mundoY + painel.jogador.telaY;
            
            // "Culling": Só desenha o que está visível na tela para ganhar performance
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