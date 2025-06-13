package missao;

import ambiente.Ambiente;
import robo.Robo;

public interface Missao {
    
    void executar(Robo robo, Ambiente ambiente);
    double getRaio();

}
