package main;

import javax.swing.JFrame;

public class Main {
	
	//só fazemos as instânciações das classes

	public static void main(String[] args) {
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setTitle("JOGO");
		
		Painel painel = new Painel();
		janela.add(painel);
		
		janela.pack();
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		painel.startGameThread();
		painel.setupJogo();
		

	}

}
