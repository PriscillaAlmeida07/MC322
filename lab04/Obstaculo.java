public class Obstaculo implements Entidade{
    
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

    public int getX(){
        return posicaoX;
    }
    public int getY(){
        return posicaoY;
    }
    public int getZ(){
        return posicaoZ;
    }
    public TipoEntidade getTipo(){
        return TipoEntidade.OBSTACULO;
    }
    public String getDescricao(){
        return "bla";
    }
    public char getRepresentacao(){
        return 'a';
    }
}