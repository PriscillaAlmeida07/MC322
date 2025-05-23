import java.util.ArrayList;

public abstract class Sensor {

    private final double raio;
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

    public TipoSensor getTipo(){
        return tipo;
    }

    // Declaração de que todas as classes que herdam de sensor devem ter uma função "monitorar".
    public abstract ArrayList<Entidade> monitorar(Ambiente ambiente, int[] vetorPosicao, int caso);
}
