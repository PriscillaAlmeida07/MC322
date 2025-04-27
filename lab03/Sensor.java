public abstract class Sensor {

    private final double raio;

    // Construtor.
    public Sensor(){
        raio = 40;
    }
    
    // Obtém o raio do sensor.
    public double getRaio(){
        return raio;
    }

    // Declaração de que todas as classes que herdam de sensor devem ter uma função "monitorar".
    public abstract void monitorar(Ambiente ambiente, int[] vetorPosicao);
}
