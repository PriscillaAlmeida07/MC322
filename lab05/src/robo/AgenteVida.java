package robo;
// passar por mensagem o robo necessitado de vidas p curador

import java.util.ArrayList;
import java.util.Scanner;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import exceptions.ColisaoException;
import exceptions.ErroComunicacaoException;
import exceptions.ForaDosLimitesException;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;
import interfaces.Entidade;
import missao.Missao;
import missao.MissaoVida;
import sensores.Sensor;

public class AgenteVida extends AgenteInteligente {

    public AgenteVida(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    @Override
    public void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao){

        if(missao instanceof MissaoVida){ // pois é protected
            System.out.println("O robo esta procurando robos mortos para revive-los\n");
            Sensor sensor = getSensorRobos();
            int[] vetorPosicao = getPosicao();
            // Vamos utilizar o sensor para achar os robos mais proximos do agente
            ArrayList<Entidade> robosProx = sensor.monitorar(ambiente, vetorPosicao, 1);
            ArrayList<Robo> robosMortos = new ArrayList<>();

            if(robosMortos.size() != 0){
                // O retorno de executar conterá um ArrayList de robosMortos
                robosMortos = missao.executar(this, ambiente, centralComunicacao, robosProx);
                for (int i=0; i< robosMortos.size(); i++){
                    // moverPara(robosMortos.get(i).getX() - 1, robosMortos.get(i).getY(),robosMortos.get(i).getZ()); mover sera complicado pois e se ele colidir com algo?
                    // Reviveremos robos mortos (Só agentesVidas conseguirao fazer robos reviverem criarei uma exception (nao fiz ainda kkk))
                    
                    robosMortos.get(i).setVida(1);
                    System.out.println("O " + robosMortos.get(i).getNome() + "reviveu: vida = 1");
                    
                    try{
                        enviarMensagem(centralComunicacao, robosMortos.get(i), "O robo reviveu"); //sempre sera valido mas temos q tratar com try msm assim
                    }  catch (ErroComunicacaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    
                    // nao sei se ja implementamos isso mas acho q precisamos desligar o robo morto e liga-lo aqui quando ele reviver (achoq so estamos diminuindo e aumentando a vida apenas)
                }
            } else {
                System.out.println("Nao há robos mortos no raio ");
                
            }
        } else {
            // aqui implementaremos uma missao mais basica so para ter duas opcoes
        }
    } 


    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException{

    }

    public String getDescricao(){return "Agente que";}


}



//     // Verifica o robô com a menor vida e manda uma mensagem para o curador mais próximo sobre a sua localização
//     @Override 
//     public void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao) {
//         ArrayList<Robo> robos = ambiente.getArrayRobos();
//         ArrayList<Robo> robosMortos = new ArrayList<>();
//         ArrayList<Robo> curadores = new ArrayList<>();

//         for (int i = 0; i < robos.size(); i++){
//             if (robos.get(i).getVida() == 0){
//                 robosMortos.add(robos.get(i));
//             } else if (robos.get(i) instanceof Curador){
//                 curadores.add(robos.get(i));
//             }
//         }

//         Robo roboProximo;
//         for (int i = 0; i < robosMortos.size(); i++){
//             for (int j= 0; j < curadores.size(); j++){
                
//             }
            
//         }

//     }

// }