public class RoboObstaculoAereo extends RoboAereo {
        
    // Robô capaz de criar obstáculos posicionando nuvens no céu.
    private int numNuvens;

    // Construtor.
    public RoboObstaculoAereo(String nome){
        super(nome);
        numNuvens = 2;
    }

    // Posiciona uma nuvem em sua posição, criando um obstáculo para outros robôs.
    public void soltarNuvens(){
        if ((numNuvens == 0)){
            System.out.println("Não há mais nuvens disponíveis");
        } else { // Ainda tem nuvens.
            numNuvens--;
        }
    }
}

