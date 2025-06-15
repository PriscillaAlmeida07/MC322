package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import interfaces.Entidade;
import robo.Robo;

public class MissaoBuscarPonto implements Missao {
    
    // Missão capaz de encontrar um caminho para levar os Agentes a um ponto específico do mapa

    @Override
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, ArrayList<Entidade> robosProx, Arquivo arquivo){
        int Xfinal = 30; int Yfinal = 30;
        int deltaX = Xfinal - robo.getX();
        int deltaY = Yfinal - robo.getY();
        robo.moverPara(deltaX, deltaY, 0, ambiente);
        return null;
    }
}
