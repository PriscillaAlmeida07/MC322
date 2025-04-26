public abstract class Sensor {

    private double raio;

    // Construtor.
    public Sensor() {
        raio = 40;
    }
    
    // Obtém o raio do sensor.
    public double getRaio() {
        return raio;
    }

    // Define o raio do sensor.
    public void setRaio(double raio) {
        this.raio = raio; 
    }

    // Declaração de que todas as classes que herdam de sensor devem ter uma função "monitorar".
    public abstract void monitorar(Ambiente ambiente, int[] vetorPosicao);
}
