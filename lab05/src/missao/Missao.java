package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import interfaces.Entidade;
import robo.Robo;

public interface Missao {
    
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, CentralComunicacao centralComunicacao, ArrayList<Entidade> robosProx);

}
