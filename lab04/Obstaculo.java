public class Obstaculo implements Entidade {
    
    // Posições iniciais dos obstáculos (menores valores ocupados).
    private final int posicaoX;
    private final int posicaoY;
    private final int posicaoZ;

    private final TipoObstaculo tipo;

    // Construtor.
    public Obstaculo(TipoObstaculo tipo, int posicaoX, int posicaoY, int posicaoZ){
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }

    // Obtém a posição inicial do obstáculo.
    public int[] getPosicao(){
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = posicaoZ;

        return vetor;
    }

    // Obtém o tipo do obstáculo.
    public TipoObstaculo getTipoObstaculo(){
        return tipo;
    }

    // Obtém posições do obstáculo:

    @Override
    public int getX(){return posicaoX;}
    @Override
    public int getY(){return posicaoY;}
    @Override
    public int getZ(){return posicaoZ;}

    // Obtém informações sobre o obstáculo:

    @Override
    public TipoEntidade getTipo(){return TipoEntidade.OBSTACULO;}
    @Override
    public String getDescricao(){return "Objeto que impede a permanência de um robô em determinada área do ambiente";}
    @Override
    public char getRepresentacao(){return 'O';}
}