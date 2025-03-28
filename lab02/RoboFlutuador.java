public class RoboFlutuador extends RoboAereo {
    
    // Robo incapaz de realizar subidas e descidas muito bruscas.
    private int subidaMaxima;
    private int descidaMaxima;

    // Construtor;
    public RoboFlutuador(String nome){
        super(nome);
        subidaMaxima = 10;
        descidaMaxima = 5;
    }
}
