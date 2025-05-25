public abstract class RoboTerrestre extends Robo {

    private int velocidade;
    private final int velocidadeMaxima;

    // Construtor.
    public RoboTerrestre(String nome, String id, EstadoRobo estado){
        super(nome, id, estado);
        velocidade = 0;
        velocidadeMaxima = 50;
    }

    // Obtém a velocidade máxima do robô.
    public int getVelocidadeMaxima(){
        return velocidadeMaxima;
    }

    // Define a velocidade atual.
    public void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    /*
    // Movimentação com override para considerar a velocidade máxima que um robô terrestre pode atingir.
    @Override
    public void mover(int deltaX, int deltaY){
        if (velocidade < 0) {
            System.out.println("O robô não pode se mover com velocidades negativas");
        } else if (velocidade <= velocidadeMaxima){
            super.mover(deltaX, deltaY);
        } else { // Velocidade acima da permitida
            System.out.println("O robô atingiu a velocidade máxima " + velocidadeMaxima + " e não pode se mover");
        }
    }
        */
}
