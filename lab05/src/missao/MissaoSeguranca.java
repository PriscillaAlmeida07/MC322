package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoSeguranca implements Missao {
    private int raio = 10;
    
    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo){
        // Vamos escrever no arquivo sobre a missao realizada
        String mensagem;
        ArrayList<Robo> robosProtegidos = robo.getGerenciadorSensores().getRobosPrtegidos();

        for (int i = 0; i < robosProtegidos.size(); i++){
            robosProtegidos.get(i).setProtegido(); // uda para true
            mensagem = "O agente está protegendo o " + robosProtegidos.get(i).getNome() + "\n";
            robo.arquivarEPrintar(mensagem, arquivo);
        }
    }

    public void encerrarMissao(AgenteInteligente robo){
        ArrayList<Robo> robosProtegidos = robo.getGerenciadorSensores().getRobosPrtegidos();
        for (int i = 0; i < robosProtegidos.size(); i++){
            if(robosProtegidos.get(i) instanceof Robo roboProtegido){
                roboProtegido.setProtegido(); // vai mudar para false
            }
        }
    }

    public double getRaio(){
        return raio;
    }

}

// Achoq nao sera mais necessario
    // public void encerrarMissao(){
    //     for (int i = 0; i < robosProtegidos.size(); i++){
    //         if(robosProtegidos.get(i) instanceof Robo roboProtegido){
    //             roboProtegido.setProtegido(); // vai mudar para false
    //         }
    //     }
    // }
