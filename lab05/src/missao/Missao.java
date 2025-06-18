package missao;

import java.util.Scanner;

import ambiente.Ambiente;
import arquivos.Arquivo;
import robo.AgenteInteligente;

public interface Missao {
    
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada);

}
