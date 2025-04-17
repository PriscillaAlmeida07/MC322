public class RoboTerrestre extends Robo {

    private int velocidade;
    private int velocidadeMaxima;

    // Construtor.
    public RoboTerrestre(String nome){
        super(nome);
        velocidade = 0;
        velocidadeMaxima = 50;
    }

    // Define a velocidade atual.
    public void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    // Movimentação com override para considerar a velocidade máxima que um robô terrestre pode atingir.
    @Override
    public void mover(int deltaX, int deltaY){
        if (velocidade <= velocidadeMaxima){
            super.mover(deltaX, deltaY);
        } else { // Velocidade acima da permitida
            System.out.println("O robo atingiu a velocidade máxima " + velocidadeMaxima + " e não pode se mover\n");
        }
    }
}
