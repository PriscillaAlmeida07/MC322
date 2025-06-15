package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.ArrayList;
import robo.AgenteInteligente;
import robo.Robo;

public interface Missao {
    
    public void executar(AgenteInteligente robo, Ambiente ambiente, ArrayList<Robo> robosAfetados, Arquivo arquivo);

}
