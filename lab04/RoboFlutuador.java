
import java.util.ArrayList;
import java.util.Scanner;

public class RoboFlutuador extends RoboAereo implements Curador{
    
    // Robo flutuador: ele é incapaz de realizar subidas e descidas muito bruscas.
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
    
    public String getDescricao(){return "Robô aéreo incapaz de realizar subidas e descidas muito bruscas.";}

    // A tarefa especifica do RoboFlutuador é subir ou descer dentro do limite estabelecido
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException{
        if(caso == 1)
            subir(ambiente, deltaX, deltaY, deltaZ); 
        else
            descer(ambiente, deltaX, deltaY, deltaZ);
    }

    // Confere a velocidade de subida antes de realizar o movimento.
    @Override
    public void subir(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException{
        if (deltaZ <= subidaMaxima){
            super.subir(ambiente, deltaX, deltaY, deltaZ);
        } else { // Ele é incapaz de subir o tanto indicado
            System.out.println("O robo tentou subir mais do que o permitido, ao invéz disso, subirá " + subidaMaxima);
            super.subir(ambiente, deltaX, deltaY, subidaMaxima);
        }
    }

    // Confere a velocidade de descida antes de realizar o movimento.
    @Override
    public void descer(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException{
        if (deltaZ <= descidaMaxima){
            super.descer(ambiente, deltaX, deltaY, deltaZ);
        } else { // Ele é incapaz de descer o tanto indicado
            System.out.println("O robo tentou descer mais do que o permitido, ao invéz disso, descerá " + descidaMaxima);
            super.descer(ambiente, deltaX, deltaY, descidaMaxima);
        }
    }

    @Override
    public int getReparo(){
        return reparo;
    }

    @Override
    public void curar(Ambiente ambiente){
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(reparo);
            }
        }
    }
}
