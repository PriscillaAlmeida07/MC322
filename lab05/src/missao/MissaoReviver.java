package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.ArrayList;
import java.util.Scanner;
import robo.*;

public class MissaoReviver implements Missao {

    // Essa missão é capaz de reviver todos os robôs mortos dentro de um raio de 20 metros do Agente Vida.
    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada){
        String mensagem;
        ArrayList<Robo> robosMortos = robo.getGerenciadorSensores().getRobosMortos();

        // Revive e registra todos os robôs que estavam mortos
        for (int i = 0; i < robosMortos.size(); i++){
            robosMortos.get(i).setVida(1);
            mensagem = "O agente reviveu o " + robosMortos.get(i).getNome() + " que agora está com vida " + robosMortos.get(i).getVida() + "/10";
            robo.arquivarEPrintar(mensagem, arquivo);
        }
        System.out.print("\n");
    }
}