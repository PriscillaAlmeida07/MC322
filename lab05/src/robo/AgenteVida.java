package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import exceptions.*;
import interfaces.Curador;
import interfaces.Entidade;
import java.util.ArrayList;
import java.util.Scanner;
import missao.*;
import sensores.*;

public class AgenteVida extends AgenteInteligente {

    // Construtor.
    public AgenteVida(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    @Override
    public void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao, Arquivo arquivo){

        String mensagem;
        ArrayList<Entidade> robosEmAlcance = utilizarSensorRobos(ambiente);

        if (missao instanceof MissaoReviver){
            System.out.println("O robô está procurando robôs mortos para revivê-los\n");

            ArrayList<Robo> robosMortos = encontrarRobosMortos(robosEmAlcance);
     
            if (!robosMortos.isEmpty()){
                missao.executar(this, ambiente, robosMortos, arquivo);
                comunicarRevividos(centralComunicacao, robosMortos);

            } else { // robosMortos está vazio
                mensagem = "Não há robôs mortos no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);
            }

        } else { // missao instanceof MissaoContactarCuradores
            System.out.println("O robô está procurando robôs com pouca vida e irá comunicar as suas posições para os curadores próximos\n");

            ArrayList<Robo> robosFracos = encontrarRobosFracos(robosEmAlcance);
            ArrayList<Curador> robosCuradores = encontrarRobosCuradores(robosEmAlcance);

            if ((!robosFracos.isEmpty()) && (!robosCuradores.isEmpty())){


                missao.executar(this, ambiente, robosEmAlcance, arquivo);
                comunicarRevividos(centralComunicacao, robosMortos);

            } else if (robosFracos.isEmpty()){
                mensagem = "Não há robôs com pouca vida no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);

            } else { // robosCuradores está vazio
                mensagem = "Não há robôs curadores para serem alertados no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);

            }
        }
    } 


    public ArrayList<Entidade> utilizarSensorRobos(Ambiente ambiente){
        int[] vetorPosicao = getPosicao();
        Sensor sensor = getSensorRobos();
        sensor.setRaio(30);
        ArrayList<Entidade> robosEmAlcance = sensor.monitorar(ambiente, vetorPosicao, 1);
        sensor.setRaio(4);
        return robosEmAlcance;
    }

    public ArrayList<Robo> encontrarRobosMortos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosMortos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo){
                if (robo.getVida() == 0){
                    robosMortos.add(robo);
                }
            }
        }
        return robosMortos;
    }

    public ArrayList<Robo> encontrarRobosFracos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosFracos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo){
                if ((robo.getVida() >= 1) && (robo.getVida() < 5)){
                    robosFracos.add(robo);
                }
            }
        }
        return robosFracos;
    }

    public ArrayList<Curador> encontrarRobosCuradores(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Curador> robosCuradores = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Curador curador){
                robosCuradores.add(curador);
            }
        }
        return robosCuradores;
    }

    public void comunicarRevividos(CentralComunicacao centralComunicacao, ArrayList<Robo> robosRevividos){
        String mensagem;

        for (int i = 0; i < robosRevividos.size(); i++){
            try {
                mensagem = "Você " + robosRevividos.get(i).getNome() + " reviveu por causa do Agente Vida";
                enviarMensagem(centralComunicacao, robosRevividos.get(i), mensagem);
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
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