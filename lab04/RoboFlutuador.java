
import java.util.ArrayList;
import java.util.Scanner;

public class RoboFlutuador extends RoboAereo implements Curador{
    
    // Robo flutuador: ele é incapaz de realizar subidas e descidas muito bruscas.
    private final int subidaMaxima;
    private final int descidaMaxima;
    private final int reparo;

    // Construtor.
    public RoboFlutuador(String nome, String id){
        super(nome, id);
        subidaMaxima = 10;
        descidaMaxima = 5;
        reparo = 2;
    }

    @Override
    public void executarTarefa(Scanner entrada){
        int deltaZ = 0;
        System.out.print("O robo subirá (digite 1) ou descerá (digite 2): ");
        int voo = entrada.nextInt();
        if ((voo == 1) || (voo == 2)) {
            System.out.print("Quantos metros: ");
            deltaZ = entrada.nextInt();
        }

        if (voo == 1){
            this.subir(deltaZ);
        } else if (voo == 2){
            this.descer(deltaZ);
        }
    }
    // Confere a velocidade de subida antes de realizar o movimento.
    @Override
    public void subir(int deltaZ){
        if (deltaZ <= subidaMaxima){
            super.subir(deltaZ);
        } else { // Ele é incapaz de subir o tanto indicado
            System.out.println("O robo tentou subir mais do que o permitido, ao invéz disso, subirá " + subidaMaxima);
            super.subir(subidaMaxima);
        }
    }

    // Confere a velocidade de descida antes de realizar o movimento.
    @Override
    public void descer(int deltaZ){
        if (deltaZ <= descidaMaxima){
            super.descer(deltaZ);
        } else { // Ele é incapaz de descer o tanto indicado
            System.out.println("O robo tentou descer mais do que o permitido, ao invéz disso, descerá " + descidaMaxima);
            super.descer(descidaMaxima);
        }
    }
/* 
    // Conserta a altitude do robô caso ele tenha tentado ir para uma posição inadequada.
    public void setAltitude(int deltaZ, int caso){
        if (caso == 1){ // Ele tentou subir, mas descerá para retornar a posição anterior
            if (deltaZ <= subidaMaxima){
                super.setAltitude(deltaZ);
            } else { // Ele é incapaz de subir o tanto indicado
                super.setAltitude(subidaMaxima);
            }
            
        } else { // (caso == 2) Ele tentou descer, mas subirá para retornar a posição anterior
            if (deltaZ <= descidaMaxima){
                super.setAltitude(-deltaZ);
            } else { // Ele é incapaz de descer o tanto indicado
                super.setAltitude(-descidaMaxima);
            } 
        }
    }
*/
    public String getDescricao(){return "Robo flutuador: ele é incapaz de realizar subidas e descidas muito bruscas.";}

    @Override
    public int getReparo(){
        return reparo;
    }

    @Override
    public void curar(Ambiente ambiente){
        Sensor sensor = getSensorRobo();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(reparo);
            }
        }
    }
}
