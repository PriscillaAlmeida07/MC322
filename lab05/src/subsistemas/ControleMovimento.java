package subsistemas;

import ambiente.Ambiente;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import robo.AgenteInteligente;

public class ControleMovimento {
    // Usado para a missao buscar ponto
    public void movimentarAgente(AgenteInteligente robo, Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ColisaoException, ForaDosLimitesException{
        robo.moverPara(deltaX, deltaY, 0, ambiente);
    }
}
