package entidade;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Painel;
import main.Tecla;

public class Jogador extends Entidade {
	
	Painel painel;
	Tecla tecla;
	
	public Jogador(Painel painel, Tecla tecla) {
		this.painel = painel;
		this.tecla = tecla;
		
		setDefaultValues();
		getImagemJogador();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		velocidade = 4;
		direcao = "cima";
	}
	
	public void getImagemJogador() {
		try {
			cima1 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_costas1.png"));
			cima2 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_costas2.png"));
			baixo1 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_frente1.png"));
			baixo2 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_frente2.png"));
			esquerda1 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_esquerda1.png"));
			esquerda2 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_esquerda2.png"));
			direita1 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_direita1.png"));
			direita2 = ImageIO.read(getClass().getResourceAsStream("/jogador/protagonista_direita2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(tecla.cimaPres == true || tecla.baixoPres == true || tecla.esquerdaPres == true || tecla.direitaPres == true) {
			if(tecla.cimaPres == true) {
				direcao = "cima";
				y -= velocidade;
			}
			if(tecla.baixoPres == true) {
				direcao = "baixo";
				y += velocidade;
			}
			
			if(tecla.esquerdaPres == true) {
				direcao = "esquerda";
				x -= velocidade;
			}
			
			if(tecla.direitaPres == true) {
				direcao = "direita";
				x += velocidade;
			}
			
			contadorSprite ++;
			
			if(contadorSprite > 12){
				
				if(numeroSprite == 1) {
					numeroSprite = 2;
				}
				else if(numeroSprite == 2) {
					numeroSprite = 1;
				}
				contadorSprite = 0;
			}
		}
		
	}
	
	
	public void draw(Graphics g2) {
		
		BufferedImage imagem = null;
		
		switch(direcao) {
		case "cima":
			if(numeroSprite == 1) {
			imagem = cima1;
			}
			if(numeroSprite == 2) {
			imagem = cima2;
			}
			break;

		case "baixo":
			if(numeroSprite == 1) {
				imagem = baixo1;
			}
				if(numeroSprite == 2) {
				imagem = baixo2;
				}
				break;

		case "esquerda":
			if(numeroSprite == 1) {
				imagem = esquerda1;
			}
				if(numeroSprite == 2) {
				imagem = esquerda2;
				}
				break;

		case "direita":
			if(numeroSprite == 1) {
				imagem = direita1;
			}
				if(numeroSprite == 2) {
				imagem = direita2;
				}
				break;
		}
		g2.drawImage(imagem, x, y,painel.quadradoTam,painel.quadradoTam, null);
	}
}	


