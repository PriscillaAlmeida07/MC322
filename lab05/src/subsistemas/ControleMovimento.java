package subsistemas;

import ambiente.Ambiente;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import robo.AgenteInteligente;

public class ControleMovimento {

    // Subsistema que auxilia os agentes em missões que exigem movimentação.

    // Movimenta o robô para um ponto específico do espaço.
    public void movimentarAgente(AgenteInteligente robo, Ambiente ambiente, int X, int Y) throws ColisaoException, ForaDosLimitesException {
        int deltaX = X - robo.getX();
        int deltaY = Y - robo.getY();
        
        robo.moverPara(deltaX, deltaY, 0, ambiente);
    }
}
