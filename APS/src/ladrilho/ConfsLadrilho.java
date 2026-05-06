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
		
		ladrilho = new Ladrilho[10];
		mapNumeroLadrilho = new int[painel.colunasMax][painel.linhasMax];
		
		getImagemLadrilho();
		loadMap();
	}
	
	public void getImagemLadrilho(){
		try {
			
			ladrilho[0] = new Ladrilho();
			ladrilho[0].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/grass00.png"));
			
			ladrilho[1] = new Ladrilho();
			ladrilho[1].image = ImageIO.read(getClass().getResourceAsStream("/ladrilhos/agua00.png"));
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
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
