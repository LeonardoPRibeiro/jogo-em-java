package entidade;
import java.awt.Rectangle;
//quase uma classe abstrata, só não é pq n tem métodos.
//Ta servindo pra "Molde" para todas as entidades que vai ter no jogo.
import java.awt.image.BufferedImage;

public class Entidade {
	
	public int mundoX,mundoY;
	public int velocidade;
	
	
    public BufferedImage cima1,cima2,baixo1,baixo2,esquerda1,esquerda2,direita1,direita2; 
    public String direcao;

   
    public int contadorSprite= 0;
    public int numeroSprite = 1;
    
    public Rectangle hitBox;
    public boolean hitBoxAtiva = false;
    
}
