public abstract class RoboTerrestre extends Robo {

    private int velocidade;
    private final int velocidadeMaxima;

    // Construtor.
    public RoboTerrestre(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        velocidade = 0;
        velocidadeMaxima = 50;
    }
    

    // Obtém a velocidade máxima do robô.
    public int getVelocidadeMaxima(){
        return velocidadeMaxima;
    }

    // Define a velocidade atual.
    public void setVelocidade(int velocidade) throws VelocidadeMaxException{
        if(velocidade > velocidadeMaxima){
            throw new VelocidadeMaxException();
        }
        this.velocidade = velocidade;
        
    }

}
