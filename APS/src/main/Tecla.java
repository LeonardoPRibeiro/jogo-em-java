package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tecla implements KeyListener {

    public boolean cimaPres, baixoPres, esquerdaPres, direitaPres, acaoPres, num1, num2, num3, enterPres;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { cimaPres = true; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { baixoPres = true; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { esquerdaPres = true; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { direitaPres = true; }
        if(code == KeyEvent.VK_1) { num1 = true; }
        if(code == KeyEvent.VK_2) { num2 = true; }
        if(code == KeyEvent.VK_3) { num3 = true; }
        if(code == KeyEvent.VK_ENTER) { enterPres = true; }
        
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
        if(code == KeyEvent.VK_1) { num1 = false; }
        if(code == KeyEvent.VK_2) { num2 = false; }
        if(code == KeyEvent.VK_3) { num3 = false; }
        if(code == KeyEvent.VK_ENTER) { enterPres = false; }
        // ADICIONE ISSO TAMBÉM:
        if(code == KeyEvent.VK_E) { 
            acaoPres = false; 
        }
    }
}