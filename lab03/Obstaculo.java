public class Obstaculo {
    
    // Posições iniciais dos obstáculos.
    private int posicaoX;
    private int posicaoY;
    private int posicaoZ;

    private TipoObstaculo tipo;

    public Obstaculo(TipoObstaculo tipo, int posicaoX, int posicaoY, int posicaoZ){
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }

    public int[] getPosicao(){
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = posicaoZ;
        return vetor;
    }

    public TipoObstaculo getTipo(){
        return tipo;
    }
}