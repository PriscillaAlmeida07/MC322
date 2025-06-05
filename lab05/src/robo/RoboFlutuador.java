package robo;

import java.util.ArrayList;
import java.util.Scanner;

public class RoboFlutuador extends RoboAereo implements Curador {
    
    // Robo flutuador: incapaz de realizar subidas e descidas muito bruscas.
    private final int subidaMaxima;
    private final int descidaMaxima;
    private final int reparo;

    // Construtor.
    public RoboFlutuador(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        subidaMaxima = 10;
        descidaMaxima = 5;
        reparo = 2;
    }

    // Obtém a descrição desse robô.
    @Override
    public String getDescricao(){return "Robô aéreo incapaz de realizar subidas e descidas muito bruscas.";}

    // Obtém o tanto de vida que o robô é capaz de curar.
    @Override
    public int getReparo(){
        return reparo;
    }

    // A tarefa específica do RoboFlutuador é subir ou descer dentro do limite estabelecido.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for curado por outro robô");

        // Decide se o robô irá subir ou descer, dependendo do caso
        if (caso == 1){
            subir(ambiente, deltaX, deltaY, deltaZ); 
        } else { // caso == 2
            descer(ambiente, deltaX, deltaY, deltaZ);
        }
    }

    // Confere a velocidade de subida antes de realizar o movimento.
    @Override
    public void subir(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException {
        if (deltaZ <= subidaMaxima){
            super.subir(ambiente, deltaX, deltaY, deltaZ);
        } else { // Ele é incapaz de subir o tanto indicado
            System.out.println("O robô tentou subir mais do que o permitido, ao invéz disso, subirá " + subidaMaxima);
            super.subir(ambiente, deltaX, deltaY, subidaMaxima);
        }
    }

    // Confere a velocidade de descida antes de realizar o movimento.
    @Override
    public void descer(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException {
        if (deltaZ <= descidaMaxima){
            super.descer(ambiente, deltaX, deltaY, deltaZ);
        } else { // Ele é incapaz de descer o tanto indicado
            System.out.println("O robô tentou descer mais do que o permitido, ao invéz disso, descerá " + descidaMaxima);
            super.descer(ambiente, deltaX, deltaY, descidaMaxima);
        }
    }

    // Cura todos os robôs próximos (menos ele mesmo).
    @Override
    public void curar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for curado por outro robô");
        
        // Informações necessárias para o funcionamento da função:
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo){
                if(!robo.getID().equals(this.getID())){
                    
                    if (robo.getVida() == 10) {
                        System.out.println("O " + robo.getNome() + " não pode ser curado, pois já está com a vida máxima");
                    } else if ((robo.getVida() + reparo) >= 10){
                        robo.setVida(10 - robo.getVida());
                        System.out.println("O " + this.getNome() + " curou completamente o " + robo.getNome() + " que possui agora 10/10 de vida");
                    } else {
                        robo.setVida(reparo);
                        System.out.println("O " + this.getNome() + " curou o " + robo.getNome() + " que possui agora " + robo.getVida() + "/10 de vida");
                    }
                }
            }
        }
        System.out.print("\n");
    }
}
