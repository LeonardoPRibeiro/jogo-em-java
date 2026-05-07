package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tecla implements KeyListener {
	
	// Essas variáveis guardam o estado de cada tecla. 
	// O Jogador.java olha para essas booleans para saber se deve se mover ou não.
	public boolean cimaPres, baixoPres, esquerdaPres, direitaPres;

	@Override
	public void keyTyped(KeyEvent e) {
		// Não estamos usando este método, mas o Java exige que ele esteja aqui por causa da interface KeyListener
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// Este método é chamado quando você aperta uma tecla
		int codigo = e.getKeyCode();
		
		// Verifica se a tecla apertada foi W, A, S ou D e muda o estado para 'true'
		if(codigo == KeyEvent.VK_W) {
			cimaPres = true;
		}
		
		if(codigo == KeyEvent.VK_A) {
			esquerdaPres = true;
		}
		
		if(codigo == KeyEvent.VK_S) {
			baixoPres = true;
		}
		
		if(codigo == KeyEvent.VK_D) {
			direitaPres = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// Importante: quando você solta a tecla, precisamos avisar ao programa que o movimento parou
		int codigo = e.getKeyCode();
		
		// Volta o estado para 'false' assim que o dedo sai da tecla
		if(codigo == KeyEvent.VK_W) {
			cimaPres = false;
		}
		
		if(codigo == KeyEvent.VK_A) {
			esquerdaPres = false;
		}
		
		if(codigo == KeyEvent.VK_S) {
			baixoPres = false;
		}
		
		if(codigo == KeyEvent.VK_D) {
			direitaPres = false;
		}
	}
}