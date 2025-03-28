public class RoboTerrestre extends Robo {

    private int velocidadeMaxima;
    private int velocidade;

    // Construtor.
    public RoboTerrestre(String nome){
        super(nome);
        velocidadeMaxima = 50;
        velocidade = 0;
    }

    public void setVelocidade(int velocidade){
        this.velocidade = velocidade;
    }

    @Override
    public void mover(int deltaX, int deltaY){
        if(velocidade < velocidadeMaxima){
            super.mover(deltaX, deltaY);
        } else {
            System.out.println("O robo atingiu a velocidade máxima " + velocidadeMaxima +" e não pode se mover");
        }
    }
}
