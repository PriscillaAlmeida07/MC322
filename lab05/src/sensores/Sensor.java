package sensores;

import ambiente.Ambiente;
import enums.TipoSensor;
import interfaces.Entidade;
import java.util.ArrayList;

public abstract class Sensor {

    private double raio;
    private final TipoSensor tipo;

    // Construtor.
    public Sensor(double raio, TipoSensor tipo){
        this.raio = raio;
        this.tipo = tipo;
    }

    // Obtém o tipo do sensor.
    public TipoSensor getTipo(){
        return tipo;
    }
    
    // Obtém o raio do sensor.
    public double getRaio(){
        return raio;
    }

    // Altera o raio do sensor.
    public void setRaio(double raio){
        this.raio = raio;
    }

    // Declaração de que todas as classes que herdam de sensor devem ter:
    public abstract ArrayList<Entidade> monitorar(Ambiente ambiente, int[] vetorPosicao, int caso);
    public abstract void imprimirResultado(ArrayList<Entidade> resultado, int[] vetorPosicao);
    
}
