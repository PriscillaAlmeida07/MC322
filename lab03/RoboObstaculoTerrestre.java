public class RoboObstaculoTerrestre extends RoboTerrestre {
    
    // Robô capaz de criar obstáculos posicionando blocos no solo.
    private int numBlocos;

    // Construtor.
    public RoboObstaculoTerrestre(String nome){
        super(nome);
        numBlocos = 1;
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs.
    public int soltarBlocos(){
        if (numBlocos == 0){
            System.out.println("Não há mais blocos disponíveis\n");
            return 0;
        } else { // Ainda tem blocos.
            numBlocos--;
            return 1;
        }
    }
}
