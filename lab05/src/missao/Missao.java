package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import interfaces.Entidade;
import robo.Robo;

public interface Missao {
    
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, ArrayList<Entidade> robosProx, Arquivo arquivo);

}
