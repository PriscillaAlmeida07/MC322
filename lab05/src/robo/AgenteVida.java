package robo;
// passar por mensagem o robo necessitado de vidas p curadr

import java.util.Scanner;

import ambiente.Ambiente;
import enums.EstadoRobo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import exceptions.RoboDesligadoException;

public class AgenteVida extends AgenteInteligente{
    public AgenteVida(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    @Override
    public void executarMissao(Ambiente ambiente){
        System.out.println("aaa");
    }

    //precisamos implementar tambem
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException{
    }

}