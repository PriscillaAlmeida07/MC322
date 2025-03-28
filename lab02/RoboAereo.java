public class RoboAereo extends Robo {
    
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String nome){
        super(nome);
        this.altitudeMaxima = 100;
        this.altitude = 0;
    }

    public void subir(int metros){
        this.altitude += metros;
    }

    public void descer(int metros){
        this.altitude -= metros;
    }
}
