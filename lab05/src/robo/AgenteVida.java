package robo;
// passar por mensagem o robo necessitado de vidas p curadr

import java.util.Scanner;
import java.util.ArrayList;

import ambiente.Ambiente;
import enums.EstadoRobo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import exceptions.RoboDesligadoException;
import interfaces.Curador;
import java.lang.reflect.Array;

public class AgenteVida extends AgenteInteligente {

    public AgenteVida(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    //precisamos implementar tambem
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException{
    }

    // Verifica o robô com a menor vida e manda uma mensagem para o curador mais próximo sobre a sua localização
    @Override 
    public void executarMissao(Ambiente ambiente) {
        ArrayList<Robo> robos = ambiente.getArrayRobos();
        ArrayList<Robo> robosMortos = new ArrayList<>();
        ArrayList<Robo> curadores = new ArrayList<>();

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i).getVida() == 0){
                robosMortos.add(robos.get(i));
            } else if (robos.get(i) instanceof Curador){
                curadores.add(robos.get(i));
            }
        }

        Robo roboProximo;
        for (int i = 0; i < robosMortos.size(); i++){
            for (int j= 0; j < curadores.size(); j++){
                
            }
            
        }

    }

    }

}