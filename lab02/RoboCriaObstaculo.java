public class RoboCriaObstaculo extends RoboTerrestre {
    
    // Robo capaz de criar obstáculos posicionando blocos no ambiente
    private int numBlocos;

    // Construtor
    public RoboCriaObstaculo(String nome){
        super(nome);
        numBlocos = 5;
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs
    public void soltarBlocos(int X, int Y){
        if (numBlocos == 0){
            System.out.println("Não há mais blocos disponíveis");
        } else {
            numBlocos--;
            
        }
    }
}
