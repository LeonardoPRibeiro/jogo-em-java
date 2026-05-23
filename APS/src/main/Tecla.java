package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tecla implements KeyListener {

    public boolean cimaPres, baixoPres, esquerdaPres, direitaPres, acaoPres; // <-- Garanta que acaoPres está aqui

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { cimaPres = true; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { baixoPres = true; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { esquerdaPres = true; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { direitaPres = true; }
        
        // SE ESTIVER USANDO A TECLA "E":
        if(code == KeyEvent.VK_E) { 
            acaoPres = true; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { cimaPres = false; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { baixoPres = false; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { esquerdaPres = false; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { direitaPres = false; }
        
        // ADICIONE ISSO TAMBÉM:
        if(code == KeyEvent.VK_E) { 
            acaoPres = false; 
        }
    }
}