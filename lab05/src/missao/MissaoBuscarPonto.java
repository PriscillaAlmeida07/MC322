package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import interfaces.Entidade;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoBuscarPonto implements Missao {
    
    // Missão capaz de encontrar um caminho para levar os Agentes a um ponto específico do mapa

    @Override
    // Ainda tem que dar um jeito de escolher o ponto final que ele quer ir
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo){
        int Xfinal = 30; int Yfinal = 30;
        int deltaX = Xfinal - robo.getX();
        int deltaY = Yfinal - robo.getY();
        robo.moverPara(deltaX, deltaY, 0, ambiente);
        return null;
    }
}
