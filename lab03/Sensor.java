public abstract class Sensor {
    private double raio;

    public Sensor(){
        raio = 40;
    }
    
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio; 
    }

    public abstract void monitorar(Ambiente ambiente, int[] vetorPosicao);
}
