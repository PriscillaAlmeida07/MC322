package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import exceptions.*;
import interfaces.Entidade;
import java.util.ArrayList;
import java.util.Scanner;
import missao.MissaoSeguranca;
import sensores.Sensor;

public class AgenteSeguranca extends AgenteInteligente {
    
    // Construtor.
    public AgenteSeguranca(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    // Realiza a missão atribuida.
    @Override
    public void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao, Arquivo arquivo){
        if (missao instanceof MissaoSeguranca){
            System.out.println("O robô esta protegendo os seguintes robôs:\n");
            Sensor sensor = getSensorRobos();
            // Mudaremos o raio do sensor para conseguir cobrir uma area maior
            sensor.setRaio(10);
            int[] vetorPosicao = getPosicao();
            // Vamos utilizar o sensor para achar os robos mais proximos do agente
            ArrayList<Entidade> robosProx = sensor.monitorar(ambiente, vetorPosicao, 1);
            ArrayList<Robo> robosProtegidos = missao.executar(this, ambiente, robosProx, arquivo);

            if (!robosProtegidos.isEmpty()){
                for (int i=0; i< robosProtegidos.size(); i++){
                    try {
                        System.out.println("O " + robosProtegidos.get(i).getNome() + " está sendo protegido.");
                        enviarMensagem(centralComunicacao, robosProtegidos.get(i), "Você " + robosProtegidos.get(i).getNome() + " está sendo protegido pelo Agente Vida"); //sempre sera valido mas temos q tratar com try msm assim
                    }  catch (ErroComunicacaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                }
            } else {
                arquivo.escreverNoArquivo("Nenhum robo esta no raio de seguranca do robo logo nenhum robo esta sendo protegido\n");
                System.out.println("Nenhum robo esta no raio de seguranca do robo logo nenhum robo esta sendo protegido\n");
            }

        } else {
            // missao mais basica so para ter duas opcoes
        }

    }
    

    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException{

    }

    public String getDescricao(){return "Agente que pode receber a missão segurança (que protege os robos dentro do raio de serem atacados)";}

}

        // if(getMissao() = missaoVida)
        // Missao missao = new MissaoSeguranca();

        // definirMissao(missao);
        // missao.executar(this, ambiente);
        // System.out.println("O robo esta patrulhando\n");