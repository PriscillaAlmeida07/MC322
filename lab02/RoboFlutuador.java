public class RoboFlutuador extends RoboAereo {
    
    // Robo incapaz de realizar subidas e descidas muito bruscas.
    private int subidaMaxima;
    private int descidaMaxima;

    // Construtor.
    public RoboFlutuador(String nome){
        super(nome);
        subidaMaxima = 10;
        descidaMaxima = 5;
    }

    // Confere a velocidade de subida antes de realizar o movimento.
    @Override
    public void subir(int deltaZ){
        if (deltaZ <= subidaMaxima){
            super.subir(deltaZ);
        } else { // Ele é incapaz de subir o tanto indicado
            System.out.println("O robo tentou subir mais do que o permitido, ao invéz disso, subirá" + subidaMaxima);
            super.subir(subidaMaxima);
        }
    }

    // Confere a velocidade de descida antes de realizar o movimento.
    @Override
    public void descer(int deltaZ){
        if (deltaZ <= descidaMaxima){
            super.descer(deltaZ);
        } else { // Ele é incapaz de descer o tanto indicado
            System.out.println("O robo tentou descer mais do que o permitido, ao invéz disso, descerá" + descidaMaxima);
            super.descer(descidaMaxima);
        }
    }
}
