package main;

import entidade.Entidade;

public class VerificarColisao {
    
    Painel painel;
    
    public VerificarColisao(Painel painel) {
        this.painel = painel;
    }
    
    public void verificarLadrilho(Entidade entidade) {
        
        // Define as bordas da HitBox da entidade no mundo pixel por pixel
        int esquerdaEntidadeX = entidade.mundoX + entidade.hitBox.x;
        int direitaEntidadeX = entidade.mundoX + entidade.hitBox.x + entidade.hitBox.width;
        int cimaEntidadeY = entidade.mundoY + entidade.hitBox.y;
        int baixoEntidadeY = entidade.mundoY + entidade.hitBox.y + entidade.hitBox.height; // Corrigido de width para height
        
        // Transforma a posição de pixels para o número da Linha/Coluna da matriz do mapa
        int entidadeEsquerdaCol = esquerdaEntidadeX / painel.quadradoTam;
        int entidadeDireitaCol = direitaEntidadeX / painel.quadradoTam;
        int entidadeCimaLin = cimaEntidadeY / painel.quadradoTam;
        int entidadeBaixoLin = baixoEntidadeY / painel.quadradoTam; 
        
        int ladrilho1, ladrilho2;
        
        // Prevê onde a entidade vai estar baseada na direção que ela quer andar
        switch(entidade.direcao) {
        case "cima":
            // Previsão do Y de cima menos a velocidade, tudo dividido pelo tamanho do bloco
            entidadeCimaLin = (cimaEntidadeY - entidade.velocidade) / painel.quadradoTam;
            ladrilho1 = painel.cLadrilho.mapNumeroLadrilho[entidadeEsquerdaCol][entidadeCimaLin];
            ladrilho2 = painel.cLadrilho.mapNumeroLadrilho[entidadeDireitaCol][entidadeCimaLin];
            
            // Se qualquer um dos dois blocos da frente tiver colisão ativada...
            if(painel.cLadrilho.ladrilho[ladrilho1].colisao == true || painel.cLadrilho.ladrilho[ladrilho2].colisao == true) {
                entidade.hitBoxAtiva = true; // Ativa a colisão e impede o movimento
            }
            break;
            
        case "baixo":
            // Previsão do Y de baixo mais a velocidade
            entidadeBaixoLin = (baixoEntidadeY + entidade.velocidade) / painel.quadradoTam;
            ladrilho1 = painel.cLadrilho.mapNumeroLadrilho[entidadeEsquerdaCol][entidadeBaixoLin];
            ladrilho2 = painel.cLadrilho.mapNumeroLadrilho[entidadeDireitaCol][entidadeBaixoLin];
            
            if(painel.cLadrilho.ladrilho[ladrilho1].colisao == true || painel.cLadrilho.ladrilho[ladrilho2].colisao == true) {
                entidade.hitBoxAtiva = true;
            }
            break;
            
        case "esquerda":
            // Previsão do X da esquerda menos a velocidade
            entidadeEsquerdaCol = (esquerdaEntidadeX - entidade.velocidade) / painel.quadradoTam;
            ladrilho1 = painel.cLadrilho.mapNumeroLadrilho[entidadeEsquerdaCol][entidadeCimaLin];
            ladrilho2 = painel.cLadrilho.mapNumeroLadrilho[entidadeEsquerdaCol][entidadeBaixoLin];
            
            if(painel.cLadrilho.ladrilho[ladrilho1].colisao == true || painel.cLadrilho.ladrilho[ladrilho2].colisao == true) {
                entidade.hitBoxAtiva = true;
            }
            break;
            
        case "direita":
            // Previsão do X da direita mais a velocidade
            entidadeDireitaCol = (direitaEntidadeX + entidade.velocidade) / painel.quadradoTam;
            ladrilho1 = painel.cLadrilho.mapNumeroLadrilho[entidadeDireitaCol][entidadeCimaLin];
            ladrilho2 = painel.cLadrilho.mapNumeroLadrilho[entidadeDireitaCol][entidadeBaixoLin];
            
            if(painel.cLadrilho.ladrilho[ladrilho1].colisao == true || painel.cLadrilho.ladrilho[ladrilho2].colisao == true) {
                entidade.hitBoxAtiva = true;
            }
            break;
        }
    }
}