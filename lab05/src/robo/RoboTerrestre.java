package robo;

public abstract class RoboTerrestre extends Robo {

    private final int velocidadeMaxima;

    // Construtor.
    public RoboTerrestre(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        velocidadeMaxima = 50;
    }
    
    // Obtém a velocidade máxima do robô.
    public int getVelocidadeMaxima(){
        return velocidadeMaxima;
    }

    // Confere se o robô é capaz de se movimentar em determinada velocidade.
    public void conferirVelocidade(int velocidade) throws VelocidadeMaxException {
        if (velocidade > velocidadeMaxima){
            throw new VelocidadeMaxException();
        }
    }
}
