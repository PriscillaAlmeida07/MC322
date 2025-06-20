package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.ArrayList;
import java.util.Scanner;
import robo.*;

public class MissaoSeguranca implements Missao {

    private final int raio = 10;

    // Obtém o raio de proteção.
    public double getRaio(){
        return raio;
    }

    // Essa missão é capaz de proteger todos os robôs dentro de um raio de 10 metros do Agente Vida.
    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada){
        String mensagem;
        ArrayList<Robo> robosProtegidos = robo.getGerenciadorSensores().getRobosProtegidos();

        for (int i = 0; i < robosProtegidos.size(); i++){
            robosProtegidos.get(i).setProtegido(); // vai mudar para true a variavel protegido de cada robô
            mensagem = "O agente está protegendo o " + robosProtegidos.get(i).getNome();
            robo.arquivarEPrintar(mensagem, arquivo);
        }
        System.out.print("\n");
    }

    // Mudamos o estado da variavel protegido (dos robôs que estavam sendo protegidos pela missão).
    public void encerrarMissao(AgenteInteligente robo){
        ArrayList<Robo> robosProtegidos = robo.getGerenciadorSensores().getRobosProtegidos();
        for (int i = 0; i < robosProtegidos.size(); i++){
            if (robosProtegidos.get(i) instanceof Robo roboProtegido){
                roboProtegido.setProtegido(); // vai mudar para false a variavel protegido de cada robô
            }
        }
    }
}
