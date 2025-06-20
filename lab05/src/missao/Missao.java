package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.Scanner;
import robo.AgenteInteligente;

public interface Missao {
    
    // Os agentes inteligentes podem ter uma das quatro missões: buscar ponto, contactar curadores, reviver e segurança.
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada);

}
