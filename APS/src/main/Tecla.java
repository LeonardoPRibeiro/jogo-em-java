package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tecla implements KeyListener {
	
	public boolean cimaPres, baixoPres, esquerdaPres, direitaPres;

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int codigo = e.getKeyCode();
		
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
		int codigo = e.getKeyCode();
		
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
	
	

