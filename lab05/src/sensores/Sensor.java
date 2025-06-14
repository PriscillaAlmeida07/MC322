package sensores;

import java.util.ArrayList;

import ambiente.Ambiente;
import enums.TipoSensor;
import interfaces.Entidade;

public abstract class Sensor {

    private double raio;
    private final TipoSensor tipo;

    // Construtor.
    public Sensor(double raio, TipoSensor tipo){
        this.raio = raio;
        this.tipo = tipo;
    }
    
    // Obtém o raio do sensor.
    public double getRaio(){
        return raio;
    }

    // Mudaremos o raio quando o agente Seguranca estiver usando o sensorRobos
    public void setRaio(double raio){
        this.raio = raio;
    }

    // Obtém o tipo do sensor.
    public TipoSensor getTipo(){
        return tipo;
    }

    // Declaração de que todas as classes que herdam de sensor devem ter:
    public abstract ArrayList<Entidade> monitorar(Ambiente ambiente, int[] vetorPosicao, int caso);
    public abstract void imprimirResultado(ArrayList<Entidade> resultado, int[] vetorPosicao);
    
}
