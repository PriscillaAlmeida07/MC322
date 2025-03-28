public class RoboObstaculoTerrestre extends RoboTerrestre {
    
    // Robô capaz de criar obstáculos posicionando blocos no solo.
    private int numBlocos;

    // Construtor.
    public RoboObstaculoTerrestre(String nome){
        super(nome);
        numBlocos = 2;
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs.
    public void soltarBlocos(){
        if (numBlocos == 0){
            System.out.println("Não há mais blocos disponíveis");
        } else { // Ainda tem blocos.
            numBlocos--;
        }
    }
}
