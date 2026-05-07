package ladrilho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Painel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ConfsLadrilho {
	
	Painel painel;
	Ladrilho[] ladrilho;
	int mapNumeroLadrilho [] [];
	String linha;
	
	public ConfsLadrilho(Painel painel) {
		this.painel = painel;
		
		ladrilho = new Ladrilho[10];  //aumenta aqui se precisar colocar mais blocos diferentes, o limite é 10 por enquanto
		mapNumeroLadrilho = new int[painel.colunasMax][painel.linhasMax];
		
		getImagemLadrilho();
		loadMap();
	}
	
	public void getImagemLadrilho(){
		
		//Aqui, cada instância de ladrilho é um bloco novo. ladrilho[0] é o bloco de grama, e assim por diante.
		//Se quiser adicionar novos blocos, basta fazer do mesmo jeito q ta ai e adiciona a imagem em aps/res/ladrilhos. Só arrastar ela pra lá
		
		//Se vcs olharem em aps/res/mapas/map, vai ver um monte de número. Esses números são os números dos ladrilhos, conforme segue o código
		
		
		try {
			
			ladrilho[0] = new Ladrilho();										//bloco de grama
			ladrilho[0].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/grass00.png"));
			
			ladrilho[1] = new Ladrilho();
			ladrilho[1].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/agua00.png"));
			
			ladrilho[2] = new Ladrilho();
			ladrilho[2].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/arvore00.png"));
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
		
		//essa funcao é pra carregar o mapa, que ta em aps/res/mapas
	    try {
	        InputStream is = getClass().getResourceAsStream("/mapas/map0.txt" );
	        if (is == null) {
	            System.out.println("Erro: Arquivo de mapa não encontrado!");
	            return;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
	        int col = 0;
	        int row = 0;
	        
	        // Um único loop para controlar as linhas
	        while (row < painel.linhasMax) {
	            String linhaLida = br.readLine(); // Lê uma linha inteira do arquivo
	            
	            if (linhaLida == null) break; // Para se o arquivo acabar antes do esperado

	            String numeros[] = linhaLida.split(" "); // Divide a linha pelos espaços
	            
	            // Loop para processar cada coluna daquela linha
	            for (col = 0; col < painel.colunasMax; col++) {
	                int num = Integer.parseInt(numeros[col]);
	                mapNumeroLadrilho[col][row] = num;
	            }
	            row++; // Vai para a próxima linha
	        }
	        br.close();
	        
	    } catch (Exception e) {
	        System.out.println("Erro ao carregar o mapa:");
	        e.printStackTrace(); // Isso vai te dizer exatamente o que deu errado
	    }
	}
	public void draw(Graphics g2) {
		//aqui ele só desenha na tela. Só vai chamar no Painel e no Main
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < painel.colunasMax && row < painel.linhasMax) {
			
			int numLadrilho = mapNumeroLadrilho[col][row];
			g2.drawImage(ladrilho[numLadrilho].image, x, y, painel.quadradoTam,painel.quadradoTam, null);
			col++;
			x += painel.quadradoTam;
			
			if(col == painel.colunasMax) {
				col = 0;
				x = 0;
				row ++;
				y += painel.quadradoTam;
			}
		}
	}

}
