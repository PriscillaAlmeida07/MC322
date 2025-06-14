package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import exceptions.ErroComunicacaoException;
import interfaces.Curador;
import interfaces.Entidade;
import robo.Robo;
import sensores.Sensor;

public class MissaoVida implements Missao {


    @Override
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, CentralComunicacao centralComunicacao, ArrayList<Entidade> robosProx){

        ArrayList<Robo> robosMortos = new ArrayList<>();

        for (int i = 0; i < robosProx.size(); i++){
            if(robosProx.get(i) instanceof Robo roboProx){
                if (roboProx.getVida() == 0){
                    robosMortos.add(roboProx);
                }
            }
        }
        return robosMortos;
    }
}



//  Se preciso voltar a usar
    // String mensagem = "Você é o robô curador mais próximo para encontrar o robo morto na posição (" + robosMortos.get(i).getX() + "," + robosMortos.get(i).getY() + "," + robosMortos.get(i).getZ() + ")";
    // try {
    //     robo.enviarMensagem(centralComunicacao, roboProximo, mensagem);
    //     System.out.println("O robo curador " + roboProximo.getNome() + "recebeu uma mensagem sobre um robô morto próximo");
    // } catch (ErroComunicacaoException e) {
    //     System.err.println("Erro: " + e.getMessage());
    // }