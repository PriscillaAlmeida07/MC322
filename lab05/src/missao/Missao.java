package missao;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import robo.Robo;

public interface Missao {
    
    void executar(Robo robo, Ambiente ambiente, CentralComunicacao centralComunicacao);
    double getRaio();

}
