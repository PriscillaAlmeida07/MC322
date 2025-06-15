package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import robo.AgenteInteligente;

public interface Missao {
    
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo);

}
