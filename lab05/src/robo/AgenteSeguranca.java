package robo;

import java.util.ArrayList;
import java.util.Scanner;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;
import interfaces.Entidade;
import missao.Missao;
import missao.MissaoSeguranca;
import missao.MissaoVida;
import sensores.Sensor;

public class AgenteSeguranca extends AgenteInteligente {
    
    public AgenteSeguranca(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    @Override
    public void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao){
        if(missao instanceof MissaoSeguranca){
            System.out.println("O robo esta protegendo os robos que estao dentro do raio\n");
            Sensor sensor = getSensorRobos();
            int[] vetorPosicao = getPosicao();
            // Vamos utilizar o sensor para achar os robos mais proximos do agente
            ArrayList<Entidade> robosProtegidos = sensor.monitorar(ambiente, vetorPosicao, 1);
            ArrayList<Robo> robosProteg = new ArrayList<>();
            robosProteg = missao.executar(this, ambiente, centralComunicacao, robosProtegidos);
            // falta implementar nos atacantes que se na classe robo estiver protegido = true ele nao pode atacar
            // falta enviar mensagem se der certo
        } else {
            // missao mais basica so para ter duas opcoes
        }

    }
    

    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException{

    }

    public String getDescricao(){return "Agente que";}

}

        // if(getMissao() = missaoVida)
        // Missao missao = new MissaoSeguranca();

        // definirMissao(missao);
        // missao.executar(this, ambiente);
        // System.out.println("O robo esta patrulhando\n");